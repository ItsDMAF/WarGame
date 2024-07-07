package Assets;

public class Card {
    private final String suit;
    private final int rank;
    private final String imagePath;

    public Card(String suit, int rank, String imagePath) {
        this.suit = suit;
        this.rank = rank;
        this.imagePath = imagePath;
    }
    
    public String getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public String toString() {
        return "Card"
                + "\nsuit:" + suit
                + "\nrank:" + rank 
                + "\nimagePath:" + imagePath;
    }
    
    
}
