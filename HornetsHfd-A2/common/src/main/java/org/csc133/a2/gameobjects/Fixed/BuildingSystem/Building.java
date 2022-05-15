package org.csc133.a2.gameobjects.Fixed.BuildingSystem;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.util.MathUtil;
import org.csc133.a2.gameobjects.Components.BoxOutline;
import org.csc133.a2.gameobjects.Fixed.FireSystem.Fire;
import org.csc133.a2.gameobjects.Fixed.Fixed;
import org.csc133.a2.gameobjects.collections.FireCollection;
import org.csc133.a2.interfaces.BuildingBurnState;

import java.util.Random;

public class Building extends Fixed {

    private int initialValue;
    private int burnAmount;

    private BuildingBurnState burnState;

    public Building(Point origin, Dimension dimensions){

        setPos(origin);
        setDimensions(dimensions);
        setColor(ColorUtil.rgb(255,0,0));
        setInitialValue(getSize());
        setBurnState(new BuildingNotBurningYet());

        initComponents();
        BoxOutline buildingPerimeter =
                new BoxOutline(getColor().getValue(),
                        getDimensions(), 5);
        getComponents().add(buildingPerimeter);

    }

    private void setInitialValue(int value){

        initialValue = value;

    }

    public int getInitialValue(){

        return(initialValue);

    }

    public int getSize(){

        return((getHeight() * getWidth())/10);

    }

    /*
    public int calculateBurns(ArrayList<GameObject> potentialFires){
        int burns = 0;

        //[TODO] come back for review
        for(GameObject pF: potentialFires){

            if(pF instanceof Fire){

                if(containsPoint(pF.getPos())){

                    Fire aFire = (Fire)pF;
                    int someBurns = aFire.getPeakSize();
                    burns+=someBurns;

                }

            }

        }

        if(burns > getSize()){
            stopAllBurns(potentialFires);
        }

        return(burns);
    }//*/

    private void stopAllBurns(FireCollection fireCollection){

        fireCollection.stopBurnsInObject(this);
    }

    private void burnCheck(FireCollection fireCollection){
        if(fireCollection.activeFireCount(this) == 0){
            if(!(burnState instanceof BuildingNotBurningYet)){
                setBurnState(new BuildingDoneBurning());
            }
        }
    }

    public void updateBurns(FireCollection fireCollection){
        burnCheck(fireCollection);

        burnAmount = fireCollection.calculateBurns(this)*13;
        if(burnAmount > getSize()){
            stopAllBurns(fireCollection);
            setBurnState(new BuildingDoneBurning());
            burnAmount = getSize();
        }
    }

    public int getBurnAmount(){
        return(burnAmount);
    }

    public int getDestroyPercentage(){
        float amountBurned = getBurnAmount();
        float buildingSize = getSize();
        int destroyedPercentage =
        MathUtil.round(
            100
            *
            (amountBurned / buildingSize)
        );
        return(destroyedPercentage);
    }

    protected void setFireInBuilding(Fire fire){
        int fireX =
                new Random().nextInt(getWidth()) - (getWidth()/2);
        int fireY =
                new Random().nextInt(getHeight()) - (getHeight()/2);

        fireX += getPos().getX();
        fireY += getPos().getY();

        System.out.println("Reference building dimensions = " + getDimensions().toString());
        System.out.println("Reference building position = " + getPos());
        System.out.println("Fire spawned at location (" + fireX +
                ", " +fireY + ")");

        fire.setPos(new Point(fireX,fireY));
        fire.start();
    }

    private void setBurnState(BuildingBurnState nextState){
        burnState = nextState;
    }

    public FireCollection sparkUpdate(){
        return(burnState.sparkUpdate(this));
    }

}
