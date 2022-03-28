package org.csc133.a2.views;

import org.csc133.a2.GameWorld;

public class ViewWater extends HudSegment{

    GameWorld gw;

    public ViewWater(GameWorld referenceWorld){
        this.initialize();

        this.setSegmentLabel("FUEL");

        gw = referenceWorld;
    }

    @Override
    public void update(){
        this.updateDataValue(gw.getHelicopterWater()+"");
    }
}
