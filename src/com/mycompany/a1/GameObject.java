package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

/**
 * Abstract class for all objects of type GameObject.
 * 
 * @author Kyle Szombathy
 */
public abstract class GameObject {
    private int size, color;
    private Location location;

    /*
     * Note: the methods here could be abstract but I chose to leave them as is
     * for sake of having default methods.
     */

    public GameObject() {
        // Set default parameters for debugging
        location = new Location();
        color = ColorUtil.YELLOW;
        size = 100;
    }
    
    public GameObject(Location location, int color, int size) {
        this.location = location;
        this.color = color;
        this.size = size;   
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (size > 0) {
            this.size = size;
        }
    }

    public int getColor() {
        return color;
    }

    /**
     * Set color
     * @param color An int value defined by ColorUtil.[ColorName]
     */
    public void setColor(int color) {
        this.color = color;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    
    public void setLocation(double xLocation, double yLocation) {
        location.setLocation(xLocation, yLocation);
    }
}
