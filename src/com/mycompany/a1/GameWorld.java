package com.mycompany.a1;

public class GameWorld {
    
    public void init() {
        // TODO: code here to create the initial game objects/layout
    }

    // TODO: additional methods here to manipulate world objects and related
    // game state data

    /** Transfer the spaceship to a location of a randomly selected alien */
    public void transferToAlien() {
        // TODO Auto-generated method stub

    }

    /** Transfer the spaceship to a location of a randomly selected astronaut */
    public void transferToAstronaut() {
        // TODO Auto-generated method stub

    }

    /** Move the spaceship to the right. */
    public void moveShipRight() {
        // TODO Auto-generated method stub

    }

    /** Move the spaceship to the left. */
    public void moveShipLeft() {
        // TODO Auto-generated method stub

    }

    /** Move the spaceship up. */
    public void moveShipUp() {
        // TODO Auto-generated method stub

    }

    /** Move the spaceship down. */
    public void moveShipDown() {
        // TODO Auto-generated method stub

    }

    /** Expand the size of the spaceship door. */
    public void expandDoor() {
        // TODO Auto-generated method stub

    }

    /** Contract (decrease) the size of the spaceship door */
    public void contractDoor() {
        // TODO Auto-generated method stub

    }

    /**
     * Tell the game world that the “game clock” has ticked. All moving objects
     * are told to update their positions according to their current direction
     * and speed.
     */
    public void tickGameClock() {
        // TODO Auto-generated method stub

    }

    /**
     * Open the door and update the score according to the types and conditions
     * of opponents that are let in to the spaceship as described above in rules
     * of play. This causes all of the opponents whose centers are within the
     * boundaries of the bounding square of the door to be removed from the game
     * world.
     */
    public void openDoorAndUpdateScore() {
        openDoor();
        updateScore();
    }
    
    private void openDoor() {
        // TODO Auto-generated method stub

    }

    private void updateScore() {
        // TODO Auto-generated method stub

    }

    /**
     * PRETEND that a collision occurred between two aliens. This type of
     * collision means that a new alien is generated.
     */
    public void collisionAlienAlien() {
        // TODO Auto-generated method stub

    }

    /**
     * PRETEND that a collision occurred between an alien and an astronaut. This
     * type of collision means that a fight occurred between an alien and an
     * astronaut.
     */
    public void collisionAlienAstronaut() {
        // TODO Auto-generated method stub

    }

    /**
     * Print the points of game state values: (1) current score, (2) number of
     * astronauts rescued, (3) number of aliens sneaked in to the spaceship, and
     * (4) number of astronauts left in the world, (5) number of aliens left in
     * the world.
     */
    public void printScore() {
        // TODO Auto-generated method stub

    }

    /**Print a “map” showing the current world state*/
    public void printMap() {
        // TODO Auto-generated method stub

    }

    /**
     * Exit, by calling the method System.exit(0) to terminate the program.
     * Confirm by prompting for Y/N
     * @param firstChar
     */
    public void exitGame(char firstChar) {
        // TODO Auto-generated method stub

    }
}
