package org.csc133.a2.views;

import org.csc133.a2.GameWorld;

public class ViewDamage extends HudSegment{

    GameWorld gw;

    public ViewDamage(GameWorld referenceWorld){
        this.initialize();

        this.setSegmentLabel("DAMAGE");

        gw = referenceWorld;
    }

    @Override
    public void update(){
        this.updateDataValue(gw.getFireDamage()+"");
    }
}
