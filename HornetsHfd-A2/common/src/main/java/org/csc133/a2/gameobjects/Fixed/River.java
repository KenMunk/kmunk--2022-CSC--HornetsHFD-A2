package org.csc133.a2.gameobjects.Fixed;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.Components.BoxOutline;
import org.csc133.a2.gameobjects.Components.Component;

public class River extends Fixed{

    private static River riverInstance;

    private int width;

    private River(Point origin){

        //I'm going to give the river a fixed position with
        //respect to the screen but the lower bounds of
        //thickness will be dynamic out of fairness.
        //
        this.setPos(origin);
        this.updateWidth();
        initComponents();

        BoxOutline riverOutline = new BoxOutline(ColorUtil.BLUE,
                getDimensions(),10);
        riverOutline.setPos(new Point(0,0));
        getComponents().add(riverOutline);

    }

    public static River getInstance(Point origin){
        if(riverInstance == null){
            riverInstance = new River(origin);
        }
        return(riverInstance);
    }

    public static River getInstance(){
        return(getInstance(new Point(1920/2,3*1440/5)));
    }

    private void updateWidth(){
        int displayHeight = 1440;

        int tempWidth = (displayHeight/10);
        this.width = tempWidth;
        setDimensions(new Dimension(1920,width));
    }

    public boolean containsPoint(Point reference){
        return(
                reference.getY() >= this.getPos().getY()-(getHeight()/2)
                        &&
                        reference.getY() <= this.getPos().getY()+(getHeight()/2)
        );
    }

}
