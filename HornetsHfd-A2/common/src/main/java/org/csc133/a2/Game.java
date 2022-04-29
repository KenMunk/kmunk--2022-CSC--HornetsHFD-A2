package org.csc133.a2;

//import static com.codename1.ui.CN.*;

import com.codename1.charts.util.ColorUtil;
import com.codename1.system.Lifecycle;
import com.codename1.ui.*;
//import com.codename1.ui.layouts.*;
//import com.codename1.io.*;
//import com.codename1.ui.plaf.*;
//import com.codename1.ui.util.Resources;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.UITimer;
import com.codename1.util.MathUtil;
import org.csc133.a2.commands.*;
import org.csc133.a2.views.ControlCluster;
import org.csc133.a2.views.GlassCockpit;
import org.csc133.a2.views.MapView;
//import org.jetbrains.annotations.NotNull;

//import java.math.RoundingMode;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

//This will be our controller [Personal note]

public class Game extends Form implements Runnable{

    GameWorld gameWorld;
    ControlCluster bottomControlCluster;
    GlassCockpit topGlassCockpit;
    MapView middleMapView;

    public Game(){
        this.gameWorld = GameWorld.getInstance();

        bottomControlCluster = new ControlCluster(this.gameWorld);
        topGlassCockpit = new GlassCockpit(this.gameWorld);

        ViewOffsets viewOffsets = new ViewOffsets
            (
                topGlassCockpit.getHeight(),
                bottomControlCluster.getHeight()
            );

        middleMapView = new MapView(this.gameWorld,viewOffsets);


        //setTitle("Hornet Fire Defense A2");

        UITimer timer = new UITimer(this);
        timer.schedule(17,true,this);

        this.setLayout(new BorderLayout());

        this.add(BorderLayout.NORTH,topGlassCockpit);
        this.add(BorderLayout.SOUTH,bottomControlCluster);
        this.add(BorderLayout.CENTER,middleMapView);

        addKeyListener('Q', new Exit(gameWorld));
        addKeyListener('f', new Fight(gameWorld));
        addKeyListener('d', new Drink(gameWorld));
        addKeyListener('P', new Pause(gameWorld));
        addKeyListener(-91, new Accelerate(gameWorld));
        addKeyListener(-92, new Brake(gameWorld));
        addKeyListener(-93, new TurnLeft(gameWorld));
        addKeyListener(-94, new TurnRight(gameWorld));

        this.show();

    }

    @Override
    public Component getNextFocusDown(){
        return(this);
    }

    @Override
    public Component getNextFocusUp(){
        return(this);
    }

    @Override
    public Component getNextFocusLeft(){
        return(this);
    }

    @Override
    public Component getNextFocusRight(){
        return(this);
    }

    @Override
    public void run(){
        gameWorld.update();
        middleMapView.update();
        topGlassCockpit.update();
    }

    @Override
    public void paint(Graphics context){
        super.paint(context);

        //middleMapView.paint(context);
    }
}
