package org.csc133.a2.gameobjects.Fixed.FireSystem;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.interfaces.FireState;

public class IsBurning implements FireState {

    @Override
    public void fireUpdate(Fire context) {

        context.grow();

    }

    @Override
    public void localDraw(Fire fire, Graphics context,
                          Point parentOrigin,
                          Point screenOrigin){

        fire.localDrawCharred(context,parentOrigin,screenOrigin);
        fire.localDrawBurns(context,parentOrigin,screenOrigin);

    }
}
