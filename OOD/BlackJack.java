package OOD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BlackJack {
    public static void main(String[] args) {
        Simulator sim = new Simulator();
        sim.simulate();
    }
}

enum Suit {
    Heart,
    Spade,
    Club,
    Diamond
}

enum Action {
    Hit,
    Stand
}

class Card implements Comparable<Card> {
    private final int faceValue;
    private final Suit suit;

    //Ctor
    Card(int v, Suit s) {
        faceValue = v;
        suit = s;
    }

    int getFaceValue() {
        return faceValue;
    }

    boolean isAce() {
        return faceValue == 1;
    }

    boolean isFace() {
        return faceValue >= 10;
    }

    @Override
    public int compareTo(Card another) {
        return this.getFaceValue() - another.getFaceValue();
    }

    @Override
    public String toString() {
        return "Card{ FaceValue = " + faceValue + "; Suit = " + suit;
    }
}

class Deck {
    private List<Card> deck;

    Deck() {
        deck = new ArrayList<>(52);
        for (int i = 1; i <= 13; i++) {
            for (Suit s : Suit.values()) {
                deck.add(new Card(i, s));
            }
        }
    }

    public void shuffle() {
        Random rand = new Random();
        for (int i = deck.size() - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Card a = deck.get(i);
            Card b = deck.get(j);
            deck.set(i, b);
            deck.set(j, a);
        }
    }

    public Card dealCard() {
        return this.isEmpty() ? null : deck.remove(deck.size() - 1);
    }

    public boolean isEmpty() {
        return deck.size() == 0;
    }
}

class Player {
    private List<Card> hand;
    private final int strategy;
    private static final int DEFAULT_STRATEGY = 18;

    Player() {
        this(DEFAULT_STRATEGY);
    }

    Player(int strategy) {
        hand = new ArrayList<>();
        this.strategy = strategy;
    }

    public List<Card> getHand() {
        return hand;
    }

    public Action action(Deck deck) {
        if (this.score() < strategy) {
            hit(deck.dealCard());
            return Action.Hit;
        }
        return Action.Stand;
    }

    public void hit(Card card) {
        hand.add(card);
    }

    public void stand() {
        return;
    }

    public int score() {
        int score = 0;
        List<Card> newHand = new ArrayList<>(hand.size());
        for (Card elem : hand) {
            newHand.add(elem);
        }
        Collections.sort(newHand, Collections.reverseOrder());
        int i = 0;
        for (; i < newHand.size(); i++) {
            Card cur = newHand.get(i);
            if (cur.isFace()) {
                score += 10;
            } else if (!cur.isAce()) {
                score += cur.getFaceValue();
            } else {
                break;
            }
            if (score > 21) {
                return 0;
            }
        }
        int numAce = newHand.size() - i;
        if (numAce != 0) {
            int max = score + 10 + numAce;
            if (max <= 21) {
                return max;
            } else {
                int min = score + numAce;
                return min > 21 ? 0 : min;
            }
        }
        return score;
    }

    public boolean isBusted() {
        return this.score() == 0;
    }
}

class Dealer extends Player {
    private static final int MUST_HIT_CRITERIA = 17;

    Dealer() {
        super(MUST_HIT_CRITERIA);
    }
}

class Simulator {
    Deck deck;
    Player player;
    Player dealer;

    Simulator() {
        deck = new Deck();
        deck.shuffle();
        player = new Player(18);
        dealer = new Dealer();
    }

    public void simulate() {
        player.hit(deck.dealCard());
        dealer.hit(deck.dealCard());
        player.hit(deck.dealCard());
        dealer.hit(deck.dealCard());
        play();
    }

    private void play() {
        while (player.action(deck) != Action.Stand) {
            if (player.isBusted()) {
                System.out.println("Player busted! Dealer wins.");
                System.out.println("player's hand:");
                printStatus(player);
                return;
            }
        }
        while (!dealer.isBusted() && dealer.score() <= player.score() && dealer.action(deck) == Action.Hit) {
            if (dealer.isBusted()) {
                System.out.println("Dealer busted. Player wins.");
                System.out.println("player's hand:");
                printStatus(player);
                System.out.println("dealer's hand:");
                printStatus(dealer);
                return;
            }
        }
        if (dealer.score() > player.score()) {
            System.out.println("Dealer wins.");
        } else if (dealer.score() == player.score()) {
            System.out.println("Draw.");
        } else {
            System.out.println("Player wins.");
        }
        System.out.println("player's hand:");
        printStatus(player);
        System.out.println("dealer's hand:");
        printStatus(dealer);
    }

    private void printStatus(Player player) {
        for (Card elem : player.getHand()) {
            System.out.println(elem);
        }
        System.out.println(player.score());
    }
}