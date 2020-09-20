package test;

public enum Rank
{
    C2("2"),
    C3("3"),
    C4("4"),
    C5("5"),
    C6("6"),
    C7("7"),
    C8("8"),
    C9("9"),
    C10("10"),
    Jack("Valet"),
    Queen("Dame"),
    King("Roi"),
    Ace("As");

    String display;

    Rank(String display)
    {
        this.display = display;
    }

    @Override
    public String toString()
    {
        return display;
    }

    boolean isConsecutive(Rank next)
    {
        return next.ordinal() - this.ordinal() == 1;
    }
}
