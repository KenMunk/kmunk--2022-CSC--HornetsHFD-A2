package org.csc133.a2.interfaces;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.Fixed.FireSystem.Fire;

public interface FireState {

    public void fireUpdate(Fire context);

    public void localDraw(Fire fire, Graphics context,
                          Point parentOrigin,
                             Point screenOrigin);

}
