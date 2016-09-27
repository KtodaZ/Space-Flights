package com.mycompany.a1;

import java.util.Random;

/**
 * Location class that holds location values and includes location utility methods
 * @author Kyle Szombathy
 */
public class Location {
    public static final double WORLD_MIN_X = 0;
    public static final double WORLD_MIN_Y = 0;
    public static final double WORLD_MAX_X = 1024;
    public static final double WORLD_MAX_Y = 768;
    private double xLocation;
    private double yLocation;
    
    public Location() {
        setRandomLocation();
    }

    public Location(double xLocation, double yLocation) {
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }
    
    /**
     * Set location giving x and y vals
     * @param xLocation Double value between 0 and WORLD_MAX_X
     * @param yLocation Double value between 0 and WORLD_MAX_Y
     */
    public void setLocation(double xLocation, double yLocation) {
        setXLocation(xLocation);
        setYLocation(yLocation);
    }
    
    /** Sets a random location for x and y */
    public void setRandomLocation() {
        xLocation = getRandomDoubleRounded(WORLD_MIN_X, WORLD_MAX_X);
        yLocation = getRandomDoubleRounded(WORLD_MIN_Y, WORLD_MAX_Y);
    }
    
    /**
     * Get a random double that is rounded to the nearest 10s place
     * @param rangeMin The min value
     * @param rangeMax The max value
     * @return a random double rounded to the 10s place between min and max values
     */
    private double getRandomDoubleRounded(double rangeMin, double rangeMax) {
        Random rand = new Random();
        double randomDouble = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
        return roundDoubleTo10sPlace(randomDouble);
    }
    
    /**
     * Round a double to the nearest 10s place
     * @param input Input double ex: 33.33
     * @return Rounded input value ex: 33.3
     */
    private double roundDoubleTo10sPlace(double input) {
        return Math.round(input*10.0)/10.0;
    }

    public double getXLocation() {
        return xLocation;
    }

    public void setXLocation(double xLocation) {
        if(xLocation > WORLD_MIN_X && xLocation < WORLD_MAX_X) {
            this.xLocation = xLocation;
        }
    }

    public double getYLocation() {
        return yLocation;
    }

    public void setYLocation(double yLocation) {
        if(yLocation > WORLD_MIN_Y && yLocation < WORLD_MAX_X) {
            this.yLocation = yLocation;
        }
    }
}
