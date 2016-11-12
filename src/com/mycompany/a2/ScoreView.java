package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.io.Log;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.Layout;

/**
 * Container that holds the view of the Score Also acts as an observer
 * @author Kyle Szombathy
 *
 */
public class ScoreView extends Container implements Observer {
    Label totScore, totScoreVal, astroRescued, astroRescuedVal, alienSneaked, alienSneakedVal, astroRemain,
            astroRemainVal, alienRemain, alienRemainVal, sound, soundVal;

    public ScoreView() {
        super();
        createLabels();
        addLabels();
    }

    public ScoreView(Layout layout) {
        super(layout);
        createLabels();
        addLabels();
    }

    /** Create the label objects to use in this class */
    private void createLabels() {
        totScore = new Label("Total Score:");
        totScoreVal = new Label();
        astroRescued = new Label("Astronauts Rescued:");
        astroRescuedVal = new Label();
        alienSneaked = new Label("Aliens Sneaked In:");
        alienSneakedVal = new Label();
        astroRemain = new Label("Astronauts Remaining:");
        astroRemainVal = new Label();
        alienRemain = new Label("Aliens Remaining:");
        alienRemainVal = new Label();
        sound = new Label("Sound:");
        soundVal = new Label();
    }

    /** Add the labels to this container */
    private void addLabels() {
        this.add(totScore);
        this.add(totScoreVal);
        this.add(astroRescued);
        this.add(astroRescuedVal);
        this.add(alienSneaked);
        this.add(alienSneakedVal);
        this.add(astroRemain);
        this.add(astroRemainVal);
        this.add(alienRemain);
        this.add(alienRemainVal);
        this.add(sound);
        this.add(soundVal);
    }

    /** Use observer pattern to receive updates from GameObject */
    public void update(Observable observable, Object data) {
        GameWorld gameWorld = (GameWorld) observable;
        totScoreVal.setText(formatIntegerLabelValue(gameWorld.getScore()) + "  ");
        astroRescuedVal.setText(formatIntegerLabelValue(gameWorld.getAstronautsRescued()));
        alienSneakedVal.setText(formatIntegerLabelValue(gameWorld.getAliensSnuckIn()));
        astroRemainVal.setText(formatIntegerLabelValue(gameWorld.getNonCapturedAstronautCount()));
        alienRemainVal.setText(formatIntegerLabelValue(gameWorld.getNonCapturedAlienCount()));
        soundVal.setText(gameWorld.getSoundString());
    }

    /** Format an int into the format needed in this class */
    private String formatIntegerLabelValue(int integer) {
        String intString = Integer.toString(integer);
        return intString + "  ";
    }

}