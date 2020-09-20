package test;

import java.util.Objects;

public class Card implements Comparable<Card>
{
    Suit suit;
    Rank rank;

    public Card(Suit suit, Rank rank)
    {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit()
    {
        return suit;
    }

    public Rank getRank()
    {
        return rank;
    }

    @Override
    public int compareTo(Card o)
    {
        return this.rank.compareTo(o.rank);
    }

    @Override
    public String toString()
    {
        return "(" + rank + ", " + suit + ")";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit &&
            rank == card.rank;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(suit, rank);
    }
}
