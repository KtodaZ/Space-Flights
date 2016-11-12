package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Command to display help info
 * @author Kyle Szombathy
 *
 */
public class CGiveHelpInfo extends Command {
    private GameWorld gameWorld;

    /**
     * Constructor that passes command name and gameWorld object
     * @param commandName Name of the command
     * @param gameWorld Object necessary to use in actionPerformed
     */
    public CGiveHelpInfo(String commandName, GameWorld gameWorld) {
        super(commandName);
        this.gameWorld = gameWorld;
    }

    /**
     * Perform the action related to this commands name
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        gameWorld.displayHelpInfo();
    }
}