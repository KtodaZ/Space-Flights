package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;
import com.codename1.io.Log;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

/**
 * Class that holds a collection of game objects and other state variables.
 * 
 * @author Kyle Szombathy
 */
public class GameWorld extends Observable {
    // Class information
    private static final String AUTHOR = "Kyle Szombathy";
    private static final String COURSE_NAME = "CSC 133";
    private static final int ASSIGNMENT_NUMBER = 2;
    // final values
    private static final int ASTRONAUT_INITAL_SIZE = 4;
    private static final int ALIEN_INITIAL_SIZE = 3;
    private static final int ALIEN_SCORE_MODIFIER = -10;
    private static final int ASTRONAUT_SCORE_MODIFIER = 10;
    private static final int MAXIMUM_SCORE = ASTRONAUT_INITAL_SIZE * 10;
    // Map values
    private int mapWidth, mapHeight;
    // Collections and values
    private GameObjectCollection gameObjects;
    private int score, astronautsRescued, aliensSnuckIn;
    private boolean sound;

    // ============ Initialization Methods ============
    public GameWorld(int mapWidth, int mapHeight) {
        super();
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        init();
    }
    
    // Init game objects and parameters
    public void init() {
        initGameObjects();
        initGameParams();
        updateObservers();
    }

    private void initGameObjects() {
        gameObjects = new GameObjectCollection();
        initRescuers();
        initOpponents();
    }

    private void initRescuers() {
        gameObjects.add(Spaceship.getSpaceship());
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
        sound = true;
    }

    // ============ Getters for GameObjects ============
    
    // public methods
    public int getScore() {
        return score;
    }

    public int getAstronautsRescued() {
        return astronautsRescued;
    }

    public int getAliensSnuckIn() {
        return aliensSnuckIn;
    }

    public boolean getSound() {
        return sound;
    }
    
    public String getSoundString() {
        if (sound) return "ON";
        else return "OFF";
    }
    
    public int getNonCapturedAstronautCount() {
        return gameObjects.getNonCapturedAstronautCount();
    }

    public int getNonCapturedAlienCount() {
        return gameObjects.getNonCapturedAlienCount();
    }
   

    // ============ Keyboard Interactions ============
    /** Transfer the spaceship to a location of a randomly selected alien */
    public void moveSpaceshipToRandomAlien() {
        Location randomAlienLocation = gameObjects.getRandomNonCapturedAlien().getLocation();
        moveSpaceshipToLocation(randomAlienLocation);
        Log.p("Transfered to random alien.");
    }

    /** Transfer the spaceship to a location of a randomly selected astronaut */
    public void moveSpaceshipToRandomAstronaut() {
        Location randomAstronautLocation = gameObjects.getNonCapturedRandomAstronaut().getLocation();
        moveSpaceshipToLocation(randomAstronautLocation);
        Log.p("Transfered to random astonaut.");
    }

    private void moveSpaceshipToLocation(Location location) {
        Spaceship spaceship = gameObjects.getSpaceship();
        if (spaceship != null)
            spaceship.jumpToLocation(location);
        gameObjects.setSpaceship(spaceship);
        updateObservers();
    }

    /** Move the spaceship to the right. */
    public void moveShipRight() {
        Spaceship spaceship = gameObjects.getSpaceship();
        spaceship.moveRight();
        updateObservers();
        Log.p("Moved ship to the right.");
    }

    /** Move the spaceship to the left. */
    public void moveShipLeft() {
        Spaceship spaceship = gameObjects.getSpaceship();
        spaceship.moveLeft();
        updateObservers();
        Log.p("Moved ship to the left.");
    }

    /** Move the spaceship up. */
    public void moveShipUp() {
        Spaceship spaceship = gameObjects.getSpaceship();
        spaceship.moveUp();
        updateObservers();
        Log.p("Moved ship upwards.");
    }

    /** Move the spaceship down. */
    public void moveShipDown() {
        Spaceship spaceship = gameObjects.getSpaceship();
        spaceship.moveDown();
        updateObservers();
        Log.p("Moved ship downwards.");
    }

    /**
     * Expand the size of the spaceship door.
     */
    public void expandSpaceshipDoor() {
        Spaceship spaceship = gameObjects.getSpaceship();
        spaceship.expandDoor();
        updateObservers();
        Log.p("Expanded door size.");
    }

    /**
     * Contract (decrease) the size of the spaceship door
     */
    public void contractSpaceshipDoor() {
        Spaceship spaceship = gameObjects.getSpaceship();
        spaceship.contractDoor();
        updateObservers();
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
        printMap();
    }

    private void moveAllMoveableObjects() {
        IIterator<GameObject> iterator = gameObjects.getIterator();
        while(iterator.hasNext()) {
            GameObject gameObject = iterator.next();
            if (gameObject instanceof IMoving) {
                ((IMoving) gameObject).move();
            }
        }
        updateObservers();
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
        IIterator<GameObject> iterator = gameObjects.getIterator();
        while(iterator.hasNext()) {
            GameObject gameObject = iterator.next();
            
            if (isValidOpponentInDoor(gameObject)) {
                // Capture Opponent
                Opponent opponent = (Opponent) gameObject;
                opponent.setCaptured(true);
                updateScore(opponent);
            }
        }
    }

    private boolean isValidOpponentInDoor(GameObject opponent) {
        return GameObjectCollection.isValidOpponent(opponent) 
                && isGameObjectInDoor(opponent);
    }

    private boolean isGameObjectInDoor(GameObject gameObject) {
        Location gameObjectLocation = gameObject.getLocation();
        Spaceship spaceship = gameObjects.getSpaceship();
        return spaceship.isLocationWithinBoundaries(gameObjectLocation);
    }

    private void updateScore(Opponent opponent) {
        if (GameObjectCollection.isAlien(opponent)) {
            aliensSnuckIn++;
            addToScore(ALIEN_SCORE_MODIFIER);
        } else if (GameObjectCollection.isAstronaut(opponent)) {
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
        updateObservers();
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
        if (gameObjects.getNonCapturedAlienCount() >= 2) {
            spawnNewAlienNextToAlien(gameObjects.getRandomNonCapturedAlien());
        } else {
            Log.p("Error. 2 aliens must be present for a collision to occur.", Log.ERROR);
        }
    }

    private void spawnNewAlienNextToAlien(Alien alien) {
        Location nearbyLocation = alien.getLocation().getRandomLocationNearby(alien.getSize());
        gameObjects.add(new Alien(nearbyLocation));
        updateObservers();
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
        if (gameObjects.getNonCapturedAlienCount() > 0) {
            collideRandomAlienAndAstronaut();
            Log.p("Alien and Astronaut have collided.");
        } else {
            Log.p("There are no aliens. Cannot collide an Alien and an Astronaut.", Log.ERROR);
        }
    }

    private void collideRandomAlienAndAstronaut() {
        Astronaut randomAstronaut = gameObjects.getNonCapturedRandomAstronaut();
        randomAstronaut = gameObjects.removeNonCapturedRandomAstronaut();
        randomAstronaut.receiveDamage();
        gameObjects.add(randomAstronaut);
        updateObservers();
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
        sb.append("Score: ").append(score).append("\n");
        // 2) number of astronauts rescued
        sb.append("Astronauts rescued: ").append(astronautsRescued).append("\n");
        // 3) number of aliens sneaked in to the spaceship
        sb.append("Aliens snuck in: ").append(aliensSnuckIn).append("\n");
        // 4) number of astronauts left in the world
        sb.append("Astronauts remaining: ").append(gameObjects.getNonCapturedAstronautCount()).append("\n");
        // 5) number of aliens left in the world.
        sb.append("Aliens remaining: ").append(gameObjects.getNonCapturedAlienCount()).append("\n");

        Log.p(sb.toString());
    }

    /** Print a “map” showing the current world state */
    public void printMap() {
        StringBuilder sb = new StringBuilder();
        IIterator<GameObject> iterator = gameObjects.getIterator();
        while(iterator.hasNext()) {
            GameObject gameObject = iterator.next();
            if (GameObjectCollection.isOpponentCaptured(gameObject)) {
                // Do not list if opponent is captured
                continue;
            }
            sb.append(gameObject.getName()).append(": ");
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
        Boolean bOk = Dialog.show("Confirm quit", "Are you sure you want to quit?", "Ok", "Cancel");
        if (bOk) {
            Display.getInstance().exitApplication();
        }
    }

    // ============ Side Menu Interactions ============

    /** Toggle sound on or off */
    public void turnSoundOnOff() {
        setSound(!getSound());
    }

    /**
     * Set the value of sound
     * @param value Boolean value you want sound to be
     */
    private void setSound(boolean value) {
        sound = value;
        Log.p("Set Sound to " + value);
        updateObservers();
    }

    /** Displays a dialog box giving name, course name, and version number */
    public void displayAboutInfo() {
        StringBuilder aboutMsg = new StringBuilder();
        aboutMsg.append("Name: ").append(AUTHOR).append("\n");
        aboutMsg.append("Course Name").append(COURSE_NAME).append("\n");
        aboutMsg.append("Assignment #: ").append(ASSIGNMENT_NUMBER);
        Dialog.show("About", aboutMsg.toString(), "Ok", null);
    }

    /** Display a list of keys available to the user */
    public void displayHelpInfo() {
        StringBuilder helpInfo = new StringBuilder("Keybinds availble to the user:\n");
        helpInfo.append(createKeyBindHelpText("e", "Expand the door")).append("\n");
        helpInfo.append(createKeyBindHelpText("c", "Contract the door")).append("\n");
        helpInfo.append(createKeyBindHelpText("s", "open the door and update Score")).append("\n");
        helpInfo.append(createKeyBindHelpText("r", "move spaceship to the Right")).append("\n");
        helpInfo.append(createKeyBindHelpText("l", "move spaceship to the Left")).append("\n");
        helpInfo.append(createKeyBindHelpText("u", "move the spaceship Up")).append("\n");
        helpInfo.append(createKeyBindHelpText("d", "move the spaceship Down")).append("\n");
        helpInfo.append(createKeyBindHelpText("o", "move the spaceship to an astrOnaught location")).append("\n");
        helpInfo.append(createKeyBindHelpText("a", "move the spaceship to an Alien location")).append("\n");
        helpInfo.append(createKeyBindHelpText("w", "create a neW alien")).append("\n");
        helpInfo.append(createKeyBindHelpText("f", "an alien-astronaut Fight")).append("\n");
        helpInfo.append(createKeyBindHelpText("t", "clock Ticks")).append("\n");
        helpInfo.append(createKeyBindHelpText("x", "eXit game")).append("\n");
        Dialog.show("Help", helpInfo.toString(), "Ok", null);
    }

    /**
     * Create a message in the form of "'key' - message" given a keyboard key
     * and the message associated with the command
     * @param key from the keyboard
     * @param message describing the action the key takes
     * @return a message in the form of "'key' - message"
     */
    private String createKeyBindHelpText(String key, String message) {
        return "'" + key + "' - " + message;
    }
    
    // ============ Observable methods ============
    
    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);
        updateObservers();
    }
    
    private void updateObservers() {
        setChanged();
        notifyObservers();
    }
}
