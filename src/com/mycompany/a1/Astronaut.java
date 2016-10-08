/**
 * 
 */
package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

/**
 * Astronaut that has a health value, color, and speed that can degrade over time.
 * @author Kyle Szombathy
 */
public class Astronaut extends Opponent {
    private static final String NAME = "Astronaut"; 
    private static final int ASTRONAUT_START_HEALTH = 5;
    private static final int ASTRONAUT_SPEED_CONSTANT = 1;
    private static final int ASTRONAUT_DEFAULT_COLOR = ColorUtil.BLUE;
    private int health;
    
    // ============ Constructors ============
    public Astronaut() {
        super(ASTRONAUT_DEFAULT_COLOR);
        health = ASTRONAUT_START_HEALTH;
        updateSpeed(); // Set speed
    }

    public Astronaut(int color, int speed, int direction) {
        super(color, speed, direction);
        health = ASTRONAUT_START_HEALTH;
    }
    
    // ============ Getters ============
    public String getName() {
        return NAME;
    }
    
    public int getHealth() {
        return health;
    }

    public int getHealthPointsLost() {
        return ASTRONAUT_START_HEALTH - health;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(" health=");
        sb.append(getHealth());
        return sb.toString();
    }

    // ============ Fight Methods ============
    public void receiveDamage() {
        degradeHealth();
        updateSpeed();
        fadeColor();
    }

    private void degradeHealth() {
        health--;
    }

    private void updateSpeed() {
        setSpeed(health * ASTRONAUT_SPEED_CONSTANT);
    }
    
    private void fadeColor() {
        setColor(ColorUtil.rgb(
                ColorUtil.red(getColor()), 
                ColorUtil.green(getColor()), 
                ColorUtil.blue(getColor() - 25)));
    }
}
