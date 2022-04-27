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

    @Override
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

    public int getDamage(){
        int damage = 0;
        for(Building structure: gameObjects){
            damage += structure.getBurnAmount();
        }

        return(damage);
    }

    public int getInitialValues(){
        int value = 0;

        for(Building structure: gameObjects){
            value += structure.getInitialValue();
        }

        return(value);
    }
}
