package com.mycompany.a2;

import java.io.UnsupportedEncodingException;

import com.codename1.charts.util.ColorUtil;

/**
 * Abstract class for all objects of type GameObject.
 * 
 * @author Kyle Szombathy
 */
public abstract class GameObject {
    private static final String NAME = "GameObject";
    private static final int GAMEOBJECT_DEFAULT_COLOR = ColorUtil.YELLOW;
    private static final int GAMEOBJECT_DEFAULT_SIZE = 100;
    private int size, color;
    private Location objCenter;

    // ============ Constructors ============
    public GameObject() {
        // Set default parameters for debugging
        objCenter = new Location(); // Set random location
        color = GAMEOBJECT_DEFAULT_COLOR;
        size = GAMEOBJECT_DEFAULT_SIZE;
    }

    public GameObject(int size, int color) {
        objCenter = new Location(); // Set random location
        this.size = size;
        this.color = color;
    }

    public GameObject(int size, int color, Location location) {
        objCenter = location;
        this.size = size;
        this.color = color;
    }

    public GameObject(Location location, int size, int color) {
        this.objCenter = location;
        this.size = size;
        this.color = color;
    }

    // ============ Getters / Setters ============
    public String getName() {
        return NAME;
    }

    public int getSize() {
        return size;
    }

    /**
     * Set the size of the object
     * @param size The size you want the object to be.
     * @throws UnsupportedEncodingException if the operation is not supported
     */
    public void setSize(int size) {
        if (size > 0) {
            this.size = size;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int getColor() {
        return color;
    }

    /**
     * Set the color of the object. Use ColorUtil.[color]
     * 
     * @param color the color of the object. Use ColorUtil.[color]
     * @throws UnsupportedEncodingException if the operation is not supported
     */
    public void setColor(int color) {
        this.color = color;
    }

    public Location getLocation() {
        return objCenter;
    }

    public void setLocation(Location location) {
        this.objCenter = location;
    }

    public void setLocation(double xLocation, double yLocation) {
        objCenter.setLocation(xLocation, yLocation);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Location
        sb.append(getLocation().toString());
        // Color
        sb.append(" color:[");
        sb.append(ColorUtil.red(color)).append(", ");
        sb.append(ColorUtil.green(color)).append(", ");
        sb.append(ColorUtil.blue(color)).append("] ");
        // Size
        sb.append("size=").append(getSize());
        return sb.toString();
    }

    // ============ Utilities ============
    /** Check if a location is within the bounds of this object */
    public boolean isLocationWithinBoundaries(Location location) {
        double sizeDbl = (double) this.size;
        double rightBoundary, leftBoundary, topBoundary, bottomBoundary;
        rightBoundary = objCenter.getX() + sizeDbl;
        leftBoundary = objCenter.getX() - sizeDbl;
        topBoundary = objCenter.getY() + sizeDbl;
        bottomBoundary = objCenter.getY() - sizeDbl;

        if (location.getX() < rightBoundary && location.getX() > leftBoundary && location.getY() < topBoundary
                && location.getY() > bottomBoundary) {
            return true;
        }
        return false;
    }
}
