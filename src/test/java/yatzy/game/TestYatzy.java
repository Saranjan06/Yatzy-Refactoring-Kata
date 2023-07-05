package yatzy.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import yatzy.models.Dice;
import yatzy.models.DiceNumbersEnum;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author saran
 */
public class TestYatzy {

    @Test
    @DisplayName("Testing chance()...")
    public void testChance() {
        List<Dice> dices = new ArrayList<>();
        dices.add(new Dice(DiceNumbersEnum.TWO));
        dices.add(new Dice(DiceNumbersEnum.THREE));
        dices.add(new Dice(DiceNumbersEnum.FOUR));
        dices.add(new Dice(DiceNumbersEnum.FIVE));
        dices.add(new Dice(DiceNumbersEnum.ONE));
        int expected = 15;

        int output = Yatzy.chance(dices);
        assertEquals(expected, output, new StringBuilder().append("[2, 3, 4, 5, 1]").append(" should give ").append(expected).toString());

    }

}
