package org.csc133.a2.gameobjects.collections;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.Fixed.BuildingSystem.Building;

import java.util.Random;

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

        while(regionalFires.getFireSize() < 1000){
            for(Building structure: gameObjects){
                regionalFires.add(structure.sparkUpdate());
                regionalFires.add(structure.sparkUpdate());
                updateBurns(regionalFires);
            }
        }

        return regionalFires;
    }

    public void sparkUpdate(FireCollection allFires){
        for(Building structure: gameObjects){
            int sparkRoll = new Random().nextInt(10000);
            if(sparkRoll<3){
                allFires.add(structure.sparkUpdate());
            }

        }
    }

}
