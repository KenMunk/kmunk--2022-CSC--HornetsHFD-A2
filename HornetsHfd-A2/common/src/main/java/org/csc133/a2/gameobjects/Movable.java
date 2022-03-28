package org.csc133.a2.gameobjects;

import com.codename1.ui.geom.Point;
import com.codename1.util.MathUtil;
import org.csc133.a2.gameobjects.GameObject;

public class Movable extends GameObject{

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
        this.heading %= 2;
    }

    public double getHeading(){
        return(heading);
    }

    Point pointAdjustment(){
        Point output = this.getPos();

        int xOut = (int) MathUtil.round
        (
            this.getSpeed()
            *
            Math.cos
            (
                (this.getHeading()-90) * Math.PI/180
            )
        )+output.getX();

        int yOut = (int) MathUtil.round
        (
            this.getSpeed()
                *
                Math.sin
                    (
                        (this.getHeading()-90) * Math.PI/180
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
