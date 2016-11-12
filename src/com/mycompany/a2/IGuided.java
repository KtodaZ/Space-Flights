/**
 * 
 */
package com.mycompany.a2;

/**
 * For objects that are guided
 * @author Kyle Szombathy
 */
public interface IGuided {
    
    public void moveLeft();
    
    public void moveRight();
    
    public void moveUp();
    
    public void moveDown();
    
    public void jumpToLocation(Location location);
}
