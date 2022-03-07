package org.csc133.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a2.GameWorld;

public class Fight extends Command {

    GameWorld gw;

    public Fight(GameWorld referenceWorld){
        super("Fight");
        gw = referenceWorld;
    }

    @Override
    public void actionPerformed(ActionEvent event){
        gw.helicopterDump();
    }

}
