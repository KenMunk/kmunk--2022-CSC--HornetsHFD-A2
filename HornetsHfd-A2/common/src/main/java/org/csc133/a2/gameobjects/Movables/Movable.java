package org.csc133.a2.gameobjects.Movables;

import com.codename1.ui.geom.Point;
import com.codename1.util.MathUtil;
import org.csc133.a2.gameobjects.Components.ComponentNode;

public abstract class Movable extends ComponentNode {

    private float heading;
    private float speed;

    public void setSpeed(float speed){
        this.speed = speed;
    }

    public float getSpeed(){
        return(speed);
    }

    public void adjustSpeed(float byAmount){
        this.speed += byAmount;
    }

    public void setHeading(float heading){
        this.heading = heading;
        this.setRotation((heading*3.14f)/180);
    }

    public void adjustHeading(float byAmount){
        System.out.println("Starting heading: " + heading);
        this.heading += byAmount;
        this.heading %= 360;
        if(this.heading < 0){
            this.heading = 359-this.heading;
        }

        System.out.println("Updated rotation: " + getHeading());
        this.setRotation((heading*3.14f)/180);
    }

    public float getHeading(){
        return(heading);
    }

    public Point pointAdjustment(){
        Point output = new Point(0,0);

        int xOut = (int) MathUtil.round
        (
            this.getSpeed()
            *
            Math.cos
            (
                (this.getHeading()-90) * 3.14/180
            ) * (-1)
        )+output.getX();

        int yOut = (int) MathUtil.round
        (
            this.getSpeed()
                *
                Math.sin
                    (
                        (this.getHeading()-90) * 3.14/180
                    ) * (-1)
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
