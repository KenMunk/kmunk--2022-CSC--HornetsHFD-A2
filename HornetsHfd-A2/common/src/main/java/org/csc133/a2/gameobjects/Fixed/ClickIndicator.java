package org.csc133.a2.gameobjects.Fixed;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.Components.CircleSolid;
import org.csc133.a2.gameobjects.Components.ComponentNode;

public class ClickIndicator extends ComponentNode {

    public ClickIndicator(Point location){
        setPos(location);
        getComponents().add(new CircleSolid(ColorUtil.CYAN,
                new Dimension(10,10)));
    }

}
