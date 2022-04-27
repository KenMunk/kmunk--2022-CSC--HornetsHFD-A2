package org.csc133.a2.views;

import org.csc133.a2.GameWorld;

public class ViewSpeed extends HudSegment{

    GameWorld gw;

    public ViewSpeed(GameWorld referenceWorld){
        this.initialize();

        this.setSegmentLabel("SPEED");

        gw = referenceWorld;
    }

    @Override
    public void update(){
        //[TODO] reevaluate after making helicopter singletons
        //this.updateDataValue(gw.getHelicopterSpeed()+"");
    }

}
