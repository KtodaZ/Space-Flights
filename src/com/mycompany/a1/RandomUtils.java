package com.mycompany.a1;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class to hold java.util.Random utility methods
 * 
 * @author Kyle Szombathy
 */
public class RandomUtils {
    private static Random rand = new Random();

    /** Get random int between min and max */
    public static int getRandomInt(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than min");
        }
        return rand.nextInt(max + 1 - min) + min;
    }

    /** Get a random double that is rounded to the nearest 10s place */
    public static double getRandomDouble(double rangeMin, double rangeMax) {
        if (rangeMin >= rangeMax) {
            throw new IllegalArgumentException("Max must be greater than min");
        }
        return rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
    }

    /**Gets a Random ArrayList index */
    public static <E> E getRandomArrayListIndex(ArrayList<E> list) {
        return list.get(rand.nextInt(list.size()));
    }

}
