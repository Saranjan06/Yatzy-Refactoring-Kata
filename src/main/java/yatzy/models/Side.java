package yatzy.models;

/**
 * @author saran
 */
public class Side {
    private int number;
    private boolean visible;

    /**
     * default constructor
     */
    public Side() {

    }

    /**
     * Constructor to initialize a side's number
     * @param number
     */
    public Side(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
