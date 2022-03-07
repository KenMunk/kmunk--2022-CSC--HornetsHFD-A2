package org.csc133.a2.commands;

import com.codename1.ui.Command;
import org.csc133.a2.GameWorld;

import java.awt.event.ActionEvent;

public class Accelerate extends Command{

    GameWorld gw;

    public Accelerate(GameWorld referenceWorld){
        super("Accelerate");
        gw = referenceWorld;
    }

    public void actionPerformed(ActionEvent event){
        gw.accelerateHelicopter();
    }
}
