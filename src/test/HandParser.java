package test;

import java.util.Optional;

public class HandParser
{
    private static final HandParser instance = new HandParser();

    public static HandParser getInstance()
    {
        return instance;
    }

    public Optional<Hand> parse(String input)
    {
        var inputs = input.split(" ");
        var cards = new Card[inputs.length];
        for (int i = 0; i < inputs.length; i++)
        {
            var card = CardParser.getInstance().parse(inputs[i]);
            if (card.isEmpty())
                return Optional.empty();
            cards[i] = card.get();
        }
        return Optional.of(new Hand(cards));
    }
}
