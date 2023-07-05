package yatzy.models;

import yatzy.enumerators.DiceNumbersEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author saran
 */
public class Dice {

    private List<Side> sides; //different sides of a dice

    /**
     * Default constructor
     */
    public Dice() {
        super();
        initNumbers();
    }

    /**
     * Initialize a dice with it's selected face
     * @param numberToSelect
     */
    public Dice(DiceNumbersEnum numberToSelect) {
        initNumbers();
        selectSide(numberToSelect);
    }

    /**
     * Initialize numbers for a Dice
     */
    private void initNumbers() {
        sides = new ArrayList<>();
        Arrays.stream(DiceNumbersEnum.values()).forEach( d -> sides.add(new Side(d.getNumber())));
    }


    /**
     * Select a face
     * @param faceNumber
     */
    public void selectSide(DiceNumbersEnum faceNumber) {
        sides.stream().filter(s -> s.getNumber() == faceNumber.getNumber()).findFirst().get().setVisible(true);
    }

    /**
     * Returns the selected face
     * @return selected side or null
     */
    public Side getSelectedSide() {
        return sides.stream().filter(s -> s.isVisible()).findFirst().orElse(null);
    }

    /**
     * Getter
     * @return
     */
    public List<Side> getSides() {
        return sides;
    }

    /**
     * Setter
     * @param sides
     */
    public void setSides(List<Side> sides) {
        this.sides = sides;
    }
}
