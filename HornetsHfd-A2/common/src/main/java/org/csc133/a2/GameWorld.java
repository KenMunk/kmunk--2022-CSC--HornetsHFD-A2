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
import org.csc133.a2.interfaces.GameDialogState;
import org.csc133.a2.interfaces.GameState;
import org.csc133.a2.states.*;

//using singleton design

public class GameWorld {

    private static final GameWorld gameWorldInstance = new GameWorld();

    private GameState gameState;

    private GameDialogState dialogState;

    private ArrayList<GameObject> gameObject;

    private GameWorld(){

        this.init();

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
                maxY - (2*maxY/6)
            )
        );

        Helicopter player = new Helicopter(helipad);

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

        for(int i = 0; i<997; i++){
            gameObject.add(gameObject.size()-2,new Fire());

        }

        gameObject.add(player);
        gameState = new GamePlaying();
        closeDialog();

    }

    public void update(){

        gameState.update(this);

    }

    public void updateBurns(){
        for(GameObject go : gameObject){
            if(go instanceof Building){
                ((Building)go).updateBurns(gameObject);
            }
            go.update();
        }
    }

    public ArrayList<GameObject> getGameObjects(){

        return(gameObject);
    }

    public static GameWorld getInstance(){

        return(gameWorldInstance);
    }

    public Helicopter getPlayer(){
        Helicopter playerHelicopter;

        //First helicopter found is player helicopter

        playerHelicopter = null;

        for(GameObject go : gameObject){
            if(go instanceof Helicopter){
                playerHelicopter = ((Helicopter) go);
                break;
            }
        }

        return(playerHelicopter);
    }
    
    public Helipad getHelipad(){
        Helipad playerHelipad;

        //First Helipad found is player Helipad

        playerHelipad = null;

        for(GameObject go : gameObject){
            if(go instanceof Helipad){
                playerHelipad = ((Helipad) go);
                break;
            }
        }

        return(playerHelipad);
    }

    public void accelerateHelicopter() {

        System.err.println
        (
            this.getClass().getSimpleName()
            + " " +
            "Accelerating"
        );

        getPlayer().toAccelerate();

    }

    public void turnHelicopterLeft() {
        System.err.println
        (
            this.getClass().getSimpleName()
            + " " +
            "Turning Left"
        );

        getPlayer().toTurnLeft();
    }

    public void turnHelicopterRight() {
        System.err.println
        (
            this.getClass().getSimpleName()
            + " " +
            "Turning Right"
        );

        getPlayer().toTurnRight();
    }

    public void helicopterDump() {
        System.err.println
        (
            this.getClass().getSimpleName()
                    + " " +
                    "Dumping"
        );
        getPlayer().fight(gameObject);
    }

    public void helicopterDrink() {
        System.err.println
        (
            this.getClass().getSimpleName()
            + " " +
            "Drinking"
        );
        getPlayer().toDrink();
    }

    public void helicopterBrake() {
        System.err.println
        (
            this.getClass().getSimpleName()
            + " " +
            "Braking"
        );

        getPlayer().toSlowDown();
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

    public double getHelicopterHeading(){
        double heading = getPlayer().getHeading();
        return(heading);
    }

    public double getHelicopterSpeed(){
        double playerSpeed = getPlayer().getSpeed();
        return(playerSpeed);
    }

    public int getHelicopterFuel(){
        int fuelLevel = getPlayer().getFuel();
        return(fuelLevel);
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

        return(buildingDamage);
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

        int lossPercentage = MathUtil.round(100 * (((float)totalDamage)/totalSize ));

        if(lossPercentage>=100){
            gameState = new GameLoss();
            openDialog();
        }
        return(lossPercentage + "%");
    }

    public void calculateBuildingBurns(){
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

    //this part is wrong, see spec
    public void sendSparks(){
        if(getFireCount() > 0){
            for(int i = 0; i<gameObject.size(); i++){
                if(gameObject.get(i) instanceof Building){
                    Building burningBuilding = ((Building)gameObject.get(i));

                    int sparkRoll = new Random().nextInt(5000);

                    if(sparkRoll<3){
                        igniteFire(burningBuilding);
                    }
                }
            }
        }
    }

    private void igniteFire(Building target){
        for(int i = 0; i<gameObject.size(); i++){
            if(gameObject.get(i) instanceof Fire){
                if(((Fire) gameObject.get(i)).isReady()){
                    target.setFireInBuilding((Fire)gameObject.get(i));
                    return;
                }
            }
        }
    }

    public void startGame(){
        init();
        gameState = new GamePlaying();
    }

    public void stopGame(){
        gameState = new GameStopped();
    }

    public void continueGame(){
        gameState = new GamePlaying();
    }

    public void winGame(){
        gameState = new GameWin();
    }

    public void pauseToggle(){
        if(gameState instanceof GamePlaying){
            gameState = new GameStopped();
            openDialog();
        }
        else if(gameState instanceof GameStopped){
            gameState = new GamePlaying();
            closeDialog();
        }
    }

    public void updateHelicopter(){
        getPlayer().move();
        getPlayer().burnFuel();
        getPlayer().riverCheck(gameObject);
    }

    public int getHelicopterWater() {
        return(getPlayer().getWater());
    }

    public int getPoints() {

        int buildingValue = 0;

        for(GameObject go: gameObject){
            if(go instanceof Building){
                Building aBuilding = (Building)go;
                buildingValue += aBuilding.getInitialValue();

            }
        }

        return(buildingValue - getFireDamage());

    }

    public GameState getGameState(){
        return(gameState);
    }
    
    public void helicopterLandingCheck(){
        if
        (
            (getFireCount() == 0)
            &&
            (getHelipad().holdsHelicopter(getPlayer()))
        ){
            gameState = new GameWin();
            openDialog();
        }
            
            
    }

    public void closeDialog(){
        dialogState = new GameDialogInactive();
    }

    public void openDialog(){
        if(dialogState instanceof GameDialogInactive){
            dialogState = new GameDialogActive(this);
        }
    }
}
