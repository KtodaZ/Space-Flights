package com.mycompany.a1;

import java.util.Random;

import com.codename1.ui.geom.Point2D;

/**
 * Location class that holds location values and includes location utility
 * methods
 * 
 * @author Kyle Szombathy
 */
public class Location extends Point2D {
    private static final double WORLD_MIN_X = 0;
    private static final double WORLD_MIN_Y = 0;
    private static final double WORLD_MAX_X = 1024;
    private static final double WORLD_MAX_Y = 768;

    // ============ Constructors ============
    public Location() {
        super(0, 0); // Create placeholder Point2D object
        setRandomLocation(); // Fill it with random values
    }

    public Location(double x, double y) {
        super(0, 0); // Create placeholder Point2D object
        setLocation(x, y);
    }

    // ============ Getters and Setters ============
    public void setLocation(double x, double y) {
        setX(x);
        setY(y);
        roundLocationToTensPlace();
    }

    @Override
    public void setX(double x) {
        if (isOutOfBoundsX(x)) {
            throw new IllegalArgumentException("Attempted value '" + x + "' is out of bounds.");
        }
        super.setX(x);
        roundLocationToTensPlace();
    }

    @Override
    public void setY(double y) {
        if (isOutOfBoundsY(y)) {
            throw new IllegalArgumentException("Attempted value '" + y + "' is out of bounds.");
        }
        super.setY(y);
        roundLocationToTensPlace();
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("loc=[");
        sb.append(getX());
        sb.append(", ");
        sb.append(getY());
        sb.append("]");
        return sb.toString();
    }

    // ============ Random Methods ============
    // Set a random location for x and y
    public void setRandomLocation() {
        setX(RandomUtils.getRandomDouble(WORLD_MIN_X, WORLD_MAX_X));
        setY(RandomUtils.getRandomDouble(WORLD_MIN_Y, WORLD_MAX_Y));
    }

    /**
     * Gets a location nearby the current location This method works by first
     * choosing a side of the GameObject "square". Then it calculates a random
     * offset on this side.
     * 
     * @param minDistanceAway
     *            The minimum distance away from the current point you want the
     *            new location to be. A good metric for this is the "size" value
     *            of the object you are using.
     * @return A new Location a distance away in a random direction
     */
    public Location getRandomLocationNearby(double minDistanceAway) {
        Location nearbyLocation = new Location(getX(), getY());
        try {
            int sideNumber = RandomUtils.getRandomInt(1, 4); // Get a random
                                                             // direction
            switch (sideNumber) {
                case 1:
                    // Right side
                    nearbyLocation.setX(getX() + minDistanceAway);
                    nearbyLocation.setY(getY() + RandomUtils.getRandomDouble(minDistanceAway * -1, minDistanceAway));
                    break;
                case 2:
                    // Top side
                    nearbyLocation.setX(getX() + RandomUtils.getRandomDouble(minDistanceAway * -1, minDistanceAway));
                    nearbyLocation.setY(getY() + minDistanceAway);
                    break;
                case 3:
                    // Left side
                    nearbyLocation.setX(getX() - minDistanceAway);
                    nearbyLocation.setY(getY() + RandomUtils.getRandomDouble(minDistanceAway * -1, minDistanceAway));
                    break;
                case 4:
                    // Bottom side
                    nearbyLocation.setX(getX() + RandomUtils.getRandomDouble(minDistanceAway * -1, minDistanceAway));
                    nearbyLocation.setY(getY() - minDistanceAway);
                    break;
            }
        } catch (IllegalArgumentException e) {
            // If the value is out of bounds, try again
            return getRandomLocationNearby(minDistanceAway);
        }
        return nearbyLocation;
    }

    // ============ Utility Methods ============
    private void roundLocationToTensPlace() {
        super.setX(roundDoubleTo10sPlace(super.getX()));
        super.setY(roundDoubleTo10sPlace(super.getY()));
    }

    private static double roundDoubleTo10sPlace(double input) {
        return Math.round(input * 10.0) / 10.0;
    }

    private boolean isOutOfBoundsX(double xLocation) {
        return isPastLowerBoundX(xLocation) || isPastUpperBoundX(xLocation);
    }

    private boolean isOutOfBoundsY(double yLocation) {
        return isPastLowerBoundY(yLocation) || isPastUpperBoundY(yLocation);
    }

    private boolean isPastUpperBoundX(double xLocation) {
        return xLocation > WORLD_MAX_X;
    }

    private boolean isPastUpperBoundY(double yLocation) {
        return yLocation > WORLD_MAX_Y;
    }

    private boolean isPastLowerBoundX(double xLocation) {
        return xLocation < WORLD_MIN_X;
    }

    private boolean isPastLowerBoundY(double yLocation) {
        return yLocation < WORLD_MIN_Y;
    }

    // ============ Increment Methods ============
    /**
     * Add or subtract a value from X. If The value goes past the world
     * boundary, bounce back
     */
    public void incrementX(double amountToIncrement) {
        double proposedVal = amountToIncrement + getX();
        double finalVal = incrementOrBounceX(proposedVal);
        setX(finalVal);
    }

    // If value is within bounds, increment, else bounce back
    private double incrementOrBounceX(double proposedVal) {
        if (isPastUpperBoundX(proposedVal)) {
            return bounceBackMaxX(proposedVal);
        } else if (isPastLowerBoundX(proposedVal)) {
            return bounceBackMinX(proposedVal);
        }
        return proposedVal;
    }

    // If value is over max, bounce back the value it went over
    private double bounceBackMaxX(double proposedVal) {
        double valueOver = proposedVal - WORLD_MAX_X;
        return WORLD_MAX_X - valueOver;
    }

    // If value is under min, bounce back the value it went over
    private double bounceBackMinX(double proposedVal) {
        return Math.abs(proposedVal);
    }

    /**
     * Add or subtract a value from Y. If The value goes past the world
     * boundary, bounce back
     */
    public void incrementY(double amountToIncrement) {
        double proposedVal = amountToIncrement + getY();
        double finalVal = incrementOrBounceY(proposedVal);
        setY(finalVal);
    }

    // If value is within bounds, increment, else bounce back
    private double incrementOrBounceY(double proposedVal) {
        if (isPastUpperBoundY(proposedVal)) {
            return bounceBackMaxY(proposedVal);
        } else if (isPastLowerBoundY(proposedVal)) {
            return bounceBackMinY(proposedVal);
        }
        return proposedVal;
    }

    // If value is over max, bounce back the value it went over
    private double bounceBackMaxY(double proposedVal) {
        double valueOver = proposedVal - WORLD_MAX_Y;
        return WORLD_MAX_Y - valueOver;
    }

    // If value is under min, bounce back the value it went over
    private double bounceBackMinY(double proposedVal) {
        return Math.abs(proposedVal);
    }
}
