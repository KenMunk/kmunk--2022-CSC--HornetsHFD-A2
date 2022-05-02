package org.csc133.a2.gameobjects.Movables;

import com.codename1.ui.geom.Point;
import com.codename1.util.MathUtil;
import org.csc133.a2.gameobjects.ComponentNode;
import org.csc133.a2.gameobjects.GameObject;

public abstract class Movable extends ComponentNode {

    private double heading;
    private double speed;

    public void setSpeed(double speed){
        this.speed = speed;
    }

    public double getSpeed(){
        return(speed);
    }

    public void adjustSpeed(double byAmount){
        this.speed += byAmount;
    }

    public void setHeading(double heading){
        this.heading = heading;
    }

    public void adjustHeading(double byAmount){
        this.heading += byAmount;
        this.heading %= 360;
        if(this.heading < 0){
            this.heading = 359-this.heading;
        }
    }

    public double getHeading(){
        return(heading);
    }

    Point pointAdjustment(){
        Point output = new Point(0,0);

        int xOut = (int) MathUtil.round
        (
            this.getSpeed()
            *
            Math.cos
            (
                (this.getHeading()-90) * 3.14/180
            )
            * (-1)
        )+output.getX();

        int yOut = (int) MathUtil.round
        (
            this.getSpeed()
                *
                Math.sin
                    (
                        (this.getHeading()-90) * 3.14/180
                    )
        )+output.getY();

        output.setX(xOut);
        output.setY(yOut);
        return(output);
    }

    public Point adjustedPoint(){
        Point output = getPos();
        Point adjustment = pointAdjustment();
        output.setX(output.getX() + adjustment.getX());
        output.setY(output.getY() + adjustment.getY());
        return(output);
    }



    public void move(){
        this.setPos(this.adjustedPoint());
    }

}
