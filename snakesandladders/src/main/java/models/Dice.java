package models;

import java.util.Random;

public class Dice {

    private int maxNumber;
    private Random random;

    public Dice(int maxNumber) {
        this.maxNumber = maxNumber;
        this.random = new Random();
    }
    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public int roll() {
        return this.random.nextInt(1, maxNumber+1);
    }
}
