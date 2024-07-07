package Assets;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Player implements Serializable {
    private final String name;
    private static final long serialVersionUID = 1L;
    private final LinkedList<Card> hand;
    private final LinkedList<Card> discardPile;

    public Player(String name) {
        this.name = name;
        this.hand = new LinkedList<>();
        this.discardPile = new LinkedList<>();
    }

    // Getter of name
    public String getName() {
        return name;
    }

    // Getter of cards for hand
    public LinkedList<Card> getHand() {
        return hand;
    }

    // Getter of cards for warpiles
    public LinkedList<Card> getDiscardPile() {
        return discardPile;
    }

    // Add the shuffled cards to hand
    public void addCardsToHand(List<Card> cards) {
        hand.addAll(cards);
    }

    // Add the shuffled discarded warpiles to hands
    public void addCardsToDiscardPile(List<Card> cards) {
        discardPile.addAll(cards);
    }

    // shuffle the discards to hand
    public void shuffleDiscardIntoHand() {
        Collections.shuffle(discardPile);
        hand.addAll(discardPile);
        discardPile.clear();
    }

    // initialization
    public void updateState(Player other) {
        if (!this.name.equals(other.getName())) {
            throw new IllegalArgumentException("Player names do not match");
        }
        this.hand.clear();
        this.hand.addAll(other.getHand());
        this.discardPile.clear();
        this.discardPile.addAll(other.getDiscardPile());
    }

    @Override
    public String toString() {
        return "Player"
                + "\nname:" + name
                + "\nhand:" + hand
                + "\ndiscardPile:" + discardPile;
    }

}
