package com.mycompany.a1;

import java.util.Random;

import com.codename1.ui.geom.Point2D;

/**
 * Location class that holds location values and includes location utility methods
 * @author Kyle Szombathy
 */
public class Location extends Point2D{
    public static final double WORLD_MIN_X = 0;
    public static final double WORLD_MIN_Y = 0;
    public static final double WORLD_MAX_X = 1024;
    public static final double WORLD_MAX_Y = 768;
    
    public Location() {
        super(0,0); // Create placeholder object
        setRandomLocation(); // Fill it with random values
    }

    public Location(double x, double y) {
        super(x, y);
    }
    
    /**
     * Set location giving x and y vals
     * @param x Double value between 0 and WORLD_MAX_X
     * @param y Double value between 0 and WORLD_MAX_Y
     */
    public void setLocation(double x, double y) {
        setX(x);
    }
    
    /** Sets a random location for x and y */
    public void setRandomLocation() {
        setX(getRandomDoubleRounded(WORLD_MIN_X, WORLD_MAX_X));
        setY(getRandomDoubleRounded(WORLD_MIN_Y, WORLD_MAX_Y));
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
    
    @Override
    public double getX() {
        return super.getX();
    }

    @Override
    public double getY() {
        return super.getY();
    }

    @Override
    public void setX(double x) {
        if(x > WORLD_MIN_X && x < WORLD_MAX_X) {
            super.setX(x);
        }
    }

    @Override
    public void setY(double y) {
        if(y > WORLD_MIN_Y && y < WORLD_MAX_X) {
            super.setY(y);
        }
    }
}
