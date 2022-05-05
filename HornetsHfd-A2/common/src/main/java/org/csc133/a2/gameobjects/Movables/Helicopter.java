package org.csc133.a2.gameobjects.Movables;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.Components.Component;
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

        protected void drawHelicopterComponent(Graphics context,
                                            Point parentOrigin,
                                            Point screenOrigin){}

        public void update(){

        }

        @Override
        protected void localDraw(Graphics context, Point parentOrigin,
                                 Point screenOrigin){
            context.setColor(getColor().getValue());
            drawHelicopterComponent(context, parentOrigin, screenOrigin);
        }
    }

    private class HelicopterBody extends HelicopterComponent {

        ComponentCollection componentSockets;
        int tailBaseWidth;

        public HelicopterBody(int color){
            super(color);
            componentSockets = new ComponentCollection();
            setDimensions(new Dimension(250,300));
            tailBaseWidth = 100;

            HelicopterTail tail = new HelicopterTail(color);
            tail.setDimensions(new Dimension(tailBaseWidth,
                    tailBaseWidth*4));
            tail.setPos(new Point(0,-getDimensions().getHeight()/2));

            HelicopterBubble head = new HelicopterBubble(color);
            head.setDimensions
            (
                new Dimension
                (
                    getDimensions().getWidth(),
                    getDimensions().getWidth()
                )
            );
            head.setPos(new Point(0,getDimensions().getHeight()/2));

            int skidColor = ColorUtil.rgb(133,90,10);

            HelicopterSkid leftSkid = new HelicopterSkid(skidColor);
            leftSkid.referenceBody(getDimensions());
            leftSkid.leftSide();

            HelicopterSkid rightSkid = new HelicopterSkid(skidColor);
            rightSkid.referenceBody(getDimensions());
            rightSkid.rightSide();

            HelicopterFins fins = new HelicopterFins(color);
            fins.setFinBase(tail.getEndWidth());
            fins.setPos(new Point(0,-getHeight()/2-tailBaseWidth*4));

            componentSockets.add(fins);
            componentSockets.add(leftSkid);
            componentSockets.add(rightSkid);
            componentSockets.add(tail);
            componentSockets.add(head);
        }

        @Override
        protected void drawHelicopterComponent(Graphics context,
                                            Point parentOrigin,
                                            Point screenOrigin){
            for(Component c : componentSockets){
                c.draw(context, new Point(0,0), screenOrigin);
            }

            context.drawRect(-getDimensions().getWidth()/2,
                    0,
                    getDimensions().getWidth(),
                    getDimensions().getHeight()/2, 3 );
            context.drawLine(0,-getDimensions().getHeight()/2,0,
                    getDimensions().getHeight()/2);
            context.drawLine(-getDimensions().getWidth()/2, 0,
                    -tailBaseWidth/2,-getDimensions().getHeight()/2);
            context.drawLine(getDimensions().getWidth()/2, 0,
                    tailBaseWidth/2,-getDimensions().getHeight()/2);
            context.drawLine(-tailBaseWidth/2,
                    -getDimensions().getHeight()/2,tailBaseWidth/2,
                    -getDimensions().getHeight()/2);
        }

    }

    private class HelicopterTail extends HelicopterComponent{
        public HelicopterTail(int color){
            super(color);
            setDimensions(new Dimension(250,400));

        }

        public int getBaseWidth(){
            return(getWidth());
        }

        public int getEndWidth(){
            return(getWidth()/3);
        }

        @Override
        protected void drawHelicopterComponent(Graphics context,
                                            Point parentOrigin,
                                            Point screenOrigin){
            int halfWidth = getBaseWidth()/2;
            int halfEndWidth = getEndWidth()/2;
            int height = -getDimensions().getHeight();

            context.drawLine(-halfWidth,0,-halfEndWidth,
                    height);
            context.drawLine(halfWidth,0,halfEndWidth,
                    height);
            context.drawLine(0,0,0,height);
            context.drawLine(-halfEndWidth,height,halfEndWidth,height);

        }
    }

    private class HelicopterBubble extends HelicopterComponent{
        public HelicopterBubble(int color){
            super(color);
            setDimensions(new Dimension(250,125));
        }

        @Override
        protected void drawHelicopterComponent(Graphics context,
                                            Point parentOrigin,
                                            Point screenOrigin){
            context.drawLine(0,0,
                    0,getDimensions().getWidth()/2);
            cn1ForwardPrimitiveTranslate(context,getDimensions());

            //Since the bubble arc was flipped, I'm going to
            //flip the bubble only

            Transform transform = Transform.makeIdentity();
            context.getTransform(transform);
            transform.translate(0,getDimensions().getWidth()*1.42f);
            transform.scale(1,-1);
            context.setTransform(transform);
            context.drawArc(0,0,
                    getDimensions().getWidth(),
                    getDimensions().getWidth(),0,180);
            transform.scale(1,-1);
            transform.translate(0,-getDimensions().getWidth()*1.42f);
            context.setTransform(transform);

            cn1ReversePrimitiveTranslate(context,getDimensions());

        }
    }

    private class HelicopterSkid extends HelicopterComponent{

        int isLeft;

        public HelicopterSkid(int color){
            super(color);
            isLeft = 1;

        }

        public void rightSide(){
            isLeft = -1;
            updatePos();
        }

        public void leftSide(){
            isLeft = 1;
            updatePos();
        }

        public void updatePos(){
            setPos(new Point( isLeft*getPos().getX(), 0));
        }

        public void referenceBody(Dimension bodyDimensions){
            Dimension skidDimensions = new Dimension();
            skidDimensions.setHeight(5*bodyDimensions.getHeight()/3);
            skidDimensions.setWidth(bodyDimensions.getWidth()/6);
            setDimensions(skidDimensions);

            setPos(new Point(bodyDimensions.getWidth()/2, 0));
        }

        @Override
        protected void drawHelicopterComponent(Graphics context,
                                            Point parentOrigin,
                                            Point screenOrigin){
            Transform transform = Transform.makeIdentity();
            context.getTransform(transform);
            transform.scale(isLeft,1);
            context.setTransform(transform);

            context.fillRect(3*getWidth()/4,
                    -getHeight()/2,getWidth()/2,getHeight());

            context.setColor(ColorUtil.GRAY);
            context.drawRect(0,0,3*getWidth()/4,
                    getHeight()/8,10);


            transform.scale(isLeft,1);
            context.setTransform(transform);
        }

    }

    private class HelicopterFins extends HelicopterComponent{
        int finBaseWidth;

        public HelicopterFins(int color){
            super(color);
            finBaseWidth = 100;

        }

        public void setFinBase(int width){
            finBaseWidth = width;
            setDimensions(new Dimension(width*4,width*8));
        }

        @Override
        protected void drawHelicopterComponent(Graphics context,
                                            Point parentOrigin,
                                            Point screenOrigin){

            //first we build out the vertical stabilizer
            context.drawRect(-finBaseWidth/2,-getHeight(),
                    finBaseWidth,getHeight());
            context.drawLine(0,0,-finBaseWidth/2,-getHeight()/2);
            context.drawLine(0,0,finBaseWidth/2,-getHeight()/2+5);
            context.drawLine(-finBaseWidth/2,-getHeight()/2, 0,
                    -getHeight());
            context.drawLine(finBaseWidth/2,-getHeight()/2-5, 0,
                    -getHeight());

            //then we build out the horizontal stabilizer
            context.drawLine(-finBaseWidth/2,0,-getWidth(),
                    -getHeight()/2);
            context.drawLine(-getWidth(),
                    -getHeight()/2,-getWidth(),
                    -getHeight());
            context.drawLine(-getWidth(),
                    -getHeight(),
                    -finBaseWidth/2,
                    -getHeight());

            context.setColor(ColorUtil.GRAY);
            context.drawRect(finBaseWidth,-getHeight(),
                    finBaseWidth/4,getHeight(),5);

        }



    }

    private class HelicopterRotor extends HelicopterComponent{

        protected float rotorSpeed;
        protected float maxSpeed;

        public HelicopterRotor(int color){
            super(color);
            rotorSpeed = 0f;
            maxSpeed = 3f;
            setRotation(45);
        }

        public void setRadius(int radius){
            setDimensions(new Dimension(radius*2,radius/20));
        }

        public void incrementRotorSpeed(){
            rotorSpeed += 0.2f;
            if(rotorSpeed>maxSpeed){
                rotorSpeed = maxSpeed;
            }
        }

        public void decrementRotorSpeed(){
            rotorSpeed -= 0.1f;
            if(rotorSpeed<0f){
                rotorSpeed = 0;
            }
        }

        public boolean isSpunUp(){
            return(rotorSpeed>1.7f);
        }

        public boolean isSpinning() { return(rotorSpeed > 0);}

        protected void spinRotor(){
            incrementRotation(rotorSpeed);
        }

        @Override
        public void update(){
            spinRotor();
        }

        protected void drawHelicopterComponent(Graphics context,
                                            Point parentOrigin,
                                            Point screenOrigin){
            spinRotor();
            //cn1ForwardPrimitiveTranslate(context,getDimensions());
            //context.setColor(ColorUtil.WHITE);
            context.fillRect(-getWidth()/2,-2*getHeight()-5,
                    getWidth(),
                    getHeight());
            //cn1ReversePrimitiveTranslate(context,getDimensions());
            context.setColor(ColorUtil.LTGRAY);
            context.drawArc(-10,-2*getHeight(),
                            20,20,
                            0,360);
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
        init(initialPos, ColorUtil.YELLOW);
    }

    public void init(Point initialPos, int color){

        this.waterLevel = this.MAX_WATER;
        this.fuelLevel = this.MAX_FUEL;

        this.setPos(initialPos);

        this.setHeading(0);
        this.setSpeed(0);
        setColor(color);

        helicopterComponents = new ComponentCollection();
        helicopterComponents.add(new HelicopterBody(getColor().getValue()));
        HelicopterRotor rotor =
                new HelicopterRotor(getColor().getValue());
        rotor.setPos(new Point(0,0));
        rotor.setRadius(600);
        helicopterComponents.add(rotor);

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

    public void spinRotorUp(){
        for(Component part: getComponents()){
            if(part instanceof HelicopterRotor){
                ((HelicopterRotor)part).incrementRotorSpeed();
            }
        }
    }

    public void spinRotorDown(){
        for(Component part: getComponents()){
            if(part instanceof HelicopterRotor){
                ((HelicopterRotor)part).decrementRotorSpeed();
            }
        }
    }

    public boolean isReady(){
        for(Component part: getComponents()){
            if(part instanceof HelicopterRotor){
                return((HelicopterRotor)part).isSpunUp();
            }
        }
        return(false);
    }

    public boolean isRunning(){
        for(Component part: getComponents()){
            if(part instanceof HelicopterRotor){
                return((HelicopterRotor)part).isSpinning();
            }
        }
        return(false);
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
    protected void localDraw(Graphics context, Point parentOrigin,
                             Point screenOrigin){

        //Everything about the helicopter will be big to begin with
        // for designing, but once things are finalized, the
        // helicopter will be scaled down via hard code.
        float scaleFactor = 8f;

        scaleTransform(context,1/scaleFactor, 1/scaleFactor);

        for(Component c: helicopterComponents){
            c.draw(context,new Point(0,0),screenOrigin);
        }

        scaleTransform(context,scaleFactor, scaleFactor);
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
