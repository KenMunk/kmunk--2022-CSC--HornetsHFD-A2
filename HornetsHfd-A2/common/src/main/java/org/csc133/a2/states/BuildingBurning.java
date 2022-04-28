package org.csc133.a2.states;

import org.csc133.a2.gameobjects.collections.FireCollection;
import org.csc133.a2.gameobjects.Fixed.Building;
import org.csc133.a2.gameobjects.Fixed.Fire;
import org.csc133.a2.interfaces.BuildingBurnState;

public class BuildingBurning implements BuildingBurnState {

    public FireCollection sparkUpdate(Building context){
        FireCollection moreFires = new FireCollection();


        Fire freshFire = new Fire();
        context.setFireInBuilding(freshFire);
        moreFires.add(freshFire);

        return(moreFires);
    }

}
