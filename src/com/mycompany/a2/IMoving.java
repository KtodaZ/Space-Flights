/**
 * 
 */
package com.mycompany.a2;

/**
 * For objects that move
 * @author Kyle Szombathy
 */
public interface IMoving {
    
    public void move();
    
    public int getSpeed();
    
    public void setSpeed(int speed);
    
    public int getDirection();
    
    public void setDirection(int direciton);   
}
