package org.csc133.a2.views;

import org.csc133.a2.GameWorld;

public class ViewFuel extends HudSegment{

    GameWorld gw;

    public ViewFuel(GameWorld referenceWorld){
        this.initialize();

        this.setSegmentLabel("FUEL");

        gw = referenceWorld;
    }

    @Override
    public void update(){
        //[TODO] reevaluate after making helicopter singletons
        this.updateDataValue(gw.getHelicopterFuel()+"");
    }

}
