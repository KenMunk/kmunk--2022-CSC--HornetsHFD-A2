package org.csc133.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a2.GameWorld;

public class TurnRight extends Command {

    GameWorld gw;

    public TurnRight(GameWorld referenceWorld){
        super("TurnRight");
        gw = referenceWorld;
    }

    @Override
    public void actionPerformed(ActionEvent event){
        gw.turnHelicopterRight();
    }

}