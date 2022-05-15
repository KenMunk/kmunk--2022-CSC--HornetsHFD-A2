package org.csc133.a2.gameobjects.Movables;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.Fixed.Helipad;

public class UnPlayableHelicopter extends Helicopter{

    protected static UnPlayableHelicopter player;

    private UnPlayableHelicopter(Point initialPos) {
        super(initialPos);
        init(initialPos, ColorUtil.GREEN);
    }

    private UnPlayableHelicopter(Helipad helipad) {
        super(helipad);
        init(helipad.getPos(), ColorUtil.GREEN);
    }

    //Forcing the helicopter to respawn an instance at a requested
    // starting point
    public static void spawnAt(Point startingPoint){
        player = new UnPlayableHelicopter(startingPoint);
    }

    public static void spawnAt(Helipad helipad){
        spawnAt(helipad.getPos());
    }

    public static UnPlayableHelicopter getInstance(){
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
