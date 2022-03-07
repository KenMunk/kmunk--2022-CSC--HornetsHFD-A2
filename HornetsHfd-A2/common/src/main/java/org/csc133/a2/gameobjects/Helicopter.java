package org.csc133.a2.gameobjects;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.interfaces.Steerable;

import java.util.ArrayList;

public class Helicopter extends Movable implements Steerable {

    private final int MAX_FUEL = 25000;
    private final int MAX_SPEED = 10;
    private final int MAX_WATER = 1000;

    private int waterLevel;
    private int fuelLevel;
    private boolean canDrink;

    public Helicopter(Helipad helipad){

        this.waterLevel = this.MAX_WATER;
        this.fuelLevel = this.MAX_FUEL;

        this.setPos( helipad.getPos());

        this.setHeading(0);
        this.setSpeed(0);

        this.canDrink = false;


    }

    public void attackFires(ArrayList<Fire> aBunchOfFires){

        for(Fire aFire: aBunchOfFires){
            if(aFire.nearPosition(this.getPos())){
                aFire.extinguish(this.waterLevel);
            }
        }

        this.waterLevel = 0;
    }

    public void burnFuel(){
        if(this.fuelLevel > 0){
            //this.fuelLevel -= (int)Math.pow(this.speed,2) + 5;
            this.fuelLevel -= (int)Math.pow(this.getSpeed(),1) * 5+5;
        }
        if(this.fuelLevel < 0){
            this.fuelLevel = 0;
        }
    }

    public void addWater(){

    }

    public int getPoints(){
        return(this.fuelLevel);
    }

    public boolean fuelOut(){
        return(this.fuelLevel <= 0);
    }

    @Override
    public void draw(Graphics context, Point containerOrigin){

    }

    @Override
    public void turnLeft(double amount) {

    }

    @Override
    public void turnRight(double amount) {

    }
}
