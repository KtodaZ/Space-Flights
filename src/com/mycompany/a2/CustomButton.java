package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Image;

public class CustomButton extends com.codename1.ui.Button {

    public CustomButton() {
        super();
        initStyle();
    }

    public CustomButton(Command cmd) {
        super(cmd);
        initStyle();
    }

    public CustomButton(Image icon) {
        super(icon);
        initStyle();
    }

    public CustomButton(String text, Image icon) {
        super(text, icon);
        initStyle();
    }

    public CustomButton(String text) {
        super(text);
        initStyle();
    }

    private void initStyle() {
        this.getUnselectedStyle().setBgTransparency(255);
        this.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
        this.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
        this.getAllStyles().setPadding(Component.TOP, 5);
        this.getAllStyles().setPadding(Component.BOTTOM, 5);
    }
}
