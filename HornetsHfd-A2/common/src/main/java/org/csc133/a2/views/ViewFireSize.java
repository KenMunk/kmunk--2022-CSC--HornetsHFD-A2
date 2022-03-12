package org.csc133.a2.views;

import org.csc133.a2.GameWorld;

public class ViewFireSize extends HudSegment{

    GameWorld gw;

    public ViewFireSize(GameWorld referenceWorld){
        this.initialize();

        this.setSegmentLabel("FIRE SIZE");

        gw = referenceWorld;
    }

    @Override
    public void update(){
        this.updateDataValue(gw.getFireSize()+"");
    }
}
