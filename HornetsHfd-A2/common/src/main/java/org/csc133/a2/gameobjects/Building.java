package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.util.MathUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Building extends Fixed{

    private int initialValue;
    private int burnAmount;

    public Building(Point origin, Dimension dimensions){

        setPos(origin);
        setDimensions(dimensions);
        setColor(ColorUtil.rgb(255,0,0));
        setInitialValue(getSize()*300);

    }

    private void setInitialValue(int value){

        initialValue = value;

    }

    public int getInitialValue(){

        return(initialValue);

    }

    public int getSize(){

        Dimension buildingDimension = getDimensions();
        return((buildingDimension.getHeight() * buildingDimension.getWidth()));

    }

    public int calculateBurns(ArrayList<GameObject> potentialFires){
        int burns = 0;

        for(GameObject pF: potentialFires){

            if(pF instanceof Fire){

                if(containsPoint(pF.getPos())){

                    if(((Fire) pF).isBurning()){
                        Fire aFire = (Fire)pF;
                        int someBurns = aFire.getPeakSize();
                        burns+=someBurns;
                    }

                }

            }

        }

        return(burns);
    }

    public void updateBurns(ArrayList<GameObject> gameObjects){
        burnAmount = calculateBurns(gameObjects);
    }

    public int getBurnAmount(){
        return(burnAmount);
    }

    public int getDestroyPercentage(){
        float amountBurned = getBurnAmount();
        float buildingSize = getSize();
        int destroyedPercentage =
        MathUtil.round(
            100
            *
            (amountBurned / buildingSize)
        );
        return(destroyedPercentage);
    }

    @Override
    public void draw(Graphics context, Point containerOrigin){

        super.draw(context, containerOrigin);
        
        Point offsetPoint = new Point(0,0);

        offsetPoint.setX(getPos().getX() + containerOrigin.getX());
        offsetPoint.setY(getPos().getY() + containerOrigin.getY());

        Point lowerCorner = new Point
        (
                offsetPoint.getX() + getDimensions().getWidth(),
                offsetPoint.getY() + getDimensions().getHeight()
        );

        context.setColor(getColor().getValue());

        context.drawRect
        (
            offsetPoint.getX(),
            offsetPoint.getY(),
            getDimensions().getWidth(),
            getDimensions().getHeight(),
            5
        );

        String marketValue = "V: " + initialValue;
        context.setFont
        (
            Font.createSystemFont
            (
                Font.FACE_MONOSPACE,
                Font.STYLE_BOLD,
                //The font size could really be resizable
                Font.SIZE_MEDIUM
            )
        );

        context.drawChars
        (
            marketValue.toCharArray(),
            0,
            marketValue.length(),
            lowerCorner.getX(),
            lowerCorner.getY()
        );

        String percentDestroyed = "D: " + getDestroyPercentage() +
                "%";

        context.drawChars
        (
            percentDestroyed.toCharArray(),
            0,
            percentDestroyed.length(),
            lowerCorner.getX(),
            lowerCorner.getY()+32
        );
    }

    public void setFireInBuilding(Fire fire){
        int fireX = new Random().nextInt(getDimensions().getWidth());
        int fireY = new Random().nextInt(getDimensions().getHeight());

        fireX += getPos().getX();
        fireY += getPos().getY();

        fire.setPos(new Point(fireX,fireY));
        fire.start();
    }

}
