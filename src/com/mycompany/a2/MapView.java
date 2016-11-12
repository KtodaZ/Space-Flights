/**
 * 
 */
package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Border;

/**
 * Container that holds the view of the map
 * Also acts as an observer
 * @author Kyle Szombathy
 *
 */
public class MapView extends Container implements Observer  {
    
    
    
    public MapView() {
        super();
        setStyle();
    }

    public MapView(Layout layout) {
        super(layout);
        setStyle();
    }
    
    private void setStyle() {
        this.getAllStyles().setBorder(Border.createLineBorder(2,
                ColorUtil.LTGRAY));
    }

    public void update(Observable o, Object arg) {
        // code here to call the method in GameWorld (Observable) that output
        // the
        // game object information to the console
    }
}
