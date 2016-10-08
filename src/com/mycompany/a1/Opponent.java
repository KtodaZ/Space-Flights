/**
 * 
 */
package com.mycompany.a1;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;

/**
 * @author Kyle Szombathy
 *
 */
public abstract class Opponent extends GameObject implements IMoving {
    private static final String NAME = "Opponent";
    private static final int OPPONENT_SIZE_MIN = 20;
    private static final int OPPONENT_SIZE_MAX = 50;
    private static final int OPPONENT_DIRECTION_MIN = 0;
    private static final int OPPONENT_DIRECTION_MAX = 359;
    private static final int OPPONENT_DEFAULT_COLOR = ColorUtil.YELLOW;
    private static final int OPPONENT_DEFAULT_SPEED = 20;
    private static final int OPPONENT_DIRECTION_CHANGE_POSITIVE = 5;
    private static final int OPPONENT_DIRECTION_CHANGE_NEGATIVE = -5;

    private int speed, direction;
    private boolean inSpaceship;

    public Opponent() {
        super(chooseRandomSize(), OPPONENT_DEFAULT_COLOR);
        speed = OPPONENT_DEFAULT_SPEED;
        direction = chooseRandomDirection();
    }

    public Opponent(int color) {
        super(chooseRandomSize(), color);
        speed = OPPONENT_DEFAULT_SPEED;
        direction = chooseRandomDirection();
    }

    public Opponent(int color, int speed) {
        super(chooseRandomSize(), color);
        this.speed = speed;
        direction = chooseRandomDirection();
    }

    public Opponent(int color, int speed, Location location) {
        super(chooseRandomSize(), color, location);
        this.speed = speed;
        direction = chooseRandomDirection();
    }

    public Opponent(int color, int speed, int direction) {
        super(chooseRandomSize(), color);
        this.speed = speed;
        this.direction = direction;
    }

    // ============ Getters/ Setters ============
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if (speed < 0) {
            throw new IllegalArgumentException();
        }
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        if (direction < OPPONENT_DIRECTION_MIN || direction > OPPONENT_DIRECTION_MAX) {
            throw new IllegalArgumentException();
        }
        this.direction = direction;
    }

    @Override
    @Deprecated // Deprecated tag so developer does not accidentally use
    public void setSize(int size) {
        // Size cannot be changed once created, do nothing
    }

    public boolean isCaptured() {
        return inSpaceship;
    }

    public void setCaptured(boolean inSpaceship) {
        this.inSpaceship = inSpaceship;
    }

    @Override 
    public String getName() {
        return NAME;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(" speed=");
        sb.append(getSpeed());
        sb.append(" direction=");
        sb.append(getDirection());
        return sb.toString();
    }

    // ============ Random Methods ============
    private static int chooseRandomSize() {
        return RandomUtils.getRandomInt(OPPONENT_SIZE_MIN, OPPONENT_SIZE_MAX);
    }

    private static int chooseRandomDirection() {
        return RandomUtils.getRandomInt(0, 259);
    }

    // ============ Move Methods ============
    // causes the object to update its location.
    public void move() {
        changeDirection();
        moveToNewLocation();
    }

    // Change direction in a small increment
    private void changeDirection() {
        direction += getRandomDegreesToChange();
    }

    private int getRandomDegreesToChange() {
        return RandomUtils.getRandomInt(OPPONENT_DIRECTION_CHANGE_NEGATIVE, OPPONENT_DIRECTION_CHANGE_POSITIVE);
    }

    // Move to new location
    private void moveToNewLocation() {
        Location newLocation = calculateNewLocation();
        setLocation(newLocation);
    }

    /**
     * newLocation(x,y) = oldLocation(x,y) + (deltaX, deltaY), where deltaX =
     * cos(θ)*speed, deltaY = sin(θ)*speed
     */
    private Location calculateNewLocation() {
        double theta = calculateThetaInRadians();
        double deltaX = calculateDeltaX(theta);
        double deltaY = calculateDeltaY(theta);
        // Increment old location to return a valid new location
        Location oldLocation = getLocation();
        oldLocation.incrementX(deltaX);
        oldLocation.incrementY(deltaY);
        return oldLocation;
    }

    private double calculateThetaInRadians() {
        double direction = (double) this.direction;
        return Math.toRadians(90.0 - direction);
    }

    private double calculateDeltaX(double thetaInRadians) {
        return Math.cos(thetaInRadians) * speed;
    }

    private double calculateDeltaY(double thetaInRadians) {
        return Math.sin(thetaInRadians) * speed;
    }
}
