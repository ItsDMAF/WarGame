package Assets;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<Card> cards;
    private transient final Random random;

    public Deck() {
        cards = new ArrayList<>();
        random = new Random();
        // Create a List for the suits
        String[] suits = { "Spades", "Hearts", "Clubs", "Diamonds" };

        // Adding the regular cards Paths(In image format)
        for (int suitNumber = 1; suitNumber <= 4; suitNumber++) {
            String suit = suits[suitNumber - 1];
            for (int rank = 2; rank <= 14; rank++) {
                String imagePath = "src/images/" + suitNumber + "-" + rank + ".gif";
                cards.add(new Card(suit, rank, imagePath));
            }
        }

        // Adding the Joker cards Paths and dummy cards
        String joker1Path = "src/images/5-1.gif";
        String joker2Path = "src/images/5-2.gif";
        String Dummy1Path = "src/images/6-1.gif";
        String Dummy2Path = "src/images/6-2.gif";

        cards.add(new Card("Joker", 15, joker1Path));
        cards.add(new Card("Joker", 15, joker2Path));
        cards.add(new Card("Dummycard1", 15, Dummy1Path));
        cards.add(new Card("Dummycard2", 15, Dummy2Path));
    }
    // Use random to shuffle the cards

    public void shuffle() {
        for (int i = cards.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    // Deal the deck to half(First deck)
    public List<Card> dealHalf() {
        shuffle();
        int halfSize = cards.size() / 2;
        List<Card> halfDeck = new ArrayList<>(cards.subList(0, halfSize));
        cards.subList(0, halfSize).clear();
        return halfDeck;
    }

    // Get the rest of cards for Second deck
    public List<Card> getRemainingCards() {
        return new ArrayList<>(cards);
    }

    // Getter
    public List<Card> getCards() {
        return cards;
    }

    // Getter
    public Random getRandom() {
        return random;
    }

    // Output
    @Override
    public String toString() {
        return "Deck"
                + "\ncards:" + cards
                + "\nrandom:" + random;
    }

}
