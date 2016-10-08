package com.mycompany.a1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.codename1.io.Log;

/**
 * Class that holds a collection of game objects and other state variables.
 * 
 * @author Kyle Szombathy
 */
public class GameWorld {
    private static final int ASTRONAUT_INITAL_SIZE = 4;
    private static final int ALIEN_INITIAL_SIZE = 3;
    private static final int ALIEN_SCORE_MODIFIER = -10;
    private static final int ASTRONAUT_SCORE_MODIFIER = 10;
    private static final int MAXIMUM_SCORE = ASTRONAUT_INITAL_SIZE * 10;

    private ArrayList<GameObject> gameObjects;
    private int score, astronautsRescued, aliensSnuckIn;
    boolean exitFlag;

    // ============ Initialization Methods ============
    // Init game objects and parameters
    public void init() {
        initGameObjects();
        initGameParams();
    }

    private void initGameObjects() {
        gameObjects = new ArrayList<GameObject>();
        initRescuers();
        initOpponents();
    }

    private void initRescuers() {
        gameObjects.add(new Spaceship());
    }

    private void initOpponents() {
        fillArrayList(ALIEN_INITIAL_SIZE, Alien.class);
        fillArrayList(ASTRONAUT_INITAL_SIZE, Astronaut.class);
    }

    private <E> void fillArrayList(int numElements, Class opponentType) {
        for (int index = 0; index < numElements; index++) {
            if (opponentType.equals(Alien.class)) {
                gameObjects.add(new Alien());
            } else if (opponentType.equals(Astronaut.class)) {
                gameObjects.add(new Astronaut());
            }
        }
    }

    private void initGameParams() {
        score = 0;
        exitFlag = false;
    }

    // ============ Getters for GameObjects ============
    private Spaceship getFirstSpaceshipInGameObjects() {
        for (int objIndex = 0; objIndex < gameObjects.size(); objIndex++) {
            GameObject gameObject = gameObjects.get(objIndex);
            if (isSpaceShip(gameObject)) {
                return (Spaceship) gameObject;
            }
        }
        return null;
    }

    private void setFirstSpaceshipInGameObjects(Spaceship spaceship) {
        for (int objIndex = 0; objIndex < gameObjects.size(); objIndex++) {
            GameObject gameObject = gameObjects.get(objIndex);
            if (isSpaceShip(gameObject)) {
                gameObjects.set(objIndex, spaceship);
                break;
            }
        }
    }

    private int getNonCapturedAlienCount() {
        int alienCount = 0;
        for (int objIndex = 0; objIndex < gameObjects.size(); objIndex++) {
            GameObject gameObject = gameObjects.get(objIndex);
            if (isValidAlien(gameObject)) {
                alienCount++;
            }
        }
        return alienCount;
    }

    private int getNonCapturedAstronautCount() {
        int astronautCount = 0;
        for (int objIndex = 0; objIndex < gameObjects.size(); objIndex++) {
            GameObject gameObject = gameObjects.get(objIndex);
            if (isValidAstronaut(gameObject)) {
                astronautCount++;
            }
        }
        return astronautCount;
    }

    private Alien getRandomNonCapturedAlien() {
        Random rand = new Random();

        if (getNonCapturedAlienCount() > 0) {
            while (true) {
                int randomIndex = rand.nextInt(gameObjects.size());
                GameObject gameObject = gameObjects.get(randomIndex);
                if (isValidAlien(gameObject)) {
                    return (Alien) gameObject;
                }
            }
        }
        return null;
    }

    private Alien getRandomNonCapturedAlienAndDelete() {
        Random rand = new Random();
        GameObject gameObject;

        if (getNonCapturedAlienCount() > 0) {
            while (true) {
                int randomIndex = rand.nextInt(gameObjects.size());
                gameObject = gameObjects.get(randomIndex);
                if (isValidAlien(gameObject)) {
                    gameObjects.remove(randomIndex);
                    return (Alien) gameObject;
                }
            }
        }
        return null;
    }

    private Astronaut getNonCapturedRandomAstronaut() {
        Random rand = new Random();
        GameObject gameObject;

        if (getNonCapturedAstronautCount() > 0) {
            while (true) {
                int randomIndex = rand.nextInt(gameObjects.size());
                gameObject = gameObjects.get(randomIndex);
                if (isValidAstronaut(gameObject)) {
                    return (Astronaut) gameObject;
                }
            }
        }
        return null;
    }

    private Astronaut getNonCapturedRandomAstronautAndDelete() {
        Random rand = new Random();

        if (getNonCapturedAlienCount() > 0) {
            while (true) {
                int randomIndex = rand.nextInt(gameObjects.size());
                if (isValidAstronaut(gameObjects.get(randomIndex))) {
                    return (Astronaut) gameObjects.remove(randomIndex);
                }
            }
        }
        return null;
    }

    // The following *Valid methods return true if the opponent is not captured
    private boolean isValidAlien(GameObject gameObject) {
        return isAlien(gameObject) && !((Opponent) gameObject).isCaptured();
    }

    private boolean isValidAstronaut(GameObject gameObject) {
        return isAstronaut(gameObject) && !((Opponent) gameObject).isCaptured();
    }

    private boolean isValidOpponent(GameObject gameObject) {
        return isOpponent(gameObject) && ((Opponent) gameObject).isCaptured();
    }

    // InstanceOf Methods
    private boolean isAlien(GameObject gameObject) {
        return gameObject instanceof Alien;
    }

    private boolean isAstronaut(GameObject gameObject) {
        return gameObject instanceof Astronaut;
    }

    private boolean isSpaceShip(GameObject gameObject) {
        return gameObject instanceof Spaceship;
    }

    private boolean isOpponent(GameObject gameObject) {
        return gameObject instanceof Opponent;
    }

    // ============ Player Interactions ============
    /** Transfer the spaceship to a location of a randomly selected alien */
    public void moveSpaceshipToRandomAlien() {
        Location randomAlienLocation = getRandomNonCapturedAlien().getLocation();
        moveSpaceshipToLocation(randomAlienLocation);
        Log.p("Transfered to random alien.");
    }

    /** Transfer the spaceship to a location of a randomly selected astronaut */
    public void moveSpaceshipToRandomAstronaut() {
        Location randomAstronautLocation = getNonCapturedRandomAstronaut().getLocation();
        moveSpaceshipToLocation(randomAstronautLocation);
        Log.p("Transfered to random astonaut.");
    }

    private void moveSpaceshipToLocation(Location location) {
        Spaceship spaceship = getFirstSpaceshipInGameObjects();
        if (spaceship != null)
            spaceship.jumpToLocation(location);
        setFirstSpaceshipInGameObjects(spaceship);
    }

    /** Move the spaceship to the right. */
    public void moveShipRight() {
        Spaceship spaceship = getFirstSpaceshipInGameObjects();
        spaceship.moveRight();
        Log.p("Moved ship to the right.");
    }

    /** Move the spaceship to the left. */
    public void moveShipLeft() {
        Spaceship spaceship = getFirstSpaceshipInGameObjects();
        spaceship.moveLeft();
        Log.p("Moved ship to the left.");
    }

    /** Move the spaceship up. */
    public void moveShipUp() {
        Spaceship spaceship = getFirstSpaceshipInGameObjects();
        spaceship.moveUp();
        Log.p("Moved ship upwards.");
    }

    /** Move the spaceship down. */
    public void moveShipDown() {
        Spaceship spaceship = getFirstSpaceshipInGameObjects();
        spaceship.moveDown();
        Log.p("Moved ship downwards.");
    }

    /** Expand the size of the spaceship door. */
    public void expandSpaceshipDoor() {
        Spaceship spaceship = getFirstSpaceshipInGameObjects();
        spaceship.expandDoor();
        Log.p("Expanded door size.");
    }

    /** Contract (decrease) the size of the spaceship door */
    public void contractSpaceshipDoor() {
        Spaceship spaceship = getFirstSpaceshipInGameObjects();
        spaceship.contractDoor();
        Log.p("Contracted door size.");
    }

    /**
     * Tell the game world that the “game clock” has ticked. All moving objects
     * are told to update their positions according to their current direction
     * and speed.
     */
    public void tick() {
        moveAllMoveableObjects();
        Log.p("Ticked game clock.");
    }

    private void moveAllMoveableObjects() {
        for (int gameObjNum = 0; gameObjNum < gameObjects.size(); gameObjNum++) {
            GameObject gameObject = gameObjects.get(gameObjNum);
            if (gameObject instanceof IMoving) {
                ((IMoving) gameObject).move();
                gameObjects.set(gameObjNum, gameObject);
            }
        }
    }

    /**
     * Open the door and update the score according to the types and conditions
     * of opponents that are let in to the spaceship as described above in rules
     * of play. This causes all of the opponents whose centers are within the
     * boundaries of the bounding square of the door to be removed from the game
     * world. The player accumulates points by rescuing as many astronauts as
     * possible (who fight with the aliens) by opening the door and letting them
     * in to the spaceship.
     * 
     * opening the door and letting in all of the opponents that happen to
     * currently be at the door into the spaceship. Even though we only want to
     * rescue astronauts, nearby aliens might accidentally get in to the
     * spaceship and by changing the size of the door the player tries to avoid
     * this possibility.
     * 
     * Letting in an astronaut that has never been in a fight with an alien
     * worth ten points (if an astronaut has a degraded health, fewer points
     * would be collected as explained below), but letting in an alien deducts
     * ten points. An opponent is considered to be at the door, if its center is
     * contained in the bounding square of the door. If there are opponents at
     * the door, but the player doesn’t tell the spaceship to “open the door”,
     * they continue to move and are not let in.
     */
    public void openDoorAndUpdateScore() {
        captureOpponentsInDoorAndUpdateScore();
        Log.p("Opened Door and Updated Score.");
    }

    private void captureOpponentsInDoorAndUpdateScore() {
        for (int gameObjNum = 0; gameObjNum < gameObjects.size(); gameObjNum++) {
            GameObject gameObject = gameObjects.get(gameObjNum);

            if (isOpponentInDoor(gameObject)) {
                // Capture Opponent
                Opponent opponent = (Opponent) gameObject;
                opponent.setCaptured(true);
                gameObjects.set(gameObjNum, opponent);

                updateScore(opponent);
            }
        }
    }

    private boolean isOpponentInDoor(GameObject opponent) {
        return isOpponent(opponent) && isGameObjectInDoor(opponent);
    }

    private boolean isGameObjectInDoor(GameObject gameObject) {
        Location gameObjectLocation = gameObject.getLocation();
        Spaceship spaceship = getFirstSpaceshipInGameObjects();
        return spaceship.isLocationWithinBoundaries(gameObjectLocation);
    }

    private void updateScore(Opponent opponent) {
        if (isAlien(opponent)) {
            aliensSnuckIn++;
            addToScore(ALIEN_SCORE_MODIFIER);
        } else if (isAstronaut(opponent)) {
            astronautsRescued++;
            addToScore(calculateAstronautScore((Astronaut) opponent));
        }
    }

    private void addToScore(int valueToAdd) {
        score += valueToAdd;
        // Don't let score go over maximum score
        if (score > MAXIMUM_SCORE) {
            score = MAXIMUM_SCORE;
        }
    }

    // Calculate astronaut score based on astronauts health
    private int calculateAstronautScore(Astronaut astronaut) {
        return ASTRONAUT_SCORE_MODIFIER - astronaut.getHealthPointsLost();
    }

    /**
     * PRETEND that a collision occurred between two aliens. This type of
     * collision means that a new alien is generated. If two aliens collide, a
     * new alien is produced, and a new alien object is created in a nearby
     * location to one of the aliens that has entered to the collision.
     * 
     * for now, if the player specifies the ‘w’ command, the program first
     * checks if there are at least two aliens. If so, it randomly picks an
     * alien and produces a new alien in a location that is close to the chosen
     * alien. If the number of aliens in the world is less than two, print an
     * error message without producing a new alien.
     */
    public void collisionAlienAlien() {
        if (getNonCapturedAlienCount() >= 2) {
            spawnNewAlienNextToAlien(getRandomNonCapturedAlien());
        } else {
            Log.p("Error. 2 aliens must be present for a collision to occur.", Log.ERROR);
        }
    }

    private void spawnNewAlienNextToAlien(Alien alien) {
        Location nearbyLocation = alien.getLocation().getRandomLocationNearby(alien.getSize());
        gameObjects.add(new Alien(nearbyLocation));
    }

    /**
     * PRETEND that a collision occurred between an alien and an astronaut. This
     * type of collision means that a fight occurred between an alien and an
     * astronaut.
     * 
     * If an alien and astronaut collide, a “fight” occurs. This causes the
     * astronaut’s health attribute to be decremented, and it’s color to change
     * (such as becoming less red) which in turn would cause the astronaut’s
     * speed to be reduced.
     * 
     * But for now, if the player specifies the ‘f’ command, the program
     * randomly picks an astronaut and decrements its health value, updates its
     * speed, and changes its color. If there are no aliens, print an error
     * message instead.
     */
    public void collisionAlienAstronaut() {
        if (getNonCapturedAlienCount() > 0) {
            collideRandomAlienAndAstronaut();
            Log.p("Alien and Astronaut have collided.");
        } else {
            Log.p("There are no aliens. Cannot collide an Alien and an Astronaut.", Log.ERROR);
        }
    }

    private void collideRandomAlienAndAstronaut() {
        Astronaut randomAstronaut = getNonCapturedRandomAstronaut();
        randomAstronaut = getNonCapturedRandomAstronautAndDelete();
        randomAstronaut.receiveDamage();
        gameObjects.add(randomAstronaut);
    }

    /**
     * Print the points of game state values: (1) current score, (2) number of
     * astronauts rescued, (3) number of aliens sneaked in to the spaceship, and
     * (4) number of astronauts left in the world, (5) number of aliens left in
     * the world.
     */
    public void printScore() {
        StringBuilder sb = new StringBuilder();
        // 1) current score
        sb.append("Score: ");
        sb.append(score);
        sb.append("\n");
        // 2) number of astronauts rescued
        sb.append("Astronauts rescued: ");
        sb.append(astronautsRescued);
        sb.append("\n");
        // 3) number of aliens sneaked in to the spaceship
        sb.append("Aliens snuck in: ");
        sb.append(aliensSnuckIn);
        sb.append("\n");
        // 4) number of astronauts left in the world
        sb.append("Astronauts remaining: ");
        sb.append(getNonCapturedAstronautCount());
        sb.append("\n");
        // 5) number of aliens left in the world.
        sb.append("Aliens remaining: ");
        sb.append(getNonCapturedAlienCount());
        sb.append("\n");

        Log.p(sb.toString());
    }

    /** Print a “map” showing the current world state */
    public void printMap() {
        StringBuilder sb = new StringBuilder();

        for (int objIndex = 0; objIndex < gameObjects.size(); objIndex++) {
            GameObject gameObject = gameObjects.get(objIndex);
            if (isValidOpponent(gameObject)) {
                // Do not list if opponent is captured
                continue;
            }
            sb.append(gameObject.getName());
            sb.append(": ");
            sb.append(gameObject.toString());
            sb.append("\n");
        }

        Log.p(sb.toString());
    }

    /**
     * Exit, by calling the method System.exit(0) to terminate the program.
     * Confirm by prompting for Y/N
     * 
     * @param firstChar
     */
    public void exitGame() {
        System.exit(0);
    }

}
