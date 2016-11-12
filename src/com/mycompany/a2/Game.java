package com.mycompany.a2;

import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

/**
 * Parent class of Game. Controls User input and initialization This is the
 * "Controller" part of the games MVC Architecture
 * @author Kyle Szombathy 
 */
public class Game extends Form {
    private static final String TITLE = "Space Flights Game - Kyle Szombathy";
    private GameWorld gameWorld; // GW
    private MapView mapView; // MV
    private ScoreView scoreView; // SV
    private Container leftButtons, rightButtons, bottomButtons;
    private Command expandDoor, contractDoor, openDoorUpdateScore, moveRight, moveLeft, moveUp, moveDown,
            moveToAstronaut, moveToAlien, createNewAlien, alienAstroFight, tickClock, exitGame, turnSoundOnOff,
            giveAboutInfo, giveHelpInfo;
    private Toolbar toolbar;

    public Game() {
        setUpView();
        createGameWorld();
        addCommands();
        this.show();
    }

    // ============ Setup View ============

    /** Create all view objects */
    private void setUpView() {
        createContainerObjects();
        setFormLayoutTypes();
        createToolbar();
        addContainersToParentForm();
    }

    private void createContainerObjects() {
        mapView = new MapView(); // create an “Observer” for the maps
        scoreView = new ScoreView(); // create an “Observer” for the game state data
        leftButtons = new Container();
        rightButtons = new Container();
        bottomButtons = new Container();
    }

    /** Set view layouts of each container */
    private void setFormLayoutTypes() {
        this.setLayout(new BorderLayout()); // Set layout of parent view
        mapView.setLayout(new FlowLayout());
        scoreView.setLayout(new FlowLayout(Component.CENTER));
        leftButtons.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        rightButtons.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        bottomButtons.setLayout(new FlowLayout(Component.CENTER));
    }

    /**Create toolbar to add items to*/
    private void createToolbar() {
        toolbar = new Toolbar();
        this.setToolbar(toolbar);
        this.setTitle(TITLE);
    }

    /** Add Containers to the view */
    private void addContainersToParentForm() {
        this.add(BorderLayout.NORTH, scoreView);
        this.add(BorderLayout.CENTER, mapView);
        this.add(BorderLayout.WEST, leftButtons);
        this.add(BorderLayout.EAST, rightButtons);
        this.add(BorderLayout.SOUTH, bottomButtons);
    }
    
    // ============ Create GameWorld ============
    
    /** Create and init GameWorld object*/
    private void createGameWorld() {
        createGameWorldObject();
        addObserversToObserverable();
    }
    
    private void createGameWorldObject() {
        gameWorld = new GameWorld(mapView.getWidth(), mapView.getHeight());
    }

    /** register observers to game world */
    private void addObserversToObserverable() {
        gameWorld.addObserver((Observer) mapView); // register the map observer
        gameWorld.addObserver((Observer) scoreView); // register the score observer
    }

    // ============ Add commands to objects ============
    
    private void addCommands() {
        createCommandObjects();
        addButtonCommands();
        addSideMenuCommands();
        addKeyboardCommands();
    }
    
    /** Create the command objects related to each "C[Command_Name]" class */
    private void createCommandObjects() {
        expandDoor = new CExpandDoor("Expand", gameWorld);
        contractDoor = new CContractDoor("Contract", gameWorld);
        openDoorUpdateScore = new COpenDoorUpdateScore("Score", gameWorld);
        moveRight = new CMoveRight("MoveRight", gameWorld);
        moveLeft = new CMoveLeft("MoveLeft", gameWorld);
        moveUp = new CMoveUp("MoveUp", gameWorld);
        moveDown = new CMoveDown("MoveDown", gameWorld);
        moveToAstronaut = new CMoveToAstronaut("MoveToAstronaut", gameWorld);
        moveToAlien = new CMoveToAlien("MoveToAlien", gameWorld);
        createNewAlien = new CCreateNewAlien("NewAlien", gameWorld);
        alienAstroFight = new CAlienAstroFight("Fight", gameWorld);
        tickClock = new CTickClock("Tick", gameWorld);
        exitGame = new CExitGame("Exit", gameWorld);
        turnSoundOnOff = new CTurnSoundOnOff("Sound", gameWorld);
        giveAboutInfo = new CGiveAboutInfo("About", gameWorld);
        giveHelpInfo = new CGiveHelpInfo("Help", gameWorld);
    }
    
    private void addButtonCommands() {
        leftButtons.add(new CustomButton(expandDoor));
        leftButtons.add(new CustomButton(moveUp));
        leftButtons.add(new CustomButton(moveLeft));
        leftButtons.add(new CustomButton(moveToAstronaut));
        rightButtons.add(new CustomButton(contractDoor));
        rightButtons.add(new CustomButton(moveDown));
        rightButtons.add(new CustomButton(moveRight));
        rightButtons.add(new CustomButton(moveToAlien));
        rightButtons.add(new CustomButton(openDoorUpdateScore));
        bottomButtons.add(new CustomButton(createNewAlien));
        bottomButtons.add(new CustomButton(alienAstroFight));
        bottomButtons.add(new CustomButton(tickClock));
        toolbar.addCommandToRightBar(giveHelpInfo);
        toolbar.addCommandToRightBar(giveAboutInfo);
    }

    private void addSideMenuCommands() {
        addRegularSideMenuCommands();
        addSoundCheckBox();
    }

    private void addRegularSideMenuCommands() {
        toolbar.addCommandToSideMenu(openDoorUpdateScore);
        toolbar.addCommandToSideMenu(turnSoundOnOff);
        toolbar.addCommandToSideMenu(giveAboutInfo);
        toolbar.addCommandToSideMenu(exitGame);
    }

    private void addSoundCheckBox() {
        CheckBox soundCheckBox = new CustomCheckBox("Sound Status: ", turnSoundOnOff);
        soundCheckBox.setSelected(gameWorld.getSound());
        toolbar.addCommandToSideMenu(turnSoundOnOff);
    }

    /** Create key bindings for each necessary command */
    private void addKeyboardCommands() {
        this.addKeyListener('e', expandDoor);
        this.addKeyListener('c', contractDoor);
        this.addKeyListener('s', openDoorUpdateScore);
        this.addKeyListener('r', moveRight);
        this.addKeyListener('l', moveLeft);
        this.addKeyListener('u', moveUp);
        this.addKeyListener('d', moveDown);
        this.addKeyListener('o', moveToAlien);
        this.addKeyListener('a', moveToAstronaut);
        this.addKeyListener('w', createNewAlien);
        this.addKeyListener('f', alienAstroFight);
        this.addKeyListener('t', tickClock);
        this.addKeyListener('x', exitGame);
    }
    
}