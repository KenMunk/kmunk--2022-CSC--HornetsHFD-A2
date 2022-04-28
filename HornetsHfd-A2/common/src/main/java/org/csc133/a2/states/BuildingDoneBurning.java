package org.csc133.a2.states;

import org.csc133.a2.gameobjects.collections.FireCollection;
import org.csc133.a2.gameobjects.Fixed.Building;
import org.csc133.a2.interfaces.BuildingBurnState;

public class BuildingDoneBurning implements BuildingBurnState {

    public FireCollection sparkUpdate(Building context){
        FireCollection noMoreFires = new FireCollection();
        return(noMoreFires);
    }

}
