package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Image;

public class CustomCheckBox extends CheckBox {
    Command command;

    public CustomCheckBox(String text, Command command) {
        super(text);
        this.command = command;
        setStyle();
        setCommand();
    }
    
    private void setStyle() {
        this.getAllStyles().setBgTransparency(255);
        this.getAllStyles().setBgColor(ColorUtil.LTGRAY);
    }
    
    private void setCommand() {
        command.putClientProperty("SideComponent", this);
        this.setCommand(command);
    }
    
}
