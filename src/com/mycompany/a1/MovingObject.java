package com.mycompany.a1;

public class MovingObject extends GameObject {

    public MovingObject() {
        super();
        // Color and size instantiation not necessary because this class is not
        // used directly
    }

    public void move() {
        /*
         * newLocation(x,y) = oldLocation(x,y) + (deltaX, deltaY), where deltaX
         * = cos(θ)*speed, deltaY = sin(θ)*speed and where θ = 90 ˗ direction
         * (90 minus the direction). We use this formula since the direction is
         * specified by a compass angle as mentioned above. In this assignment,
         * we are assuming “time” is fixed at one unit per “tick”.
         */
    }
}
