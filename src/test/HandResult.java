package test;

import java.util.Arrays;
import java.util.Objects;

public class HandResult implements Comparable<HandResult>
{
    HandType type;
    Rank[] refScore;

    public HandResult(HandType type, Rank... refScore)
    {
        this.type = type;
        this.refScore = refScore;
    }

    public HandType getType()
    {
        return type;
    }

    public Rank[] getRefScore()
    {
        return refScore.clone();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HandResult that = (HandResult) o;
        return type == that.type &&
            Arrays.equals(refScore, that.refScore);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(type, refScore);
    }

    @Override
    public int compareTo(HandResult o)
    {
        var typeComp = getType().compareTo(o.getType());
        if (typeComp == 0)
        {
            for (var i = 0; i < refScore.length; i++)
            {
                var comp = refScore[i].compareTo(o.refScore[i]);
                if (comp != 0)
                    return comp;
            }

            return 0;
        }
        else
            return typeComp;
    }

    @Override
    public String toString()
    {
        return "HandResult{" +
            "type=" + type +
            ", refScore=" + Arrays.toString(refScore) +
            '}';
    }
}
