package com.mycompany.a1;

import com.codename1.io.Log;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 * Parent class of Game. Controls User input and initialization
 * @author Kyle Szombathy
 */
public class Game extends Form {
    private GameWorld gameWorld;
    private boolean exitFlag;

    public Game() {
        gameWorld = new GameWorld();
        gameWorld.init();
        play();
        exitFlag = false;
    }

    @SuppressWarnings("rawtypes")
    private void play() {
        /*
         * TODO: code here to accept and execute user commands that operate on
         * the game world (refer to “Appendix - CN1 Notes” for accepting
         * keyboard commands via a text field located on the form)
         */

        // Add Enter Command Label
        final String ENTER_COMMAND_LABEL = "Enter a Command:";
        Label myLabel = new Label(ENTER_COMMAND_LABEL);
        this.addComponent(myLabel);

        // Add Text Field
        final TextField commandField = new TextField();
        this.addComponent(commandField);

        this.show();

        commandField.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                String enteredCommand = retrieveTextFieldText(commandField);
                commandField.clear();

                if (!exitFlag) {
                    switch (getFirstKeyEntered(enteredCommand)) {
                        case 'a':
                            gameWorld.moveSpaceshipToRandomAlien();
                            break;
                        case 'o':
                            gameWorld.moveSpaceshipToRandomAstronaut();
                            break;
                        case 'r':
                            gameWorld.moveShipRight();
                            break;
                        case 'l':
                            gameWorld.moveShipLeft();
                            break;
                        case 'u':
                            gameWorld.moveShipUp();
                            break;
                        case 'd':
                            gameWorld.moveShipDown();
                            break;
                        case 'e':
                            gameWorld.expandSpaceshipDoor();
                            break;
                        case 'c':
                            gameWorld.contractSpaceshipDoor();
                            break;
                        case 't':
                            gameWorld.tick();
                            break;
                        case 's':
                            gameWorld.openDoorAndUpdateScore();
                            break;
                        case 'w':
                            gameWorld.collisionAlienAlien();
                            break;
                        case 'f':
                            gameWorld.collisionAlienAstronaut();
                            break;
                        case 'p':
                            gameWorld.printScore();
                            break;
                        case 'm':
                            gameWorld.printMap();
                            break;
                        case 'x':
                            Log.p("Would you like to exit? Press 'y' to exit or 'n' to continue.");
                            exitFlag = true;
                            break;
                        default:
                            Log.p("Error: Invalid Entry", Log.ERROR);
                            break;
                    }
                } else {
                    switch (getFirstKeyEntered(enteredCommand)) {
                        case 'y':
                            gameWorld.exitGame();
                            break;
                        case 'n':
                            exitFlag = false;
                            break;
                        default:
                            Log.p("Error: Invalid Entry. Press 'y' to exit or 'n' to continue.", Log.ERROR);
                            break;
                    }
                }
            }

            private String retrieveTextFieldText(TextField field) {
                return field.getText().toString().toLowerCase();
            }

            private char getFirstKeyEntered(String fullString) {
                try {
                    return fullString.charAt(0);
                } catch (StringIndexOutOfBoundsException e) {
                    return Character.MIN_VALUE;
                }
            }
        });
    }
}
