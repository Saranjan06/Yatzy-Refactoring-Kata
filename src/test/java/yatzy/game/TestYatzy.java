package yatzy.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import yatzy.models.Dice;
import yatzy.models.DiceNumbersEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author saran
 */
public class TestYatzy {

    @Test
    @DisplayName("Testing chance()...")
    public void testChance() {
        int[] selectedNumbers = new int [] {2, 3, 4, 5, 1};
        List<Dice> dices = GameFactory.initDices(selectedNumbers);
        int output = Yatzy.chance(dices);
        int expected = 15;
        assertEquals(expected, output, new StringBuilder().append(Arrays.toString(selectedNumbers)).append(" should give ").append(expected).toString());
    }

}
