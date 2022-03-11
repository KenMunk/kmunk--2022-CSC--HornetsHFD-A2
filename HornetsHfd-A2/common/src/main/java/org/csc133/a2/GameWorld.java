package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.system.Lifecycle;
import com.codename1.ui.*;
//import com.codename1.ui.layouts.*;
//import com.codename1.io.*;
//import com.codename1.ui.plaf.*;
//import com.codename1.ui.util.Resources;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.util.UITimer;
import com.codename1.util.MathUtil;
//import org.jetbrains.annotations.NotNull;

//import java.math.RoundingMode;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import org.csc133.a2.gameobjects.*;
import org.csc133.a2.interfaces.GameState;
import org.csc133.a2.states.IsBurning;

//using singleton design

public class GameWorld {

    private static final GameWorld gameWorldInstance = new GameWorld();

    private GameState gameState;

    private ArrayList<GameObject> gameObject;

    // [TODO] Add in the components of the gameworld

    private GameWorld(){

        this.init();

        // [TODO] Add the deployments here

    }



    private void init(){

        gameObject = new ArrayList<>();

        Display thisDisplay = Display.getInstance();
        int maxX = thisDisplay.getDisplayWidth();
        int maxY = thisDisplay.getDisplayHeight();

        Helipad helipad = new Helipad
        (
            new Point
            (
                maxX/2,
                maxY-(2*maxY/5)
            )
        );

        River aRiver = new River(new Point(-50,300));

        gameObject.add(aRiver);
        gameObject.add(helipad);
        gameObject.add
        (
            new Building
            (
                new Point(maxX/10, maxY/3),
                new Dimension(maxY/6, maxY/4)
            )
        );
        gameObject.add
        (
            new Building
            (
                new Point(maxX - 2*maxX/10, maxY/3),
                new Dimension(maxY/6, maxY/4)
            )
        );
        gameObject.add
        (
            new Building
            (
                new Point(maxX/3, maxY/30),
                new Dimension(maxX/3, maxY/8)
            )
        );
        for(int i = 1; i<7; i++){
            Fire aFire = new Fire();
            aFire.setPos(new Point(maxX/(i+1), maxY/2));
            aFire.setState(new IsBurning());
            gameObject.add(aFire);
        }


        //add the buildings (easy)

        //add the fires

    }

    public void update(){
        for(GameObject go : gameObject){
            go.update();

            //I think I need to run the updates on a per-instance
            // basis...
        }
    }

    public ArrayList<GameObject> getGameObjects(){
        return(gameObject);
    }

    public static GameWorld getInstance(){

        return(gameWorldInstance);
    }

    public void accelerateHelicopter() {

        System.err.println
        (
            this.getClass().getSimpleName()
            + " " +
            "Accelerating"
        );

    }

    public void turnHelicopterLeft() {
        System.err.println
        (
            this.getClass().getSimpleName()
            + " " +
            "Turning Left"
        );
    }

    public void turnHelicopterRight() {
        System.err.println
        (
            this.getClass().getSimpleName()
            + " " +
            "Turning Right"
        );
    }

    public void helicopterDump() {
        System.err.println
        (
            this.getClass().getSimpleName()
                    + " " +
                    "Dumping"
        );
    }

    public void helicopterDrink() {
        System.err.println
        (
            this.getClass().getSimpleName()
            + " " +
            "Drinking"
        );
    }

    public void helicopterBrake() {
        System.err.println
        (
            this.getClass().getSimpleName()
            + " " +
            "Braking"
        );

    }

    public void exit() {
        System.err.println
        (
            this.getClass().getSimpleName()
            + " " +
            "Exiting"
        );
        Display.getInstance().exitApplication();
    }

    public int getHelicopterHeading(){

        return(0);
    }

    public int getHelicopterSpeed(){

        return(0);
    }

    public int getHelicopterFuel(){

        return(0);
    }

    public String getFireCount() {
        int fireCount = 0;
        for(GameObject go: gameObject){
            if(go instanceof Fire){
                fireCount++;
            }
        }
        return(fireCount+"");
    }

    public String getFireSize() {

        int fireSize = 0;
        for(GameObject go: gameObject){
            if(go instanceof Fire){
                Fire fire = (Fire)go;
                fireSize+=fire.getSize();
            }
        }
        return(fireSize+"");
    }

    public String getLoss() {

        return("Everything");
    }
}
