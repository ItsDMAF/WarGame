package Assets;

import java.io.Serializable;

public class Card implements Serializable {
    private final String suit;
    private static final long serialVersionUID = 1L;
    private final int rank;
    private final String imagePath;

    public Card(String suit, int rank, String imagePath) {
        this.suit = suit;
        this.rank = rank;
        this.imagePath = imagePath;
    }

    // Getter of suit
    public String getSuit() {
        return suit;
    }

    // Getter of rank
    public int getRank() {
        return rank;
    }

    // Getter of ImagePath
    public String getImagePath() {
        return imagePath;
    }

    // Getter
    @Override
    public String toString() {
        return "Card"
                + "\nsuit:" + suit
                + "\nrank:" + rank
                + "\nimagePath:" + imagePath;
    }

}
