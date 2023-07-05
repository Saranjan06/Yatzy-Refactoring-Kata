package yatzy.game;

import yatzy.models.Dice;
import java.util.List;

/**
 * @author saran
 */
public class App {

    public static void main(String[] args) {
        List<Dice> dices = GameFactory.initDices(new int[]{1, 3 , 5 , 6 , 2});
        int score = Yatzy.chance(dices);
        System.out.println("Score = " + score);
    }
}
