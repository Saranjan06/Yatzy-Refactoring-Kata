package yatzy.game;

import yatzy.models.Dice;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author saran
 */
public class Yatzy {

    /**
     * Returns the sum of the all numbers
     * @param dices
     * @return
     */
    public static int chance(List<Dice> dices) {
        return sumDices(dices);
    }


/** *************************************************************************************************************** **/

    /**
     * Returns the sum of the selected side of the dice
     * @param dices
     * @return example : {1, 2 , 3 , 4 , 5} returns 15
     */
    private static int sumDices(List<Dice> dices) {
        return dices.stream().collect(Collectors.summingInt(e -> e.getSelectedSide().getNumber()));
    }
}
