package org.csc133.a2;

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
import com.codename1.ui.util.UITimer;
import com.codename1.util.MathUtil;
//import org.jetbrains.annotations.NotNull;

//import java.math.RoundingMode;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import org.csc133.a2.interfaces.GameState;

//using singleton design

public class GameWorld {

    private static GameWorld gameWorldInstance = null;

    private GameState gameState;

    // [TODO] Add in the components of the gameworld

    private GameWorld(){


        // [TODO] Add the deployments here

    }

    public static GameWorld getInstance(){

        if(gameWorldInstance == null){
            gameWorldInstance = new GameWorld();
        }
        return(gameWorldInstance);
    }

}
