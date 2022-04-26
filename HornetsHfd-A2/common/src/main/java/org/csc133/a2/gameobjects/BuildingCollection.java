package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.Fixed.Building;
import org.csc133.a2.gameobjects.Fixed.Fire;

public class BuildingCollection extends GameObjectCollection<Building>{

    public BuildingCollection(){
        super();
    }

    public void draw(Graphics g, Point containerOrigin){
        for(Building structure : gameObjects){
            structure.draw(g,containerOrigin);
        }
    }

    public void updateBurns(FireCollection someFires){
        for(Building structure : gameObjects){
            structure.updateBurns(someFires);
        }
    }
}
