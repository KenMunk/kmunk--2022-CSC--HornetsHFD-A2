package org.csc133.a2.gameobjects;

import com.codename1.ui.geom.Point;
import com.codename1.util.MathUtil;
import org.csc133.a2.gameobjects.GameObject;

public abstract class Movable extends GameObject{

    private double heading;
    private double speed;

    Point pointAdjustment(){
        Point output = this.getPos();

        int xOut = (int) MathUtil.round
        (
            this.speed
            *
            Math.cos
            (
                (this.heading-90) * Math.PI/180
            )
        )+output.getX();

        int yOut = (int) MathUtil.round
        (
            this.speed
                *
                Math.sin
                    (
                        (this.heading-90) * Math.PI/180
                    )
        )+output.getY();

        output.setX(xOut);
        output.setY(yOut);
        return(output);
    }

    public void move(){
        this.setPos(this.pointAdjustment());
    }

}
