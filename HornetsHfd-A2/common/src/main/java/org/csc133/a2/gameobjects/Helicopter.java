package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.interfaces.HelicopterIntakeState;
import org.csc133.a2.interfaces.Steerable;
import org.csc133.a2.states.IntakeCannotDrink;
import org.csc133.a2.states.IntakeIsDry;

import java.util.ArrayList;

public class Helicopter extends Movable implements Steerable {

    private final int MAX_FUEL = 25000;
    private final int MAX_SPEED = 10;
    private final int MAX_WATER = 1000;

    private int waterLevel;
    private int fuelLevel;

    private HelicopterIntakeState waterIntakeState;

    public Helicopter(Helipad helipad){

        this.waterLevel = this.MAX_WATER;
        this.fuelLevel = this.MAX_FUEL;

        this.setPos( helipad.getPos());

        this.setHeading(0);
        this.setSpeed(0);
        setColor(ColorUtil.YELLOW);

        waterIntakeState = new IntakeCannotDrink();

    }

    private void attackFires(ArrayList<GameObject> aBunchOfFires){

        for(GameObject go: aBunchOfFires){
            if(go instanceof Fire){

                Fire aFire = (Fire)go;

                if(aFire.nearPosition(this.getPos())){
                    aFire.extinguish(this.waterLevel);
                }
            }
        }

        waterIntakeState = new IntakeIsDry();

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

    public void riverCheck(ArrayList<GameObject> potentialRiver){

    }

    public int getPoints(){
        return(this.fuelLevel);
    }

    public boolean fuelOut(){
        return(this.fuelLevel <= 0);
    }

    @Override
    public void draw(Graphics context, Point containerOrigin){
        Display thisDisplay = Display.getInstance();

        int heliSize = thisDisplay.getDisplayHeight()/40;
        context.setColor(this.getColor().getValue());

        Point offsetPoint = getOffsetPoint(containerOrigin);

        context.fillArc
        (
                offsetPoint.getX()-heliSize,
                offsetPoint.getY()-heliSize,
                heliSize*2,
                heliSize*2,
                0,
                360
        );


        int futureX = pointAdjustment().getX()*20 + offsetPoint.getX();
        int futureY = pointAdjustment().getY()*20 + offsetPoint.getY();

        context.drawLine
        (
                offsetPoint.getX(),
                offsetPoint.getY(),
                futureX,
                futureY
        );

    }

    //controlled actions

    public void drink(){
        waterLevel += waterIntakeState.drinkUpdate();
    }

    @Override
    public void turnLeft(double amount) {
        adjustHeading(amount);
    }

    @Override
    public void turnRight(double amount) {
        adjustHeading(-amount);
    }

    public int getFuel() {

        int tempFuel = fuelLevel;
        return(tempFuel);

    }

    public int getWater(){
        int tempWater = waterLevel;
        return(tempWater);
    }

    public void toAccelerate() {
        adjustSpeed(3);
        if(getSpeed() > MAX_SPEED){
            setSpeed(MAX_SPEED);
        }
    }

    public void toTurnLeft() {
        turnLeft(15);
    }

    public void toTurnRight() {
        turnRight(15);
    }

    public void toDump(ArrayList<GameObject> gameObject) {
        attackFires(gameObject);
    }

    public void toDrink() {
    }

    public void toSlowDown() {
        adjustSpeed(-1);
        if(getSpeed() < 0){
            setSpeed(0);
        }
    }

    public void updateHelicopterPosition(){
        move();
    }
}
