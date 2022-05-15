package org.csc133.a2.views;

import org.csc133.a2.GameWorld;

public class ViewHeading extends HudSegment{

    GameWorld gw;

    public ViewHeading(GameWorld referenceWorld){
        this.initialize();
        this.setSegmentLabel("HEADING");
        gw = referenceWorld;
    }

    @Override
    public void update(){
        //[TODO] reevaluate after making helicopter singletons
        this.updateDataValue(gw.getHelicopterHeading()+"");
    }

}
