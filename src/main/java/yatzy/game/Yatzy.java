package yatzy.game;

import yatzy.models.Dice;
import yatzy.enumerators.DiceNumbersEnum;

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
        HashMap<Integer, Integer> pairMap = findPairsInReversingOrder(dices, 1);
        final int pairNumber = 2;
        return pairMap.entrySet().stream().map(e -> e.getKey() * pairNumber).reduce(0, Integer::sum);
    }


    /**
     * Returns the sum of all numbers concerned by the two pairs
     * @param dices
     * @return
     */
    public static int twoPairs(List<Dice> dices) {
        HashMap<Integer, Integer> pair = findPairsInReversingOrder(dices, 2);
        final int pairNumber = 2;
        return pair.entrySet().stream().map(e -> e.getKey() * pairNumber).reduce(0, Integer::sum);
    }

    /**
     * Returns the score for three occurances of a number if exists
     * @param dices
     * @return
     */
    public static int threeOfAKind(List<Dice> dices) {
        final int numberOfOccurences = 3;
        Optional<Map.Entry<Integer, Integer>> entry = findANumberOfAKind(dices, numberOfOccurences);
        if (entry.isPresent()) {
            return entry.get().getKey() * numberOfOccurences;
        }
        return 0;
    }

    /**
     * Returns the score for three occurances of a number if exists
     * @param dices
     * @return
     */
    public static int fourOfAKind(List<Dice> dices) {
        final int numberOfOccurences = 4;
        Optional<Map.Entry<Integer, Integer>> entry = findANumberOfAKind(dices, numberOfOccurences);
        if (entry.isPresent()) {
            return entry.get().getKey() * numberOfOccurences;
        }
        return 0;
    }

    /**
     * Returns the sum of Dices if small stright (1, 2, 3, 4, 5)
     * @param dices
     * @return
     */
    public static int smallStright(List<Dice> dices) {
        final int startingNumber = 1;
        if (strightExists(dices, startingNumber)) {
            return sumDices(dices);
        }
        return 0;
    }

    /**
     * Returns the sum of Dices if large stright (2, 3, 4, 5, 6)
     * @param dices
     * @return
     */
    public static int largeStright(List<Dice> dices) {
        final int startingNumber = 2;
        if (strightExists(dices, startingNumber)) {
            return sumDices(dices);
        }
        return 0;
    }

    /**
     * Returns the sum of dices if 2 of a kind and 3 of a kind (for example 1, 1, 2, 2, 2 returns : 8)
     * @param dices
     * @return
     */
    public static int fullHouse(List<Dice> dices) {
        final int[] numberOfAKind = new int[]{2, 3};
        final int elementNeededForFullHouse = numberOfAKind.length;
        HashMap<Integer, Integer> appearancesBySide = getAppearanceOfEachSide(dices);
        if(appearancesBySide.entrySet().stream().filter(e -> e.getValue() >= numberOfAKind[0]).count() == elementNeededForFullHouse
            && appearancesBySide.entrySet().stream().anyMatch(e -> e.getValue() >= numberOfAKind[1])) {
            return sumDices(dices);
        }
        return 0;
    }

/** *************************************************************************************************************** **/

    /**
     * Returns
     * @param dices
     * @return
     */
    private static boolean strightExists(List<Dice> dices, int startingNumber) {
        final int[] cpt = {startingNumber};
        return getAppearanceOfEachSide(dices).entrySet().stream().sorted(Map.Entry.comparingByKey()).allMatch(e -> e.getKey() == cpt[0]++);
    }

    /**
     * Returns the number which is repeated "numberOfOccurences" times
     * @param dices
     * @param numberOfOccurences
     * @return example : {1, 2, 1, 1, 5} with numberOfOccurences = 3 returns {1 : 3}
     */
    private static Optional<Map.Entry<Integer, Integer>> findANumberOfAKind(List<Dice> dices, int numberOfOccurences) {
        return getAppearanceOfEachSide(dices).entrySet().stream().filter(e -> e.getValue() >= numberOfOccurences).findFirst();
    }

    /**
     * Return the number of pairs asked in the parameter
     * @param dices
     * @param nbrOfPairs
     * @return HashMap<Integer,Integer> of pairs found (empty one if not)
     */
    private static HashMap<Integer,Integer> findPairsInReversingOrder(List<Dice> dices, int nbrOfPairs) {
        final int pair = 2;
        HashMap<Integer,Integer> pairs = reverseNumbersWithOccurrences(dices).entrySet().stream().filter(e -> e.getValue() >= pair).limit(nbrOfPairs).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (prev, next) -> next, HashMap::new));
        if (pairs.size() == nbrOfPairs) {
            return pairs;
        }
        pairs = new HashMap<>();
        return pairs;
    }

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
