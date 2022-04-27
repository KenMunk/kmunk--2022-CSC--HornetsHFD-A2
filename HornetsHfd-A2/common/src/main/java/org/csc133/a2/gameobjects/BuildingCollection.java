package org.csc133.a2.gameobjects;

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

    public int getTotalDamage(){
        int damage = 0;
        for(Building structure: gameObjects){
            damage += structure.getBurnAmount();
        }

        return(damage);
    }

    public int getTotalSize(){
        int size = 0;
        for(Building structure: gameObjects){
            size += structure.getSize();
        }
        return size;
    }

    public int getInitialValues(){
        int value = 0;

        for(Building structure: gameObjects){
            value += structure.getInitialValue();
        }

        return(value);
    }

    public FireCollection igniteAll(){
        FireCollection regionalFires = new FireCollection();

        for(Building structure: gameObjects){
            Fire buildingFire = new Fire();
            structure.setFireInBuilding(buildingFire);
            regionalFires.add(buildingFire);
        }

        return regionalFires;
    }
}
