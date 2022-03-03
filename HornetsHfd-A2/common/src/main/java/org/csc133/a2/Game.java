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
import com.codename1.ui.Dialog;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.UITimer;
import com.codename1.util.MathUtil;
import org.csc133.a2.views.ControlCluster;
import org.csc133.a2.views.GlassCockpit;
import org.csc133.a2.views.MapView;
//import org.jetbrains.annotations.NotNull;

//import java.math.RoundingMode;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

//This will be our controller [Personal note]

public class Game extends Form implements Runnable{

    private GameWorld gameWorld;
    private ControlCluster bottomControlCluster;
    private GlassCockpit topGlassCockpit;
    private MapView middleMapView;

    public Game(){
        gameWorld = GameWorld.getInstance();

        bottomControlCluster = new ControlCluster(gameWorld);
        topGlassCockpit = new GlassCockpit(gameWorld);
        middleMapView = new MapView(gameWorld);

        setTitle("Hornet Fire Defense A2");

        UITimer timer = new UITimer(this);
        timer.schedule(100,true,this);

        this.setLayout(new BorderLayout());

        this.add(BorderLayout.NORTH,topGlassCockpit);
        this.add(BorderLayout.SOUTH,bottomControlCluster);
        this.add(BorderLayout.CENTER,middleMapView);

        this.show();

    }

    @Override
    public void run(){

    }

    @Override
    public void paint(Graphics context){
        super.paint(context);
    }
}
