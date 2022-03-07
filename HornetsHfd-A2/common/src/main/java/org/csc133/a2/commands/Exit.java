package org.csc133.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a2.GameWorld;

public class Exit extends Command {

    GameWorld gw;

    public Exit(GameWorld referenceWorld){
        super("Exit");
        gw=referenceWorld;
    }

    public void actionPerformed(ActionEvent event){
        gw.exit();
    }

}
