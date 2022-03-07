package org.csc133.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a2.GameWorld;


public class Accelerate extends Command{

    GameWorld gw;

    public Accelerate(GameWorld referenceWorld){
        super("Accel");
        gw = referenceWorld;
    }

    @Override
    public void actionPerformed(ActionEvent event){
        gw.accelerateHelicopter();
    }
}
