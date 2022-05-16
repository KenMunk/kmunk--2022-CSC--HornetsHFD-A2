package org.csc133.a2.gameobjects.Movables;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Point;
import com.codename1.util.MathUtil;
import org.csc133.a2.Game;
import org.csc133.a2.GameWorld;
import org.csc133.a2.gameobjects.Fixed.FireSystem.Fire;
import org.csc133.a2.gameobjects.Fixed.Helipad;
import org.csc133.a2.gameobjects.collections.FireCollection;
import org.csc133.a2.gameobjects.collections.GameObjectCollection;
import org.csc133.a2.gameobjects.collections.WorldObjectCollection;
import org.csc133.a2.gameobjects.path.ControlGrid;
import org.csc133.a2.gameobjects.path.Navigator;

import java.util.Random;

public class UnPlayableHelicopter extends Helicopter{

    protected static UnPlayableHelicopter player;
    boolean proxAlert;

    private UnPlayableHelicopter(Point initialPos) {
        super(initialPos);
        init(initialPos, ColorUtil.GREEN);
        engineState = new HelicopterEngineStarting();
        proxAlert = false;
        toTurnLeft();
        setSpeed(2);
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

    private void processFlightPath(){
        Point waypoint = Navigator.getInstance().getWaypoint(getPos());

        Point dif = new Point(0,0);
        dif.setX(waypoint.getX()-getPos().getX());
        dif.setY(waypoint.getY()-getPos().getY());
        int code = 00;
        if(dif.getX()>=0){
            code += 10;
        }
        if(dif.getY()>=0){
            code += 1;
        }
        double distance = dif.getX()*dif.getX();
        distance += dif.getY()*dif.getY();
        distance = Math.sqrt(distance);

        double wayHeading = 0;
        double helicopterHeading = getHeading();

        if(distance > 10){

            if(Math.abs(dif.getX())<0.1f){
                if(dif.getY() < 0){
                    wayHeading = 270;
                }
                else{
                    wayHeading = 90;
                }
            }
            else if(Math.abs(dif.getY())<0.1f){
                if(dif.getX() < 0){
                    wayHeading = 180;
                }
                else{
                    wayHeading = 0;
                }
            }
            else{
                double aSin = MathUtil.asin(dif.getY()/distance);
                aSin *= 180/Math.PI;
                while(aSin<0){
                    aSin+=360;
                    aSin%=360;
                }

                if(code >= 10){
                    if(code % 10 == 1){
                        while(aSin>90){
                            aSin = 180-aSin;
                        }
                    }
                    else{
                        while(aSin<270){
                            aSin = 180-aSin;
                        }
                    }
                }
                else{
                    if(code % 10 == 1){
                        while(aSin<90){
                            aSin = 180-aSin;
                        }
                    }
                    else{
                        while(aSin>270){
                            aSin = 180-aSin;
                        }
                    }
                }
                wayHeading = aSin;

            }
            wayHeading -= 90;
        }
        else{
            wayHeading = helicopterHeading;
        }

        //making it easier to mentally overlay headings
        //need to subtract 90 degrees from the resultant headings

        System.out.println("Helicopter heading = " + helicopterHeading);
        System.out.println("Heading to target = " + wayHeading);

        setHeading((float)wayHeading);
    }

    private void evasionStrategy(){

        boolean doom =
                (UnPlayableHelicopter.getInstance().radiusContainsPoint
                        (PlayableHelicopter.getInstance().getPos(),
                                100));
        if(doom){
            GameWorld.getInstance().lost();
        }

        boolean emergency =
                (UnPlayableHelicopter.getInstance().radiusContainsPoint
                        (PlayableHelicopter.getInstance().getPos(),
                                200));

        if((UnPlayableHelicopter.getInstance().radiusContainsPoint
                (PlayableHelicopter.getInstance().getPos(),
                        300)) && !proxAlert){
            proxAlert = true;
            if(emergency){
                setSpeed(10);
            }
            Point randPoint =
            ControlGrid.getInstance().getAt
                (
                    new Random().nextInt(ControlGrid.getInstance().size())
                ).getPos();
            Navigator.getInstance().addPriority(randPoint);
            Navigator.getInstance().checkFlightPriority();

        }
        else if((UnPlayableHelicopter.getInstance().radiusContainsPoint
                (PlayableHelicopter.getInstance().getPos(),
                        300))){
            if(emergency){
                setSpeed(10);
            }
        }
        else{
            proxAlert = false;
            setSpeed(2);
        }
    }

    private void flightNavigation(){

        if (Navigator.getInstance().atPriority(getPos())) {
            for(Fire f : ((WorldObjectCollection)GameWorld.
                    getInstance().
                    getGameObjectCollection()).getFires()){
                if(f.nearPosition(getPos())){
                    attackFires(((WorldObjectCollection)GameWorld.
                            getInstance().
                            getGameObjectCollection()).getFires());
                    break;
                }
            }
        }


        if(!Navigator.getInstance().firePriority()){
            WorldObjectCollection wc =
                    (WorldObjectCollection)
                            GameWorld.getInstance().getGameObjectCollection();
            FireCollection fc = wc.getFires();
            Fire destination;
            do{
                destination =
                        fc.getAt(new Random().nextInt(fc.size()));
            }while(!destination.isBurning());
            Navigator.getInstance().addLowPriority(destination.getPos());
            Navigator.getInstance().checkFlightPriority();
        }

    }

    public void flightUpdate(){
        evasionStrategy();

        if(!fuelOut()){

            processFlightPath();

            flightNavigation();

            riverCheck((GameWorld.
                    getInstance().
                    getGameObjectCollection()));
            toDrink();

            engineState = engineState.updateRotor(this);
        }
        else{
            setSpeed(0);
            engineState = new HelicopterEngineStopping();
            spinRotorDown();
        }
    }


}
