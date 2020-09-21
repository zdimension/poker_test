package test;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandParserTest
{
    private static Optional<Hand> hand(String input)
    {
        return HandParser.getInstance().parse(input);
    }

    @Test
    void parse()
    {
        assertEquals(Optional.of(new Hand(new Card[]{
            new Card(Suit.Clubs, Rank.C2),
            new Card(Suit.Diamonds, Rank.C6),
            new Card(Suit.Diamonds, Rank.C7),
            new Card(Suit.Clubs, Rank.C8),
            new Card(Suit.Spades, Rank.Ace)
        })), hand("2Tr 6Ca 7Ca 8Tr APi"));

        assertEquals(Optional.empty(), hand("invalid input"));
        assertEquals(Optional.empty(), hand("2Tr 6Cu 7Ca 8Tr APi"));
        assertEquals(Optional.empty(), hand("2Tr 6Ca 7Ca 8Tr 1Pi"));
    }
}