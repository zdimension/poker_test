package test;

import java.util.Scanner;

public class Main
{
    private static final Scanner scanner = new Scanner(System.in);

    private static Hand readHand(int num)
    {
        while (true)
        {
            System.out.print("Main " + num + ":  ");
            var read = HandParser.getInstance().parse(scanner.nextLine());
            if (read.isPresent())
                return read.get();
        }
    }

    public static void main(String[] args)
    {
        while (true)
        {
            var hand1 = readHand(1).highestType();
            var hand2 = readHand(2).highestType();
            var comp = hand1.compareTo(hand2);
            if (comp == 0)
                System.out.println("Egalite");
            else
            {
                int handNum;
                HandResult handRes;

                if (comp > 0)
                {
                    handNum = 1;
                    handRes = hand1;
                }
                else
                {
                    handNum = 2;
                    handRes = hand2;
                }

                System.out.print("La main " + handNum + " gagne avec ");
                var handType = handRes.getType();
                var card = handRes.getRefScore()[0].toString();
                switch (handType)
                {
                    case HighestCard:
                        System.out.println("hauteur " + card);
                        break;
                    case Pair:
                        System.out.println("paire de " + card);
                        break;
                    case TwoPair:
                        System.out.println("double paire " + card + " - " + handRes.getRefScore()[1]);
                        break;
                    case ThreeKind:
                        System.out.println("brelan de " + card);
                        break;
                    case Straight:
                        System.out.println("suite hauteur " + card);
                        break;
                    case Flush:
                        System.out.println("couleur hauteur " + card);
                        break;
                    case FullHouse:
                        System.out.println("full " + card + " par " + handRes.getRefScore()[1]);
                        break;
                    case FourKind:
                        System.out.println("carré de " + card);
                        break;
                    case StraightFlush:
                        System.out.println("quinte flush de " + card);
                        break;
                }
            }

            System.out.println();
        }
    }
}
