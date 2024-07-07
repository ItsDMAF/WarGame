package Assets;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Player {
    private final String name;
    private final LinkedList<Card> hand;
    private final LinkedList<Card> discardPile;

    public Player(String name) {
        this.name = name;
        this.hand = new LinkedList<>();
        this.discardPile = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public LinkedList<Card> getHand() {
        return hand;
    }

    public LinkedList<Card> getDiscardPile() {
        return discardPile;
    }

    public void addCardsToHand(List<Card> cards) {
        hand.addAll(cards);
    }

    public void addCardsToDiscardPile(List<Card> cards) {
        discardPile.addAll(cards);
    }

    public void shuffleDiscardIntoHand() {
        Collections.shuffle(discardPile);
        hand.addAll(discardPile);
        discardPile.clear();
    }

    @Override
    public String toString() {
        return "Player" 
                + "\nname:" + name 
                + "\nhand:" + hand 
                + "\ndiscardPile:" + discardPile;
    }
    
    
}
