package org.csc133.a2.gameobjects.Fixed;

import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.ComponentNode;
import org.csc133.a2.gameobjects.GameObject;

public abstract class Fixed extends ComponentNode {
    //Nothing really to do here

    public boolean nearPosition(Point pos){
        return(false);
    }
}
