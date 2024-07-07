package Assets;

import java.io.Serializable;

//Use abstract way to get the dummy cards
abstract class Dummycard implements Serializable {
    public abstract void Dummycard();

    private String dumsuit;
    private static final long serialVersionUID = 1L;
    private int dumrank;
    private String imagePath;

    public void Card(String dumsuit, int dumrank, String imagePath) {
        this.dumsuit = dumsuit;
        this.dumrank = dumrank;
        this.imagePath = imagePath;
    }

    // Getter of the suit
    public String getSuit() {
        return dumsuit;
    }

    // Getter of the rank
    public int getRank() {
        return dumrank;
    }

    // Getter of the Imagepath
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public String toString() {
        return "Card"
                + "\nsuit:" + dumsuit
                + "\nrank:" + dumrank
                + "\nimagePath:" + imagePath;
    }

}
