package org.csc133.a2.gameobjects.Movables;

import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.Fixed.Helipad;

public class PlayableHelicopter extends Helicopter{
    //Doing a hybrid singleton since it's going to
    //be difficult to guarentee that the default starting position
    // will be pre-declared before the first instance request
    protected static PlayableHelicopter player;

    private PlayableHelicopter(Point initialPos) {
        super(initialPos);
    }

    private PlayableHelicopter(Helipad helipad) {
        super(helipad);
    }

    //Forcing the helicopter to respawn an instance at a requested
    // starting point
    public static void spawnAt(Point startingPoint){
        player = new PlayableHelicopter(startingPoint);
    }

    public static void spawnAt(Helipad helipad){
        spawnAt(helipad.getPos());
    }

    public static PlayableHelicopter getInstance(){
        if(player == null){
            spawnAt(new Point(0,0));
        }
        return(player);
    }

    public static void drawSingleton(
            Graphics context,
            Point parentOrigin,
            Point screenOrigin
    )
    {
        if(player != null){
            Transform transform = Transform.makeIdentity();
            context.getTransform(transform);
            transform.translate(0,50);
            context.setTransform(transform);
            player.draw(context, parentOrigin, screenOrigin);
            transform.translate(0,-50);
            context.setTransform(transform);
        }
    }

}
