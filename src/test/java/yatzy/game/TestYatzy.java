package yatzy.game;

import org.junit.jupiter.api.*;
import yatzy.models.Dice;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author saran
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestYatzy {

    @Test
    @DisplayName("Testing chance()...")
    @Order(1)
    public void testChance() {
        //first test
        int[] selectedNumbers = new int [] {2, 3, 4, 5, 1};
        List<Dice> dices = GameFactory.initDices(selectedNumbers);
        int output = Yatzy.chance(dices);
        int expected = 15;
        assertEquals(expected, output, errorMessage(selectedNumbers, expected));

        //second test
        selectedNumbers = new int[] {3, 3, 4, 5, 1};
        dices = GameFactory.initDices(selectedNumbers);
        output = Yatzy.chance(dices);
        expected = 16;
        assertEquals(expected, output, errorMessage(selectedNumbers, expected));
    }

    @Test
    @DisplayName("Testing yatzy()...")
    @Order(2)
    public void  testYatzy() {
        //first test
        int[] selectedNumbers = {4, 4, 4, 4, 4};
        int output = Yatzy.yatzy(GameFactory.initDices(selectedNumbers));
        int expected = 50;
        assertEquals(expected, output, errorMessage(selectedNumbers, expected));

        //second test
        selectedNumbers = new int[] {6, 6, 6, 6, 6};
        output = Yatzy.yatzy(GameFactory.initDices(selectedNumbers));
        expected = 50;
        assertEquals(expected, output, errorMessage(selectedNumbers, expected));

        //third test
        selectedNumbers = new int[] {6, 6, 6, 6, 3};
        expected = 0;
        output = Yatzy.yatzy(GameFactory.initDices(selectedNumbers));
        assertEquals(expected, output, errorMessage(selectedNumbers, expected));
    }

    @Test
    @DisplayName("Testing ones()...")
    @Order(3)
    public void testOnes() {
        //first test
        int[] selectedNumbers = {1, 2, 3, 4, 5};
        int output = Yatzy.ones(GameFactory.initDices(selectedNumbers));
        int expected = 1;
        assertEquals(expected, output, errorMessage(selectedNumbers, expected));

        //second test
        selectedNumbers = new int[]{1, 2, 1, 4, 5};
        output = Yatzy.ones(GameFactory.initDices(selectedNumbers));
        expected = 2;
        assertEquals(expected, output, errorMessage(selectedNumbers, expected));

        //third test
        selectedNumbers = new int[]{6, 2, 2, 4, 5};
        output = Yatzy.ones(GameFactory.initDices(selectedNumbers));
        expected = 0;
        assertEquals(expected, output, errorMessage(selectedNumbers, expected));

        //forth test
        selectedNumbers = new int[]{1, 2, 1, 1, 1};
        output = Yatzy.ones(GameFactory.initDices(selectedNumbers));
        expected = 4;
        assertEquals(expected, output, errorMessage(selectedNumbers, expected));
    }

    @Test
    @DisplayName("Testing twos()...")
    @Order(4)
    public void testTwos() {
        //first test
        int[] selectedNumbers = {1, 2, 3, 2, 6};
        int output = Yatzy.twos(GameFactory.initDices(selectedNumbers));
        int expected = 4;
        assertEquals(expected, output, errorMessage(selectedNumbers, expected));

        //second test
        selectedNumbers = new int[]{2, 2, 2, 2, 2};
        output = Yatzy.twos(GameFactory.initDices(selectedNumbers));
        expected = 10;
        assertEquals(expected, output, errorMessage(selectedNumbers, expected));
    }

    @Test
    @DisplayName("Testing threes()...")
    @Order(5)
    public void testThrees() {
        //first test
        int[] selectedNumbers = {1, 2, 3, 2, 3};
        int output = Yatzy.threes(GameFactory.initDices(selectedNumbers));
        int expected = 6;
        assertEquals(expected, output, errorMessage(selectedNumbers, expected));

        //second test
        selectedNumbers = new int[]{2, 3, 3, 3, 3};
        output = Yatzy.threes(GameFactory.initDices(selectedNumbers));
        expected = 12;
        assertEquals(expected, output, errorMessage(selectedNumbers, expected));
    }

    @Test
    @DisplayName("Testing fours()...")
    @Order(6)
    public void testFours() {
        //first test
        int[] selectedNumbers = {4, 4, 4, 5, 5};
        int output = Yatzy.fours(GameFactory.initDices(selectedNumbers));
        int expected = 12;
        assertEquals(expected, output, errorMessage(selectedNumbers, expected));

        //second test
        selectedNumbers = new int[]{4, 4, 5, 5, 5};
        output = Yatzy.fours(GameFactory.initDices(selectedNumbers));
        expected = 8;
        assertEquals(expected, output, errorMessage(selectedNumbers, expected));

        //third test
        selectedNumbers = new int[]{4, 5, 5, 5, 5};
        output = Yatzy.fours(GameFactory.initDices(selectedNumbers));
        expected = 4;
        assertEquals(expected, output, errorMessage(selectedNumbers, expected));
    }

    @Test
    @DisplayName("Testing fives()...")
    @Order(7)
    public void testFives() {
        //first test
        int[] selectedNumbers = {4, 4, 4, 5, 5};
        int output = Yatzy.fives(GameFactory.initDices(selectedNumbers));
        int expected = 10;
        assertEquals(expected, output, errorMessage(selectedNumbers, expected));

        //second test
        selectedNumbers = new int[]{4, 4, 5, 5, 5};
        output = Yatzy.fives(GameFactory.initDices(selectedNumbers));
        expected = 15;
        assertEquals(expected, output, errorMessage(selectedNumbers, expected));

        //third test
        selectedNumbers = new int[]{4, 5, 5, 5, 5};
        output = Yatzy.fives(GameFactory.initDices(selectedNumbers));
        expected = 20;
        assertEquals(expected, output, errorMessage(selectedNumbers, expected));
    }

    @Test
    @DisplayName("Testing sixes()...")
    @Order(8)
    public void testSixes() {
        //first test
        int[] selectedNumbers = {4, 4, 4, 5, 5};
        int output = Yatzy.sixes(GameFactory.initDices(selectedNumbers));
        int expected = 0;
        assertEquals(expected, output, errorMessage(selectedNumbers, expected));

        //second test
        selectedNumbers = new int[]{4, 4, 6, 5, 5};
        output = Yatzy.sixes(GameFactory.initDices(selectedNumbers));
        expected = 6;
        assertEquals(expected, output, errorMessage(selectedNumbers, expected));

        //third test
        selectedNumbers = new int[]{6, 5, 6, 6, 5};
        output = Yatzy.sixes(GameFactory.initDices(selectedNumbers));
        expected = 18;
        assertEquals(expected, output, errorMessage(selectedNumbers, expected));
    }

    /**
     * Returns the formatted message
     * @param input : number of dices
     * @param expectedOutPut : the score we are expecting
     * @return
     */
    private static String errorMessage(int[] input, int expectedOutPut) {
        return new StringBuilder().append(Arrays.toString(input)).append(" should give ").append(expectedOutPut).toString();
    }

}
