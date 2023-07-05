package yatzy.game;

import yatzy.models.Dice;
import yatzy.enumerators.DiceNumbersEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author saran
 */
public class App {

    public static void main(String[] args) {
        List<Dice> dices = new ArrayList<>();

        dices.add(new Dice(DiceNumbersEnum.ONE));
        dices.add(new Dice(DiceNumbersEnum.TWO));
        dices.add(new Dice(DiceNumbersEnum.THREE));
        dices.add(new Dice(DiceNumbersEnum.FOUR));
        dices.add(new Dice(DiceNumbersEnum.FIVE));

        int score = Yatzy.chance(dices);
        System.out.println("Score = " + score);

    }


}
