package org.csc133.a2.gameobjects.Movables;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.Component;
import org.csc133.a2.gameobjects.Fixed.Fire;
import org.csc133.a2.gameobjects.GameObject;
import org.csc133.a2.gameobjects.Fixed.Helipad;
import org.csc133.a2.gameobjects.Fixed.River;
import org.csc133.a2.gameobjects.collections.ComponentCollection;
import org.csc133.a2.interfaces.HelicopterIntakeState;
import org.csc133.a2.interfaces.Steerable;
import org.csc133.a2.states.IntakeCanDrink;
import org.csc133.a2.states.IntakeCannotDrink;
import org.csc133.a2.states.IntakeIsDry;

import java.util.ArrayList;

public class Helicopter extends Movable implements Steerable {

    private class HelicopterComponent extends Component{

        public HelicopterComponent(int color){
            setPos(new Point(0,0));
            setColor(color);
        }

        public void drawHelicopterComponent(Graphics context){}

        @Override
        protected void localDraw(Graphics context, Point parentOrigin,
                                 Point screenOrigin){
            context.setColor(getColor().getValue());
            drawHelicopterComponent(context);
        }
    }

    private class HelicopterBody extends HelicopterComponent {

        ComponentCollection componentSockets;

        public HelicopterBody(int color){
            super(color);
            componentSockets = new ComponentCollection();
            setDimensions(new Dimension(400,250));

        }

        @Override
        public void drawHelicopterComponent(Graphics context){
            context.drawRect(-getDimensions().getWidth()/2,
                    (getDimensions().getWidth()/2) + 30,
                    getDimensions().getWidth(),
                    getDimensions().getHeight(), 3 );
        }

    }

    private abstract class componentSocket extends Component{


        @Override
        protected void localDraw(Graphics context, Point parentOrigin,
                                 Point screenOrigin){

        }
    }

    private final int MAX_FUEL = 25000;
    private final int MAX_SPEED = 10;
    private final int MAX_WATER = 1000;

    private int waterLevel;
    private int fuelLevel;

    private HelicopterIntakeState waterIntakeState;

    private ComponentCollection helicopterComponents;

    public Helicopter(Point initialPos){
        init(initialPos);
    }

    public Helicopter(Helipad helipad){

        init(helipad.getPos());

    }

    public void init(Point initialPos){

        this.waterLevel = this.MAX_WATER;
        this.fuelLevel = this.MAX_FUEL;

        this.setPos(initialPos);

        this.setHeading(0);
        this.setSpeed(0);
        setColor(ColorUtil.YELLOW);

        helicopterComponents = new ComponentCollection();
        helicopterComponents.add(new HelicopterBody(ColorUtil.MAGENTA));

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
            //this.fuelLevel -= MathUtil.pow(getSpeed(),2) + 5;
            this.fuelLevel -= (this.getSpeed() * 5);
            //this.fuelLevel -= 10;
        }
        if(this.fuelLevel < 0){
            this.fuelLevel = 0;
        }
    }

    public void riverCheck(ArrayList<GameObject> potentialRiver){
        for(GameObject go : potentialRiver){
            if(go instanceof River){
                if(go.containsPoint(getPos())){
                    if(getSpeed() <= 2)
                    if(waterLevel < 1000){
                        waterIntakeState = new IntakeCanDrink();
                    }
                    else{
                        waterIntakeState = new IntakeCannotDrink();
                    }
                }
            }
        }
    }

    public boolean fuelOut(){
        return(this.fuelLevel <= 0);
    }

    public void flightUpdate(){
        if(!fuelOut()){
            move();
            burnFuel();
        }
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

        String fuelString = "F : " + getFuel();
        String waterString = "W : " + getWater();

        context.drawChars
        (
            fuelString.toCharArray(),
            0,
            fuelString.length(),
            offsetPoint.getX(),
            offsetPoint.getY() +100
        );
        context.drawChars
        (
                waterString.toCharArray(),
                0,
                waterString.length(),
                offsetPoint.getX(),
                offsetPoint.getY() +130
        );
    }

    @Override
    protected void localDraw(Graphics context, Point parentOrigin,
                             Point screenOrigin){
        for(Component c: helicopterComponents){
            c.draw(context,parentOrigin,screenOrigin);
        }
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

    public void fight(ArrayList<GameObject> gameObject) {
        attackFires(gameObject);
    }

    public void toDrink() {
        waterLevel += waterIntakeState.drinkUpdate();
    }

    public void toSlowDown() {
        adjustSpeed(-1);
        if(getSpeed() < 0){
            setSpeed(0);
        }
    }

    public boolean isStopped(){
        return(getSpeed() == 0);
    }
}
