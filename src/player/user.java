package player;

import card.card;
import java.util.*;

public class user {
    private int id;
    private String name;
    private List<card> cards;

    // Constructor
    public user(int id, String name) {
        this.id = id;
        this.name = name;
        this.cards = new ArrayList<>();
    }

    // Method to add a card
    public void addCard(card Card) {
        cards.add(Card);
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for cards
    public List<card> getCards() {
        return cards;
    }

}