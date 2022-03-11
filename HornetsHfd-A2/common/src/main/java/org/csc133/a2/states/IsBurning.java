package org.csc133.a2.states;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.Fire;
import org.csc133.a2.interfaces.FireState;

public class IsBurning implements FireState {

    @Override
    public void fireUpdate(Fire context) {

        context.grow();

    }

    @Override
    public void drawUpdate
    (
        Fire context,
        Graphics gfxContext,
        Point containerOrigin
    ){
        context.drawCharred(gfxContext,containerOrigin);
        context.drawBurns(gfxContext,containerOrigin);
    }
}
