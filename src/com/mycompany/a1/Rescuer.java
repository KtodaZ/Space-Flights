/**
 * 
 */
package com.mycompany.a1;

/**
 * @author Kyle Szombathy
 *
 */
public abstract class Rescuer extends GameObject implements IGuided {
    private static final String NAME = "Rescuer";
    private static final int RESCUER_DEFAULT_MOVE_LENGTH_POSITIVE = 10;
    private static final int RESCUER_DEFAULT_MOVE_LENGTH_NEGATIVE = -10;

    // ============ Constructors ============

    public Rescuer(int size, int color) {
        super(size, color);
    }

    // ============ Getters / Setters ============

    @Override 
    public String getName() {
        return NAME;
    }

    // ============ Move methods ============
    public void moveLeft() {
        Location curLocation = getLocation();
        curLocation.incrementX(RESCUER_DEFAULT_MOVE_LENGTH_NEGATIVE);
        setLocation(curLocation);
    }

    public void moveRight() {
        Location curLocation = getLocation();
        curLocation.incrementX(RESCUER_DEFAULT_MOVE_LENGTH_POSITIVE);
        setLocation(curLocation);
    }

    public void moveUp() {
        Location curLocation = getLocation();
        curLocation.incrementY(RESCUER_DEFAULT_MOVE_LENGTH_POSITIVE);
        setLocation(curLocation);
    }

    public void moveDown() {
        Location curLocation = getLocation();
        curLocation.incrementY(RESCUER_DEFAULT_MOVE_LENGTH_NEGATIVE);
        setLocation(curLocation);
    }

    public void jumpToLocation(Location location) {
        setLocation(location);
    }

}
