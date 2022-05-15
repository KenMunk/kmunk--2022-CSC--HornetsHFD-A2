package org.csc133.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a2.GameWorld;
import org.csc133.a2.gameobjects.Movables.PlayableHelicopter;
import org.csc133.a2.views.ControlCluster;

import javax.naming.ldap.Control;

public class ToggleEngine extends Command {

    ControlCluster thisPanel;
    boolean currentState;

    public ToggleEngine(ControlCluster controlPanel){
        super("Toggle");
        thisPanel = controlPanel;
        currentState = false;
    }

    @Override
    public void actionPerformed(ActionEvent event){
        PlayableHelicopter.getInstance().toggleEngine();
        if (currentState !=
                PlayableHelicopter.getInstance().isPowered()){
            currentState = PlayableHelicopter.getInstance().isPowered();
            thisPanel.engineButtonSwitch(currentState);
        }
    }
}
