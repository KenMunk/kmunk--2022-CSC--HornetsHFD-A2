package org.csc133.a2.states;

import org.csc133.a2.gameobjects.collections.FireCollection;
import org.csc133.a2.gameobjects.Fixed.Building;
import org.csc133.a2.gameobjects.Fixed.Fire;
import org.csc133.a2.interfaces.BuildingBurnState;

public class BuildingNotBurningYet implements BuildingBurnState {

    //Taking the initial ignition process away from gameworld and
    //giving it to the building instead
    public FireCollection sparkUpdate(Building context){

        FireCollection moreFire = new FireCollection();
        Fire firstFire = new Fire();
        context.setFireInBuilding(firstFire);
        moreFire.add(firstFire);
        return(moreFire);

    }
}
