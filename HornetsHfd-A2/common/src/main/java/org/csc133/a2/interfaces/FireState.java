package org.csc133.a2.interfaces;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.Fixed.Fire;

public interface FireState {

    public void fireUpdate(Fire context);

    public void drawUpdate
    (
        Fire context,
        Graphics gfxContext,
        Point containerOrigin
    );

    public void localDraw(Fire fire, Graphics context,
                          Point parentOrigin,
                             Point screenOrigin);

}
