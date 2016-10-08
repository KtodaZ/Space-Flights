package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

/**
 * Opponent type Alien.
 * 
 * @author Kyle Szombathy
 */
public class Alien extends Opponent { 
    private static final String NAME = "Alien";
    private static final int ALIEN_DEFAULT_COLOR = ColorUtil.GREEN;
    private static final int ALIEN_SPEED_CONSTANT = 1;

    // ============ Constructors ============

    public Alien() {
        super(ALIEN_DEFAULT_COLOR, calculateSpeed());
    }

    public Alien(Location location) {
        super(ALIEN_DEFAULT_COLOR, calculateSpeed(), location);
    }

    // ============ Getters / Setters ============
    @Override 
    public String getName() {
        return NAME;
    }

    @Override // Override GameObject setColor
    @Deprecated // Deprecated so developer does not accidentally use
    public void setColor(int color) {
        // Color never changes. Do nothing
    }

    @Override
    @Deprecated // Deprecated so developer does not accidentally use
    public void setSpeed(int speed) {
        // Speed never changes. Do nothing
    }

    // ============ Other ============

    private static int calculateSpeed() {
        return 5 * ALIEN_SPEED_CONSTANT;
    }
}
