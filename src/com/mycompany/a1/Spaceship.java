package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

/**
 * Spaceship class that has a door equal to its size. The door expands and
 * contracts and captures Opponents.
 * 
 * @author Kyle Szombathy
 *
 */
public class Spaceship extends Rescuer {
    private static final String NAME = "Spaceship";
    private static final int SPACESHIP_DOOR_MIN = 50;
    public static final int SPACESHIP_DOOR_MAX = 1024;
    private static final int SPACESHIP_DEFAULT_SIZE = 100;
    private static final int SPACESHIP_DEFAULT_COLOR = ColorUtil.WHITE;
    private static final int SPACESHIP_DEFAULT_DOOR_EXPAND = 20;
    private static final int SPACESHIP_DEFAULT_DOOR_CONTRACT = -20;

    // ============ Constructors ============
    public Spaceship() {
        super(SPACESHIP_DEFAULT_SIZE, SPACESHIP_DEFAULT_COLOR);
    }

    public Spaceship(int doorSize) {
        super(doorSize, SPACESHIP_DEFAULT_COLOR);
    }

    public Spaceship(int doorSize, int color) {
        super(doorSize, color);
    }

    // ============ Getters/Setters ============
    @Override 
    public String getName() {
        return NAME;
    }

    @Override // Override GameObject setSize in order to apply min/max values
    public void setSize(int doorSize) {
        if (isDoorSizeOutOfBounds(doorSize)) {
            throw new IllegalArgumentException();
        }
        super.setSize(doorSize);
    }

    @Override // Override GameObject setColor
    @Deprecated // Deprecated so developer does not use
    public void setColor(int color) {
        // Color never changes. Do nothing
    }

    // ============ Expand/Contract Door ============
    public void expandDoor() {
        expandContractDoor(SPACESHIP_DEFAULT_DOOR_EXPAND);
    }

    public void expandDoor(int expandBy) {
        expandContractDoor(expandBy);
    }

    public void contractDoor() {
        expandContractDoor(SPACESHIP_DEFAULT_DOOR_CONTRACT);
    }

    public void contractDoor(int contractBy) {
        if (contractBy > 0) {
            contractBy = contractBy * -1;
        }
        expandContractDoor(contractBy);
    }

    private void expandContractDoor(int expandContractBy) {
        int proposedSize = expandContractBy + getSize();
        if (!isDoorSizeOutOfBounds(proposedSize)) {
            super.setSize(proposedSize);
        }
    }

    // ============ Utility ============
    private static boolean isDoorSizeOutOfBounds(int doorSize) {
        return isDoorSizePastLowerBound(doorSize) || isDoorSizePastUpperBound(doorSize);
    }

    private static boolean isDoorSizePastLowerBound(int doorSize) {
        return doorSize < SPACESHIP_DOOR_MIN;
    }

    private static boolean isDoorSizePastUpperBound(int doorSize) {
        return doorSize > SPACESHIP_DOOR_MAX;
    }

}
