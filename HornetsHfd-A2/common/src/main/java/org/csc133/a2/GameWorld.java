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

        ingniteAllBuildings();
        //add the fires

    }

    public void update(){
        for(GameObject go : gameObject){
            if(go instanceof Building){
                ((Building)go).updateBurns(gameObject);
            }
            go.update();
        }
        calculateBuildingBurns();
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

    public int getFireCount() {

        int fireCount = 0;

        for(GameObject go: gameObject){
            if(go instanceof Fire){
                Fire someFire = (Fire)go;
                if(someFire.isBurning()){
                    fireCount++;
                }
            }
        }

        return(fireCount);
    }

    public int getFireSize() {

        int fireSize = 0;

        for(GameObject go: gameObject){
            if(go instanceof Fire){
                Fire someFire = (Fire)go;
                if(someFire.isBurning()){
                    fireSize += someFire.getSize();
                }
            }
        }

        return(fireSize);

    }

    public int getFireDamage() {
        int buildingDamage = 0;

        for(GameObject go: gameObject){
            if(go instanceof Building){
                Building aBuilding = (Building)go;
                buildingDamage += aBuilding.getBurnAmount();

            }
        }

        return(buildingDamage * 300);
    }

    public String getLoss() {
        int totalSize = 0;
        int totalDamage = 0;

        for(GameObject go: gameObject){
            if(go instanceof Building){
                Building aBuilding = (Building)go;
                totalSize += aBuilding.getSize();
                totalDamage  += aBuilding.getBurnAmount();
            }
        }

        //Initial value will have to be calculated based
        //of an initial pixel area to a value within
        //the bounds set by the project
        //If said value is based off of a standard rate
        //between all buildings that would mean
        //that the percentage loss would be easier to compute
        return("Everything");
    }

    private void calculateBuildingBurns(){
        for(GameObject go: gameObject){
            if(go instanceof Building){
                ((Building)go).updateBurns(gameObject);
            }
        }
    }

    private void ingniteAllBuildings(){
        int startingGOLength = gameObject.size();
        for(int i = 0; i<startingGOLength; i++){
            if(gameObject.get(i) instanceof Building){
                Building aBuilding = (Building)gameObject.get(i);
                Fire aBuildingFire = new Fire();
                aBuilding.setFireInBuilding(aBuildingFire);
                gameObject.add(aBuildingFire);
            }
        }
    }
}
