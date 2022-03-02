package org.csc133.a2.states;

import org.csc133.a2.gameobjects.Fire;
import org.csc133.a2.interfaces.FireState;

public class FireIsExtinguished implements FireState {

    @Override
    public void fireUpdate(Fire context) {
        //Do nothing, nothing can happen here
    }
}
