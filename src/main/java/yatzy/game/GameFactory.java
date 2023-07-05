package yatzy.game;

import yatzy.models.Dice;
import yatzy.enumerators.DiceNumbersEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author saran
 */
public class GameFactory {

    /**
     * Initilise dices with selected faces
     * @param selectedNumbers
     * @return
     */
    public static List<Dice> initDices(int... selectedNumbers) {
        List<Dice> dices = new ArrayList<>();
        final int maximumDices = 5;
        if (selectedNumbers.length == maximumDices) {
            Arrays.stream(selectedNumbers).forEach(n -> dices.add(new Dice(DiceNumbersEnum.giveDiceNumberFor(n))));
        }
        return dices;
    }
}
