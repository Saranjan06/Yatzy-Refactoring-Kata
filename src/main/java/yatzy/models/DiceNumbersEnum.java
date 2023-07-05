package yatzy.models;

/**
 * @author saran
 */
public enum DiceNumbersEnum {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6);

    private final int number;

    private DiceNumbersEnum(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
