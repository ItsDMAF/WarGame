package GameLogic;

import Assets.Card;
import Assets.Deck;
import Assets.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameController implements Serializable {
	private static final long serialVersionUID = 1L;
    private final Player player1;
    private final Player player2;
    private final Deck deck;
    private boolean isWar;
    private final List<Card> warPile;
    private Card player1Card;
    private Card player2Card;
    private final boolean vsComputer;

    public GameController(Player player1, Player player2, boolean vsComputer) {
        this.player1 = player1;
        this.player2 = player2;
        this.deck = new Deck();
        this.isWar = false;
        this.warPile = new ArrayList<>();
        this.vsComputer = vsComputer;
    }

    public void startGame() {
        player1.getHand().clear();
        player1.getDiscardPile().clear();
        player2.getHand().clear();
        player2.getDiscardPile().clear();

        deck.shuffle();
        List<Card> firstHalf = deck.dealHalf();
        List<Card> secondHalf = deck.getRemainingCards();

        player1.addCardsToHand(firstHalf);
        player2.addCardsToHand(secondHalf);
    }

    public String playRound() {
        if (player1.getHand().isEmpty()) {
            player1.shuffleDiscardIntoHand();
        }
        if (player2.getHand().isEmpty()) {
            player2.shuffleDiscardIntoHand();
        }

        if (player1.getHand().isEmpty() || player2.getHand().isEmpty()) {
            return "Game Over! " + (player1.getHand().isEmpty() ? player2.getName() : player1.getName()) + " wins!";
        }

        player1Card = player1.getHand().poll();
        player2Card = player2.getHand().poll();

        warPile.clear();
        warPile.add(player1Card);
        warPile.add(player2Card);

        int comparison = compareCards(player1Card, player2Card);
        if (comparison > 0) {
            player1.addCardsToDiscardPile(warPile);
            isWar = false;
            return player1.getName() + " wins the round!";
        } else if (comparison < 0) {
            player2.addCardsToDiscardPile(warPile);
            isWar = false;
            return player2.getName() + " wins the round!";
        } else {
            isWar = true;
            return "War!";
        }
    }

    public String resolveWar() {
        if (!isWar) return "No war to resolve!";
        
        for (int i = 0; i < 3; i++) {
            if (player1.getHand().isEmpty()) player1.shuffleDiscardIntoHand();
            if (player2.getHand().isEmpty()) player2.shuffleDiscardIntoHand();

            if (player1.getHand().isEmpty() || player2.getHand().isEmpty()) {
                return "Game Over! " + (player1.getHand().isEmpty() ? player2.getName() : player1.getName()) + " wins!";
            }

            player1Card = player1.getHand().poll();
            player2Card = player2.getHand().poll();
            warPile.add(player1Card);
            warPile.add(player2Card);
        }

        int comparison = compareCards(player1Card, player2Card);
        if (comparison > 0) {
            player1.addCardsToDiscardPile(warPile);
            isWar = false;
            return player1.getName() + " wins the war!";
        } else if (comparison < 0) {
            player2.addCardsToDiscardPile(warPile);
            isWar = false;
            return player2.getName() + " wins the war!";
        } else {
            return resolveWar(); // Recursive in case of any consecutive wars
        }
    }

    private int compareCards(Card c1, Card c2) {
        // Compare Jokers first
        if (c1.getRank() == 15 && c2.getRank() == 15) return 0;
        if (c1.getRank() == 15) return 1;
        if (c2.getRank() == 15) return -1;

        if (c1.getRank() == 14 && c2.getRank() == 14) return 0;
        if (c1.getRank() == 14) return 1;
        if (c2.getRank() == 14) return -1;

        return Integer.compare(c1.getRank(), c2.getRank());
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public List<Card> getWarPile() {
        return warPile;
    }

    public Card getPlayer1Card() {
        return player1Card;
    }

    public Card getPlayer2Card() {
        return player2Card;
    }

    public boolean isWar() {
        return isWar;
    }

    public boolean isVsComputer() {
        return vsComputer;
    }

    public boolean isIsWar() {
        return isWar;
    }

    public void setIsWar(boolean isWar) {
        this.isWar = isWar;
    }
    
    
    public void updateState(GameController other) {
        if (!this.player1.getName().equals(other.getPlayer1().getName()) || !this.player2.getName().equals(other.getPlayer2().getName())) {
            throw new IllegalArgumentException("Player names do not match");
        }
        this.isWar = other.isWar();
        this.warPile.clear();
        this.warPile.addAll(other.getWarPile());
        this.player1Card = other.getPlayer1Card();
        this.player2Card = other.getPlayer2Card();
    }
    

    @Override
    public String toString() {
        return "GameController" 
                + "\nplayer1:" + player1 
                + "\nplayer2:" + player2 
                + "\ndeck:" + deck 
                + "\nisWar:" + isWar 
                + "\nwarPile:" + warPile 
                + "\nplayer1Card:" + player1Card 
                + "\nplayer2Card:" + player2Card 
                + "\nvsComputer:" + vsComputer;
    }
    
    
}
