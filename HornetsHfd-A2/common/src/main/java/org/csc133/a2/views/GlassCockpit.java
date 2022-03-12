package org.csc133.a2.views;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.mig.Grid;
import org.csc133.a2.GameWorld;

import java.util.ArrayList;

public class GlassCockpit extends Container {

    private GameWorld gameWorld;

    private ArrayList<HudSegment> hudSegments;

    public GlassCockpit(GameWorld referenceOfGameWorld){

        gameWorld = referenceOfGameWorld;
        this.setLayout(new GridLayout(1,7));
        
        hudSegments = new ArrayList<>();

        hudSegments.add(new ViewHeading(gameWorld));
        hudSegments.add(new ViewSpeed(gameWorld));
        hudSegments.add(new ViewFuel(gameWorld));
        hudSegments.add(new ViewFires(gameWorld));
        hudSegments.add(new ViewFireSize(gameWorld));
        hudSegments.add(new ViewDamage(gameWorld));
        hudSegments.add(new ViewLoss(gameWorld));

        for(HudSegment segment : hudSegments){
            this.add(segment);
        }
    }

    public void update(){
        this.repaint();
    }

    @Override
    public void paint(Graphics context){
        super.paint(context);

        for(HudSegment segment: hudSegments){
            segment.update();
        }

    }

}
