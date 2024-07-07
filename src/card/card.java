package card;

public class card {
    private int pointsIndex;
    private int suitIndex;
    static String[] points = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
    static String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };

    public card(int pointsIndex, int suitIndex) {
        this.pointsIndex = pointsIndex;
        this.suitIndex = suitIndex;
    }

    public int getPointsIndex() {
        return pointsIndex;
    }

    public void setPointsIndex(int pointsIndex) {
        this.pointsIndex = pointsIndex;
    }

    public int getSuitIndex() {
        return suitIndex;
    }

    public void setSuitIndex(int suitIndex) {
        this.suitIndex = suitIndex;
    }

    @Override
    public String toString() {
        return suits[suitIndex] + " " + points[pointsIndex];
    }
}