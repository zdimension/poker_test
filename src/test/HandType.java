package test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum HandType
{
    /**
     * hauteur
     */
    HighestCard(hand ->
    {
        return Optional.of(
            Arrays.stream(hand.getCards())
                .map(Card::getRank)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList()));
    }),
    /**
     * paire
     */
    Pair(hand ->
    {
        var groups = getGrouping(hand);
        return checkGrouping(groups, groups.get(0).getValue() == 2);
    }),
    /**
     * deux paires différentes
     */
    TwoPair(hand ->
    {
        var groups = getGrouping(hand);
        return checkGrouping(groups, groups.get(0).getValue() == 2 && groups.get(1).getValue() == 2);
    }),
    /**
     * Brelan - 3 de la même valeur
     */
    ThreeKind(hand ->
    {
        var groups = getGrouping(hand);
        return checkGrouping(groups, groups.get(0).getValue() == 3);
    }),
    /**
     * Suite - 5 de valeurs consécutives
     */
    Straight(hand ->
    {
        var cards = hand.getCards();
        for (var i = 0; i < cards.length - 1; i++)
            if (!cards[i].getRank().isConsecutive(cards[i + 1].getRank()))
                return Optional.empty();
        return Optional.of(List.of(HighestCard.getScoreFunc().apply(hand).orElseThrow().get(0)));
    }),
    /**
     * Couleur - 5 de même couleur
     */
    Flush(hand ->
    {
        if (Arrays.stream(hand.getCards()).map(Card::getSuit).distinct().count() == 1)
            return HighestCard.getScoreFunc().apply(hand);
        else
            return Optional.empty();
    }),
    /**
     * Full - 3 de même valeur et 2 formant une paire
     */
    FullHouse(hand ->
    {
        var groups = getGrouping(hand);
        if (groups.get(0).getValue() == 3 && groups.get(1).getValue() == 2)
        {
            return Optional.of(List.of(groups.get(0).getKey(), groups.get(1).getKey()));
        }

        return Optional.empty();
    }),
    /**
     * Carré - 4 de même valeur
     */
    FourKind(hand ->
    {
        var groups = getGrouping(hand);
        return checkGrouping(groups, groups.get(0).getValue() == 4);
    }),
    /**
     * Quinte Flush - 5 de même couleur et de valeurs consécutives
     */
    StraightFlush(hand ->
    {
        if (Flush.getScoreFunc().apply(hand).isEmpty())
            return Optional.empty();
        else
            return Straight.getScoreFunc().apply(hand);
    });

    private final Function<Hand, Optional<List<Rank>>> scoreFunc;

    HandType(Function<Hand, Optional<List<Rank>>> scoreFunc)
    {
        this.scoreFunc = scoreFunc;
    }

    private static List<Map.Entry<Rank, Long>> getGrouping(Hand hand)
    {
        return Arrays.stream(hand.getCards()).collect(Collectors.groupingBy(Card::getRank, Collectors.counting()))
            .entrySet().stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .collect(Collectors.toList());
    }

    private static Optional<List<Rank>> checkGrouping(List<Map.Entry<Rank, Long>> groups, boolean condition)
    {
        if (condition)
        {
            return Optional.of(groups.stream().map(Map.Entry::getKey).collect(Collectors.toList()));
        }

        return Optional.empty();
    }

    public Function<Hand, Optional<List<Rank>>> getScoreFunc()
    {
        return scoreFunc;
    }
}
