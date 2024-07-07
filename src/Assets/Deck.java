package Assets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private final List<Card> cards;
    private final Random random;

    public Deck() {
        cards = new ArrayList<>();
        random = new Random(); 

        String[] suits = {"Spades", "Hearts", "Clubs", "Diamonds"};
        
        //Adding the regular cards Paths
        for (int suitNumber = 1; suitNumber <= 4; suitNumber++) {
            String suit = suits[suitNumber - 1];
            for (int rank = 2; rank <= 14; rank++) {
                String imagePath = "src/images/" + suitNumber + "-" + rank + ".gif";
                cards.add(new Card(suit, rank, imagePath));
            }
        }

        // Adding the Joker cards Paths
        String joker1Path = "src/images/5-1.gif";
        String joker2Path = "src/images/5-2.gif";
        cards.add(new Card("Joker", 15, joker1Path));
        cards.add(new Card("Joker", 15, joker2Path));
    }

    public void shuffle() {
        for (int i = cards.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

 public List<Card> dealHalf() {
        shuffle();
        int halfSize = cards.size() / 2;
        List<Card> halfDeck = new ArrayList<>(cards.subList(0, halfSize));
        cards.subList(0, halfSize).clear();
        return halfDeck;
    }


    public List<Card> getRemainingCards() {
        return new ArrayList<>(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public Random getRandom() {
        return random;
    }

    @Override
    public String toString() {
        return "Deck" 
                + "\ncards:" + cards 
                + "\nrandom:" + random;
    }
    
    
}
