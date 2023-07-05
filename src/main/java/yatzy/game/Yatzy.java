package yatzy.game;

import yatzy.models.Dice;
import yatzy.models.DiceNumbersEnum;

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

    /**
     * Returns the score of YATZY_WINNING_SCORE if same occurrences were found as much as dices
     * @param dices
     * @return YATZY_WINNING_SCORE if yatzy, 0 else
     */
    public static int yatzy(List<Dice> dices) {
        final int YATZY_WINNING_SCORE = 50;
        return getAppearanceOfEachSide(dices).entrySet().stream().anyMatch(faceNumber -> faceNumber.getValue().intValue() == dices.size()) ? YATZY_WINNING_SCORE : 0;
    }

    /**
     * Returns the occurances of DiceNumbersEnum.ONE
     * @param dices
     * @return
     */
    public static int ones(List<Dice> dices) {
        return getAppearanceOf(dices, DiceNumbersEnum.ONE);
    }

    /**
     * Returns the score for the occurances of DiceNumbersEnum.TWO
     * @param dices
     * @return (DiceNumbersEnum.TWO * the occurances of DiceNumbersEnum.TWO)
     */
    public static int twos(List<Dice> dices) {
        return DiceNumbersEnum.TWO.getNumber() * (getAppearanceOf(dices, DiceNumbersEnum.TWO));
    }

    /**
     * Returns the score for the occurances of DiceNumbersEnum.THREE
     * @param dices
     * @return (DiceNumbersEnum.THREE * the occurances of DiceNumbersEnum.THREE)
     */
    public static int threes(List<Dice> dices) {
        return DiceNumbersEnum.THREE.getNumber() * (getAppearanceOf(dices, DiceNumbersEnum.THREE));
    }

    /**
     * Returns the score for the occurances of DiceNumbersEnum.FOUR
     * @param dices
     * @return (DiceNumbersEnum.FOUR * the occurances of DiceNumbersEnum.FOUR)
     */
    public static int fours(List<Dice> dices) {
        return DiceNumbersEnum.FOUR.getNumber() * (getAppearanceOf(dices, DiceNumbersEnum.FOUR));
    }

    /**
     * Returns the score for the occurances of DiceNumbersEnum.FIVE
     * @param dices
     * @return (DiceNumbersEnum.FIVE * the occurances of DiceNumbersEnum.FIVE)
     */
    public static int fives(List<Dice> dices) {
        return DiceNumbersEnum.FIVE.getNumber() * (getAppearanceOf(dices, DiceNumbersEnum.FIVE));
    }

    /**
     * Returns the score for the occurances of DiceNumbersEnum.SIX
     * @param dices
     * @return (DiceNumbersEnum.SIX * the occurances of DiceNumbersEnum.SIX)
     */
    public static int sixes(List<Dice> dices) {
        return DiceNumbersEnum.SIX.getNumber() * (getAppearanceOf(dices, DiceNumbersEnum.SIX));
    }

    /**
     * Returns the sum of the two highest matching dice
     * @param dices
     * @return
     */
    public static int pair(List<Dice> dices) {
        final int pairNumber = 2;
        HashMap<Integer, Integer> pairMap = reverseNumbersWithOccurrences(dices).entrySet().stream().filter(e -> e.getValue() >= pairNumber).limit(1).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (prev, next) -> next, HashMap::new));
        return pairMap.entrySet().stream().map(e -> e.getKey() * pairNumber).reduce(0, Integer::sum);
    }

/** *************************************************************************************************************** **/

    /**
     * Return an hashmap of each number with it's occurances in reversing order of the key
     * @param dices
     * @return (example : input {1, 2, 3, 1, 3}) -> output : {3:2; 2:1; 1:2}
     */
    private static HashMap<Integer, Integer> reverseNumbersWithOccurrences(List<Dice> dices) {
        return getAppearanceOfEachSide(dices).entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    /**
     * Returns the number of occurances of a side
     * @param dices
     * @param numberEnum
     * @return
     */
    private static int getAppearanceOf(List<Dice> dices, DiceNumbersEnum numberEnum) {
        Optional<Map.Entry<Integer, Integer>> result =  getAppearanceOfEachSide(dices).entrySet().stream().filter(entry -> numberEnum.getNumber() == entry.getKey().intValue()).findFirst();
        if (result.isPresent()) {
            return result.get().getValue();
        }
        return 0;
    }

    /**
     * Return a map of each number with its occurances
     * @param dices
     * @return
     */
    private static LinkedHashMap<Integer, Integer> getAppearanceOfEachSide(List<Dice> dices) {
        return dices.stream().collect(Collectors.groupingBy(d -> d.getSelectedSide() != null ? d.getSelectedSide().getNumber() : 0, LinkedHashMap::new,
            Collectors.summingInt(a -> 1)));
    }

    /**
     * Returns the sum of the selected side of the dice
     * @param dices
     * @return example : {1, 2 , 3 , 4 , 5} returns 15
     */
    private static int sumDices(List<Dice> dices) {
        return dices.stream().collect(Collectors.summingInt(e -> e.getSelectedSide().getNumber()));
    }


}
