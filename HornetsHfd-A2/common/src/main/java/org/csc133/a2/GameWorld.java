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
import com.codename1.ui.geom.Point;
import com.codename1.ui.util.UITimer;
import com.codename1.util.MathUtil;
//import org.jetbrains.annotations.NotNull;

//import java.math.RoundingMode;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import org.csc133.a2.gameobjects.GameObject;
import org.csc133.a2.interfaces.GameState;

//using singleton design

public class GameWorld {

    private static final GameWorld gameWorldInstance = new GameWorld();

    private GameState gameState;

    private ArrayList<GameObject> gameObject;

    // [TODO] Add in the components of the gameworld

    private GameWorld(){


        // [TODO] Add the deployments here

    }

    private void init(){

        Display thisDisplay = Display.getInstance();

        int maxX = thisDisplay.getDisplayWidth();
        int maxY = thisDisplay.getDisplayHeight();

        //add the river (easy)

        //add the firebase (easy)

        //add the buildings (easy)

        //add the fires

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
}
