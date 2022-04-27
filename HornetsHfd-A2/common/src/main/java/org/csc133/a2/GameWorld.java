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
import java.util.ArrayList;
import java.util.Random;

import org.csc133.a2.gameobjects.*;
import org.csc133.a2.gameobjects.Fixed.Building;
import org.csc133.a2.gameobjects.Fixed.Fire;
import org.csc133.a2.gameobjects.Fixed.Helipad;
import org.csc133.a2.gameobjects.Fixed.River;
import org.csc133.a2.gameobjects.Movables.Helicopter;
import org.csc133.a2.interfaces.GameState;
import org.csc133.a2.states.*;

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

        Display thisDisplay = Display.getInstance();
        int maxX = thisDisplay.getDisplayWidth();
        int maxY = thisDisplay.getDisplayHeight();


        Helipad helipad = new Helipad
        (
            new Point
            (
                maxX/2,
                maxY - (2*maxY/6)
            )
        );

        Helicopter player = new Helicopter(helipad);

        River aRiver = new River(new Point(-50,300));

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
                new Point(maxX - 2*maxX/10, maxY/3),
                new Dimension(maxY/6, maxY/4)
            )
        );

        gameObjects.getBuildings().add
        (
            new Building
            (
                new Point(maxX/3, maxY/30),
                new Dimension(maxX/3, maxY/8)
            )
        );

        FireCollection allFires = new FireCollection();

        gameObjects.add(allFires);

        //ingniteAllBuildings();
        //add the fires

        /*//No Longer spec compliant
        for(int i = 0; i<997; i++){
            gameObjectCollection.add(gameObjectCollection.size()-2,new Fire());

        }//*/

        //Player will be singleton going forward
        //gameObjects.add(player);
        gameState = new GamePlaying();
        dialogState = false;
        //*/

    }

    public void update(){

        gameState.update(this);

    }

    public void updateBurns(){

        /*
        for(GameObject go : gameObjectCollection.getBuildings()){
            if(go instanceof Building){
                ((Building)go).updateBurns(gameObject);
            }
            go.update();
        }*/
        FireCollection fires = gameObjects.getFires();
        if(fires.size() > 0){
            gameObjects.getBuildings().updateBurns(gameObjects.getFires());
        }
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
        /*
        for(GameObject go : gameObject){
            if(go instanceof Helicopter){
                playerHelicopter = ((Helicopter) go);
                break;
            }
        }

         */

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
        int buildingDamage = 0;
/*
        for(GameObject go: gameObject){
            if(go instanceof Building){
                Building aBuilding = (Building)go;
                buildingDamage += aBuilding.getBurnAmount();

            }
        }

 */

        return(buildingDamage);
    }

    public int getFireSize(){
        int size = 0;

        for(GameObject go : gameObjects){
            if(go instanceof FireCollection){
                return(((FireCollection)go).getFireSize());
            }
        }

        return(size);
    }

    public String getLoss() {
        int totalSize = 0;
        int totalDamage = 0;
/*
        for(GameObject go: gameObject){
            if(go instanceof Building){
                Building aBuilding = (Building)go;
                totalSize += aBuilding.getSize();
                totalDamage  += aBuilding.getBurnAmount();
            }
        }

 */

        int lossPercentage = MathUtil.round(100 * (((float)totalDamage)/totalSize ));

        if(lossPercentage>=100){
            gameState = new GameLoss();
            openDialog();
        }
        return(lossPercentage + "%");
    }

    public void calculateBuildingBurns(){
        /*
        for(GameObject go: gameObject){
            if(go instanceof Building){
                ((Building)go).updateBurns(gameObject);
            }
        }//*/

        /*//    [Failure] reference chain is broken
        //      Need to make the iterable
        ArrayList<Building> buildingsAvailable =
                gameObjectCollection.getBuildings();

        if(buildingsAvailable.size() > 0){
            for(Building b: buildingsAvailable){
                b.updateBurns(gameObject);
            }
        }//*/

        gameObjects.getBuildings().updateBurns(gameObjects.getFires());
    }

    private void ingniteAllBuildings(){
        /*int startingGOLength = gameObject.size();
        for(int i = 0; i<startingGOLength; i++){
            if(gameObject.get(i) instanceof Building){
                Building aBuilding = (Building)gameObject.get(i);
                Fire aBuildingFire = new Fire();
                aBuilding.setFireInBuilding(aBuildingFire);
                gameObject.add(aBuildingFire);
            }
        }
        //*/


    }

    //this part is wrong, see spec
    public void sendSparks(){
        /*
        if(getFireCount() > 0){
            for(int i = 0; i<gameObject.size(); i++){
                if(gameObject.get(i) instanceof Building){
                    Building burningBuilding = ((Building)gameObject.get(i));

                    int sparkRoll = new Random().nextInt(2000);
                    if(sparkRoll<3){
                        igniteFire(burningBuilding);
                    }
                }
            }
        }

         */
    }

    private void igniteFire(Building target){
        /*
        for(int i = 0; i<gameObject.size(); i++){
            if(gameObject.get(i) instanceof Fire){
                if(((Fire) gameObject.get(i)).isReady()){
                    target.setFireInBuilding((Fire)gameObject.get(i));
                    return;
                }
            }
        }
        */
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

        int buildingValue = 0;

        /*
        for(GameObject go: gameObject){
            if(go instanceof Building){
                Building aBuilding = (Building)go;
                buildingValue += aBuilding.getInitialValue();

            }
        }
        //*/

        /*//This passes for now, but it has too big of a vunerability
        for(Building b : gameObjectCollection.getBuildings() ){
            buildingValue += b.getInitialValue();
        }//*/

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
}
