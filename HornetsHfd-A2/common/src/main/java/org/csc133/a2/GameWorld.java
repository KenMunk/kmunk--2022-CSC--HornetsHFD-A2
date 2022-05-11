package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
//import com.codename1.ui.layouts.*;
//import com.codename1.io.*;
//import com.codename1.ui.plaf.*;
//import com.codename1.ui.util.Resources;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.util.MathUtil;
//import org.jetbrains.annotations.NotNull;

//import java.math.RoundingMode;

import org.csc133.a2.gameobjects.*;
import org.csc133.a2.gameobjects.Components.BoxOutline;
import org.csc133.a2.gameobjects.Components.CircleOutline;
import org.csc133.a2.gameobjects.Fixed.Building;
import org.csc133.a2.gameobjects.Fixed.Fire;
import org.csc133.a2.gameobjects.Fixed.Helipad;
import org.csc133.a2.gameobjects.Fixed.River;
import org.csc133.a2.gameobjects.Movables.Helicopter;
import org.csc133.a2.gameobjects.collections.FireCollection;
import org.csc133.a2.gameobjects.collections.GameObjectCollection;
import org.csc133.a2.gameobjects.collections.WorldObjectCollection;
import org.csc133.a2.gameobjects.path.Path;
import org.csc133.a2.interfaces.GameState;
import org.csc133.a2.states.*;

import javax.swing.*;
import java.util.Random;

//using singleton design

public class GameWorld {

    private static final GameWorld gameWorldInstance = new GameWorld();

    private GameState gameState;

    private boolean dialogState;

    WorldObjectCollection gameObjects;

    private GameWorld(){

        this.init();

    }

    private void init(){

        gameObjects = new WorldObjectCollection();

        int maxX = 1920;
        int maxY = 1440;

        Helipad helipad = new Helipad
        (
            new Point
            (
                maxX/2,
                (maxY/16)
            )
        );

        //Helicopter player = new Helicopter(helipad);

        River aRiver = new River(new Point(maxX/2,3*maxY/5));

        gameObjects.add(aRiver);


        gameObjects.add(helipad);


        gameObjects.getBuildings().add
        (
            new Building
            (
                new Point(maxX/10, maxY/3),
                new Dimension(maxY/6, maxY/4)
            )
        );

        gameObjects.getBuildings().add
        (
            new Building
            (
                new Point(maxX - (maxX/10), maxY/3),
                new Dimension(maxY/6, maxY/4)
            )
        );

        gameObjects.getBuildings().add
        (
            new Building
            (
                new Point((3+ new Random().nextInt(4))*maxX/10,
                        maxY - (maxY/10)),
                new Dimension(maxX/3, maxY/8)
            )
        );

        //*/

        FireCollection allFires = new FireCollection();

        gameObjects.add(allFires);

        /*
        Helicopter testChopper = new Helicopter(new Point(maxX/2,
                maxY/2));

        gameObjects.add(testChopper);
        //*/

        ingniteAllBuildings();

        for(int p = 0; p<5; p++){
            Path testPath = new Path(new Point(0,0));
            int getPosition = 0;
            for(int i = 0; i<50; i++){
                Path nextPoint =
                        new Path(new Point(new Random().nextInt(100),
                                new Random().nextInt(100)-30));
                getPosition += nextPoint.getPos().getX();
                if(getPosition>1920){
                    break;
                }
                Path oldPath = testPath;
                nextPoint.add(oldPath);
                testPath = nextPoint;

            }

            gameObjects.add(testPath);
        }


        //Player will be singleton going forward
        //gameObjects.add(player);
        //gameState = new GamePlaying();
        gameState = new GameTest();
        dialogState = false;
        //*/

    }

    public void update(){

        gameState.update(this);

    }

    public void updateBurns(){

        gameObjects.getFires().update();
        gameObjects.getBuildings().updateBurns(gameObjects.getFires());

    }

    public GameObjectCollection<GameObject> getGameObjects(){

        return(gameObjects);

    }

    public GameObjectCollection getGameObjectCollection(){
        return(gameObjects);
    }

    public static GameWorld getInstance(){

        return(gameWorldInstance);

    }

    public Helicopter getPlayer(){
        Helicopter playerHelicopter;
        //[TODO] return to this after making a player singleton
        //First helicopter found is player helicopter

        playerHelicopter = null;

        return(playerHelicopter);
    }
    
    public Helipad getHelipad(){
        return(gameObjects.getHelipad());
    }

    public void accelerateHelicopter() {

        //[TODO] return to this after making a player singleton
        //getPlayer().toAccelerate();

    }

    public void turnHelicopterLeft() {
        //[TODO] return to this after making a player singleton
        //getPlayer().toTurnLeft();
    }

    public void turnHelicopterRight() {
        //[TODO] return to this after making a player singleton

        //getPlayer().toTurnRight();
    }

    public void helicopterDump() {
        //[TODO] return to this after making a player singleton
        //getPlayer().fight(gameObject);
    }

    public void helicopterDrink() {
        //[TODO] return to this after making a player singleton
        //getPlayer().toDrink();
    }

    public void helicopterBrake() {
        //[TODO] return to this after making a player singleton
        //getPlayer().toSlowDown();
    }

    public void exit() {
        Display.getInstance().exitApplication();
    }

    public double getHelicopterHeading(){
        //[TODO] return to this after making a player singleton
        return(getPlayer().getHeading());
    }

    public double getHelicopterSpeed(){
        //[TODO] return to this after making a player singleton
        return(getPlayer().getSpeed());
    }

    public int getHelicopterFuel(){
        //[TODO] return to this after making a player singleton
        return(getPlayer().getFuel());
    }

    public int getFireCount() {
        int fireCount = 0;
        try{
            fireCount = gameObjects.getFires().activeFireCount();
        }
        catch(Exception e){

        }
        return(fireCount);
    }

    public int getFireDamage() {
        int buildingDamage = gameObjects.getBuildings().getTotalDamage();

        return(buildingDamage);
    }

    public int getFireSize(){
        return(gameObjects.getFires().getFireSize());
    }

    public String getLoss() {

        int totalSize = gameObjects.getBuildings().getTotalSize();
        int totalDamage =
                gameObjects.getBuildings().getTotalDamage();

        int lossPercentage = MathUtil.round
        (
            100
            *
            (((float)totalDamage)/totalSize )
        );

        if(lossPercentage>=100){
            gameState = new GameLoss();
            openDialog();
        }
        return(lossPercentage + "%");
    }

    public void calculateBuildingBurns(){
        gameObjects.getBuildings().updateBurns(gameObjects.getFires());
    }

    private void ingniteAllBuildings(){

        gameObjects.getFires().add(gameObjects.getBuildings().igniteAll());

    }

    //this part is wrong, see spec
    public void sendSparks(){
        gameObjects.getBuildings().sparkUpdate(gameObjects.getFires());
    }

    public void startGame(){
        init();
        gameState = new GamePlaying();
    }

    public void pauseToggle(){
        if(gameState instanceof GamePlaying){
            gameState = new GameStopped();
            openDialog();
        }
        else if(gameState instanceof GameStopped){
            gameState = new GamePlaying();
        }
    }

    public void updateHelicopter(){
        //[TODO] return to this after making a player singleton

        /*
        getPlayer().flightUpdate();
        if(getPlayer().fuelOut()){
            gameState = new GameLoss();
            openDialog();
        }
        //getPlayer().riverCheck(gameObject);
        //*/
    }

    public int getHelicopterWater() {
        //[TODO] return to this after making a player singleton
        return(getPlayer().getWater());
    }

    public int getPoints() {

        int buildingValue = gameObjects.getBuildings().getInitialValues();

        return(buildingValue - getFireDamage());

    }
    
    public void helicopterLandingCheck(){
        //[TODO] return to this after making a player singleton
        /*
        if
        (
            (getFireCount() == 0)
            &&
            (getHelipad().holdsHelicopter(getPlayer()))
        ){
            gameState = new GameWin();
            openDialog();
        }
        //*/
            
    }

    public void openDialog(){
        if(!dialogState){
            dialogState = true;
            launchDialog();
        }
    }
    
    public void launchDialog(){

        if
        (
                Dialog.show
                (
                        gameState.dialogTitle(),
                        gameState.dialogMessage(this),
                        gameState.yesOption(),
                        gameState.noOption()
                )
        )
        {
            gameState.dialogYes(this);
        }
        else{
            gameState.dialogNo(this);
        }
        dialogState = false;
    }

    public void draw(Graphics context, Point parentOrigin,
                     Point screenOrigin) {

        /*
        context.setColor(ColorUtil.CYAN);

        context.fillArc(0, 100,
                        100, 100,
                        0,360);
        */

        for(GameObject go : gameObjects){
            go.draw(context, parentOrigin, screenOrigin);
        }


    }

    public void setWorldDimensions(Dimension worldDimensions){
        worldDimensions = worldDimensions;
    }
}
