package test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HandTest
{
    private static Hand hand(String input)
    {
        return HandParser.getInstance().parse(input).orElseThrow();
    }

    @org.junit.jupiter.api.Test
    void highestType()
    {
        assertEquals(new HandResult(HandType.HighestCard, Rank.Ace, Rank.C8, Rank.C7, Rank.C6, Rank.C2),
            hand("2Tr 6Ca 7Ca 8Tr APi").highestType());

        assertEquals(new HandResult(HandType.Pair, Rank.C5, Rank.King, Rank.Queen, Rank.C3),
            hand("3Tr 5Ca 5Co DCo RCo").highestType());

        assertEquals(new HandResult(HandType.TwoPair, Rank.Jack, Rank.C4, Rank.C9),
            hand("VCo VTr 4Tr 4Pi 9Co").highestType());

        assertEquals(new HandResult(HandType.ThreeKind, Rank.C3, Rank.King, Rank.C2),
            hand("3Ca 3Pi 3Tr RPi 2Pi").highestType());

        assertEquals(new HandResult(HandType.Straight, Rank.Jack),
            hand("VCo 10Co 9Tr 8Pi 7Co").highestType());

        assertEquals(new HandResult(HandType.Flush, Rank.Queen, Rank.Jack, Rank.C7, Rank.C6, Rank.C5),
            hand("7Tr VTr 5Tr 6Tr DTr").highestType());

        assertEquals(new HandResult(HandType.FullHouse, Rank.C4, Rank.C9),
            hand("4Ca 4Pi 4Tr 9Ca 9Tr").highestType());

        assertEquals(new HandResult(HandType.FourKind, Rank.C9, Rank.Jack),
            hand("9Tr 9Pi VCo 9Ca 9Co").highestType());

        assertEquals(new HandResult(HandType.StraightFlush, Rank.Queen),
            hand("DCo VCo 10Co 9Co 8Co").highestType());
    }

    void ascending(Hand... hands)
    {
        for (int i = 0; i < hands.length - 1; i++)
        {
            assertTrue(hands[i].compareType(hands[i + 1]) < 0);
        }
    }

    void tie(Hand h1, Hand h2)
    {
        assertEquals(0, h1.compareType(h2));
    }

    @Test
    void compareTo()
    {
        // meilleure carte
        ascending(
            hand("DCo 10Ca 7Pi 5Pi 2Co"),
            hand("DTr 10Tr 7Ca 5Tr 4Ca"),
            hand("DCo 10Co 7Tr 6Pi 4Tr"),
            hand("DPi 10Ca 8Tr 7Ca 4Pi"),
            hand("DPi VCa 6Tr 5Co 3Tr"),
            hand("RPi 6Tr 5Co 3Ca 2Tr")
        );
        tie(hand("10Tr 8Pi 7Pi 6Co 4Ca"), hand("10Ca 8Ca 7Pi 6Tr 4Tr"));

        // paire
        ascending(
            hand("6Ca 6Co DCa 8Co 3Pi"),
            hand("6Ca 6Co DPi 8Tr 7Ca"),
            hand("6Ca 6Co DCo VPi 2Tr"),
            hand("6Ca 6Co RPi 7Co 4Tr"),
            hand("9Tr 9Ca RPi VCo 5Co")
        );
        tie(hand("8Pi 8Ca 10Co 6Tr 5Pi"), hand("8Co 8Tr 10Tr 6Pi 5Tr"));

        // deux paires
        ascending(
            hand("5Tr 5Pi 3Ca 3Co DCo"),
            hand("5Tr 5Pi 4Ca 4Co 10Co"),
            hand("10Ca 10Pi 2Pi 2Tr RTr")
        );
        tie(hand("RCa RPi 7Ca 7Co 8Co"), hand("RTr RPi 7Tr 7Co 8Tr"));

        // brelan
        ascending(
            hand("3Ca 3Pi 3Tr VTr 7Co"),
            hand("3Ca 3Pi 3Tr RPi 2Pi"),
            hand("6Co 6Ca 6Pi RTr 4Pi")
        );
        tie(hand("9Pi 9Co 9Ca 10Ca 8Co"), hand("9Tr 9Pi 9Co 10Ca 8Ca"));

        // suite
        ascending(
            hand("6Tr 5Pi 4Co 3Pi 2Ca"),
            hand("10Pi 9Pi 8Tr 7Co 6Pi"),
            hand("VCo 10Co 9Tr 8Pi 7Co")
        );
        tie(hand("9Tr 8Tr 7Tr 6Ca 5Ca"), hand("9Pi 8Pi 7Pi 6Co 5Co"));

        // couleur
        ascending(
            hand("VCo 10Co 9Co 4Co 2Co"),
            hand("DTr VTr 7Tr 6Tr 5Tr"),
            hand("RCa VCa 9Ca 6Ca 4Ca")
            );
        tie(hand("10Ca 8Ca 7Ca 6Ca 5Ca"), hand("10Pi 8Pi 7Pi 6Pi 5Pi"));

        // full
        ascending(
            hand("4Ca 4Pi 4Tr 5Tr 5Ca"),
            hand("4Ca 4Pi 4Tr 9Ca 9Tr"),
            hand("8Pi 8Ca 8Co 7Ca 7Tr")
            );
        tie(hand("RTr RPi RCa VTr VPi"), hand("RTr RCo RCa VTr VCo"));

        // carré
        ascending(
            hand("7Co 7Ca 7Pi 7Tr 10Co"),
            hand("7Co 7Ca 7Pi 7Tr DCo"),
            hand("RPi RCo RTr RCa 3Co")
            );
        tie(hand("4Tr 4Pi 4Ca 4Co 9Tr"), hand("4Tr 4Pi 4Ca 4Co 9Ca"));

        // quinte flush
        ascending(
            hand("6Pi 5Pi 4Pi 3Pi 2Pi"),
            hand("8Co 7Co 6Co 5Co 4Co"),
            hand("10Tr 9Tr 8Tr 7Tr 6Tr")
            );
        tie(hand("7Ca 6Ca 5Ca 4Ca 3Ca"), hand("7Pi 6Pi 5Pi 4Pi 3Pi"));

        // tous types
        ascending(
            hand("DTr 10Tr 7Ca 5Tr 4Ca"),
            hand("6Ca 6Co DCo VPi 2Tr"),
            hand("5Tr 5Pi 4Ca 4Co 10Co"),
            hand("6Co 6Ca 6Pi RTr 4Pi"),
            hand("6Tr 5Pi 4Co 3Pi 2Ca"),
            hand("DTr VTr 7Tr 6Tr 5Tr"),
            hand("4Ca 4Pi 4Tr 5Tr 5Ca"),
            hand("7Co 7Ca 7Pi 7Tr 10Co"),
            hand("8Co 7Co 6Co 5Co 4Co")
        );
    }
}