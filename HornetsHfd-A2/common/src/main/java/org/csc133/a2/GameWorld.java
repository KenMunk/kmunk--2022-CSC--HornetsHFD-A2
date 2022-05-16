package org.csc133.a2;

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
import org.csc133.a2.gameobjects.Fixed.BuildingSystem.Building;
import org.csc133.a2.gameobjects.Fixed.ClickIndicator;
import org.csc133.a2.gameobjects.Fixed.Helipad;
import org.csc133.a2.gameobjects.Fixed.River;
import org.csc133.a2.gameobjects.Movables.Helicopter;
import org.csc133.a2.gameobjects.Movables.PlayableHelicopter;
import org.csc133.a2.gameobjects.Movables.UnPlayableHelicopter;
import org.csc133.a2.gameobjects.collections.FireCollection;
import org.csc133.a2.gameobjects.collections.GameObjectCollection;
import org.csc133.a2.gameobjects.collections.WorldObjectCollection;
import org.csc133.a2.gameobjects.path.ControlGrid;
import org.csc133.a2.gameobjects.path.Navigator;
import org.csc133.a2.gameobjects.path.Path;
import org.csc133.a2.interfaces.GameState;
import org.csc133.a2.states.*;

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

        River aRiver = River.getInstance(new Point(maxX/2,3*maxY/5));
        gameObjects.add(aRiver);


        gameObjects.add(helipad);
        Navigator.init();
        PlayableHelicopter.spawnAt(helipad);
        UnPlayableHelicopter.spawnAt
        (
            new Point
            (
                helipad.getPos().getX()+200,
                    helipad.getPos().getY()
            )
        );


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

        ingniteAllBuildings();


        gameObjects.add(ControlGrid.getInstance());

        gameObjects.add(new ClickIndicator(new Point(0,0)));

        gameState = new GamePlaying();
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
        return(PlayableHelicopter.getInstance());
    }
    
    public Helipad getHelipad(){
        return(gameObjects.getHelipad());
    }

    public void accelerateHelicopter() {

        PlayableHelicopter.getInstance().toAccelerate();

    }

    public void turnHelicopterLeft() {
        
        PlayableHelicopter.getInstance().toTurnLeft();
    }

    public void turnHelicopterRight() {

        PlayableHelicopter.getInstance().toTurnRight();
    }

    public void helicopterDump() {
        
        PlayableHelicopter.getInstance().fight(gameObjects.getFires());
    }

    public void helicopterDrink() {
        
        PlayableHelicopter.getInstance().toDrink();
    }

    public void helicopterBrake() {
        
        PlayableHelicopter.getInstance().toSlowDown();
    }

    public void exit() {
        Display.getInstance().exitApplication();
    }

    public double getHelicopterHeading(){
        
        return(getPlayer().getHeading());
    }

    public double getHelicopterSpeed(){
        
        return(getPlayer().getSpeed());
    }

    public int getHelicopterFuel(){
        
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
        

        //*
        PlayableHelicopter.getInstance().flightUpdate();
        UnPlayableHelicopter.getInstance().flightUpdate();
        if(PlayableHelicopter.getInstance().fuelOut()){
            gameState = new GameLoss();
            openDialog();
        }
        PlayableHelicopter.getInstance().riverCheck(gameObjects);
        //*/
    }

    public int getHelicopterWater() {
        
        return(getPlayer().getWater());
    }

    public int getPoints() {

        int buildingValue = gameObjects.getBuildings().getInitialValues();

        return(buildingValue - getFireDamage());

    }

    public void lost(){
        gameState = new GameLoss();
        openDialog();
    }
    
    public void helicopterLandingCheck(){
        
        //*
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

        for(GameObject go : gameObjects){
            go.draw(context, parentOrigin, screenOrigin);
        }

        Navigator.getInstance().draw(context,parentOrigin,
                screenOrigin);
        UnPlayableHelicopter.drawSingleton(context,parentOrigin,screenOrigin);
        PlayableHelicopter.drawSingleton(context,parentOrigin,screenOrigin);


    }

    public void pointerPressed(Point location){
        for(GameObject go : gameObjects){
            go.pointerPressed(location);
        }

        boolean alreadyHasIndicator = false;
        for(GameObject go : gameObjects){
            if( go instanceof ClickIndicator){
                go.setPos(location);
                alreadyHasIndicator = true;
                break;
            }
        }
        if(!alreadyHasIndicator){
            gameObjects.add(new ClickIndicator(location));
        }

    }
}
