package com.mycompany.a1;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class Game extends Form {
    private GameWorld gameWorld;

    public Game() {
        gameWorld = new GameWorld();
        gameWorld.init();
        play();
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
                
                switch (getFirstChar(enteredCommand)) {
                    case 'a':
                        gameWorld.transferToAlien();
                        break;
                    case 'o':
                        gameWorld.transferToAstronaut();
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
                        gameWorld.expandDoor();
                        break;
                    case 'c':
                        gameWorld.contractDoor();
                        break;
                    case 't':
                        gameWorld.tickGameClock();
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
                    case 'y':
                    case 'n':
                        gameWorld.exitGame(getFirstChar(enteredCommand));
                        break;
                    default:
                        // TODO: Error Message
                        break;
                }
            }
            
            private String retrieveTextFieldText(TextField field){
                return field.getText().toString().toLowerCase();
            }
            
            private char getFirstChar(String fullString) {
                return fullString.charAt(0);
            }
        });
    }
}
