package org.csc133.a2.gameobjects.Fixed.FireSystem;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.interfaces.FireState;

public class IsNotStarted implements FireState {

    @Override
    public void fireUpdate(Fire context) {

        //Do nothing?

    }


    @Override
    public void localDraw(Fire fire, Graphics context,
                          Point parentOrigin,
                          Point screenOrigin){

    }
}
