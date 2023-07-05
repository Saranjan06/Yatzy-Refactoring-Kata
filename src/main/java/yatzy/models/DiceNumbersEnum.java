package yatzy.models;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author saran
 */
public enum DiceNumbersEnum {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6);

    private final int number;

    private DiceNumbersEnum(int number) {
        this.number = number;
    }

    /**
     * Returns the DiceNumbersEnum corresponding to the given parameter
     * @param number
     * @return
     */
    public static DiceNumbersEnum giveDiceNumberFor(int number) {
        Optional<DiceNumbersEnum> diceEnum = Arrays.stream(values()).filter(n -> n.getNumber() == number).findFirst();
        if (diceEnum.isPresent()) {
            return diceEnum.get();
        }
        return null;
    }

    public int getNumber() {
        return number;
    }
}
