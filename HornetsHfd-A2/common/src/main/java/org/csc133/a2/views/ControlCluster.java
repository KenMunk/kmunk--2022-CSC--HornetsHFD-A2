package org.csc133.a2.views;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import org.csc133.a2.GameWorld;

public class ControlCluster extends Container {

    private GameWorld gameWorld;

    public ControlCluster(GameWorld referenceOfGameWorld){

        gameWorld = referenceOfGameWorld;

    }

    @Override
    public void paint(Graphics context){
        super.paint(context);
        context.setColor(ColorUtil.YELLOW);
        context.fillRect
        (
                400,
                400,
                Display.getInstance().getDisplayWidth(),
                200
        );
    }


}
