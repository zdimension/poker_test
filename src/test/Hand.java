package test;

import java.util.Arrays;

public class Hand
{
    Card[] cards;

    public Hand(Card[] cards) throws IllegalArgumentException
    {
        if (cards.length != 5)
            throw new IllegalArgumentException();

        this.cards = cards.clone();
        Arrays.sort(this.cards);
    }

    public Card[] getCards()
    {
        return cards.clone();
    }

    public HandResult highestType()
    {
        var types = HandType.values();

        for (var i = types.length - 1; i >= 0; i--)
        {
            var result = types[i].getScoreFunc().apply(this);
            if (result.isPresent())
                return new HandResult(types[i], result.get().toArray(new Rank[0]));
        }

        throw new RuntimeException("This should never happen.");
    }

    @Override
    public String toString()
    {
        return '{' + Arrays.toString(cards) + '}';
    }

    public int compareType(Hand other)
    {
        return highestType().compareTo(other.highestType());
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hand hand = (Hand) o;
        return Arrays.equals(cards, hand.cards);
    }

    @Override
    public int hashCode()
    {
        return Arrays.hashCode(cards);
    }
}
