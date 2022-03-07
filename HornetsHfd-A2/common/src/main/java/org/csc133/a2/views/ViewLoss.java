package org.csc133.a2.views;

import org.csc133.a2.GameWorld;

public class ViewLoss extends HudSegment {

    GameWorld gw;

    public ViewLoss(GameWorld gameWorld) {
        this.initialize();

        this.setSegmentLabel("LOSS");

        gw = gameWorld;

    }


    @Override
    public void update(){
        this.updateDataValue(gw.getLoss()+"");
    }
}
