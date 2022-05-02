package org.csc133.a2.gameobjects.collections;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.Fixed.Fire;
import org.csc133.a2.gameobjects.GameObject;

public class FireCollection extends GameObjectCollection<Fire>{

    public FireCollection(){
        super();
    }

    public void draw(Graphics g, Point containerOrigin){
        for(Fire fireCircle : gameObjects){
            fireCircle.draw(g,containerOrigin);
        }
    }

    public int calculateBurns(GameObject referenceObject){
        int burns = 0;

        for(Fire fireCircle : gameObjects){
            if(referenceObject.containsPoint(fireCircle.getPos())){
                int someBurns = fireCircle.getPeakSize();
                burns+= someBurns;
            }
        }

        return(burns);
    }

    public void stopBurnsInObject(GameObject referenceObject){
        for(Fire fireCircle : gameObjects){
            if(referenceObject.containsPoint(fireCircle.getPos())){
                fireCircle.extinguish(fireCircle.getSize() + 100);
            }
        }
    }

    public int getFireSize(){
        int fireSize = 0;
        for(Fire fire : gameObjects){
            if(fire.isBurning()){
                fireSize += fire.getSize();
            }
        }

        return fireSize;
    }

    @Override
    public int size(){
        return(gameObjects.size());
    }

    public int activeFireCount(){
        int size = 0;

        for(Fire fireCircle : gameObjects){
            if(fireCircle.isBurning()){
                size++;
            }
        }

        return(size);
    }

    public int activeFireCount(GameObject context){
        int size = 0;

        for(Fire fireCircle : gameObjects){
            if(
                fireCircle.isBurning()
                &&
                context.containsPoint(fireCircle.getPos())
            ){
                size++;
            }
        }
        return(size);
    }

    public void add(FireCollection someFires){
        if(someFires.size() > 0){
            for(Fire aFire: someFires){
                add(aFire);
            }
        }
    }

    @Override
    public void update(){
        for(Fire aFire: gameObjects){
            aFire.update();
        }
    }

}
