package org.csc133.a2.interfaces;

import org.csc133.a2.gameobjects.collections.FireCollection;
import org.csc133.a2.gameobjects.Fixed.Building;

public interface BuildingBurnState {

    public FireCollection sparkUpdate(Building context);

}
