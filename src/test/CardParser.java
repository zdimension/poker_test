package test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CardParser
{
    private static final CardParser instance = new CardParser();
    private static final Map<String, Rank> frenchRanks = new HashMap<>()
    {
        {
            put("2", Rank.C2);
            put("3", Rank.C3);
            put("4", Rank.C4);
            put("5", Rank.C5);
            put("6", Rank.C6);
            put("7", Rank.C7);
            put("8", Rank.C8);
            put("9", Rank.C9);
            put("10", Rank.C10);
            put("V", Rank.Jack);
            put("D", Rank.Queen);
            put("R", Rank.King);
            put("A", Rank.Ace);
        }
    };
    private static final Map<String, Suit> frenchSuits = Map.of(
        "Tr", Suit.Clubs,
        "Ca", Suit.Diamonds,
        "Co", Suit.Hearts,
        "Pi", Suit.Spades
    );

    public static CardParser getInstance()
    {
        return instance;
    }

    public Optional<Card> parse(String input)
    {
        var rankOpt = frenchRanks.keySet().stream().filter(input::startsWith).findFirst();

        if (rankOpt.isPresent())
        {
            var rankStr = rankOpt.get();
            var rank = frenchRanks.get(rankStr);

            var suitStr = input.substring(rankStr.length());
            var suit = frenchSuits.get(suitStr);

            if (suit != null)
            {
                return Optional.of(new Card(suit, rank));
            }
        }

        return Optional.empty();
    }
}
