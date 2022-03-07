package org.csc133.a2.views;

import org.csc133.a2.GameWorld;

public class ViewFires extends HudSegment{


    GameWorld gw;

    public ViewFires(GameWorld referenceWorld){
        this.initialize();

        this.setSegmentLabel("FIRES");

        gw = referenceWorld;
    }

    @Override
    public void update(){
        this.updateDataValue(gw.getFireCount()+"");
    }
}
