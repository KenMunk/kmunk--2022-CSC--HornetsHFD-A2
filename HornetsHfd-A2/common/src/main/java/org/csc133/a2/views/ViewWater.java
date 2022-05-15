package org.csc133.a2.views;

import org.csc133.a2.GameWorld;

public class ViewWater extends HudSegment{

    GameWorld gw;

    public ViewWater(GameWorld referenceWorld){
        this.initialize();

        this.setSegmentLabel("WATER");

        gw = referenceWorld;
    }

    @Override
    public void update(){
        //[TODO] reevaluate after making helicopter singletons
        this.updateDataValue(gw.getHelicopterWater()+"");
    }
}
