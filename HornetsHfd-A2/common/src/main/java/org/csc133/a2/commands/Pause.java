package org.csc133.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import org.csc133.a2.GameWorld;

public class Pause extends Command {

    GameWorld gw;

    public Pause(GameWorld gameWorld) {
        super("pause");
        gw = gameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent event){
        gw.pauseToggle();
    }
}
