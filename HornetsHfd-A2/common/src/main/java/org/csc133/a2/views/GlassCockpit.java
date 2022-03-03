package org.csc133.a2.views;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import org.csc133.a2.GameWorld;

public class GlassCockpit extends Container {

    private GameWorld gameWorld;

    public GlassCockpit(GameWorld referenceOfGameWorld){

        gameWorld = referenceOfGameWorld;

    }

    @Override
    public void paint(Graphics context){
        super.paint(context);

        // [TODO] Present speed
        // [TODO] Present heading
        // [TODO] Present fuel

    }

}
