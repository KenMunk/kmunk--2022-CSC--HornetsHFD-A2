package org.csc133.a2.gameobjects.collections;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.GameObject;

import java.util.ArrayList;
import java.util.Iterator;

public class GameObjectCollection<T extends GameObject> extends GameObject implements Iterable<T>{

    ArrayList<T> gameObjects;

    class GameObjectIterator implements Iterator<T> {
        int index = 0;

        @Override
        public boolean hasNext(){
            return(index < gameObjects.size());
        }

        @Override
        public T next(){
            return gameObjects.get(index++);
        }
    }

    public T getAt(int i){
        int x = 0;
        for(T value : gameObjects){
            if(x==i){
                return(value);
            }
            x++;
        }
        throw(new ArrayIndexOutOfBoundsException());
    }

    public GameObjectCollection(){
        gameObjects = new ArrayList<>();
    }

    public void add(T gameObject){
        gameObjects.add(gameObject);
    }

    public void add(int location, T gameObject){
        gameObjects.add(location,gameObject);
    }

    public void remove(T gameObject){
        gameObjects.remove(gameObject);
    }

    public int size(){
        return gameObjects.size();
    }

    //@Override
    public Iterator<T> iterator(){
        return new GameObjectIterator();
    }



    @Override
    public void draw(Graphics context, Point parentOrigin,
                     Point screenOrigin){
        localDraw(context, parentOrigin,screenOrigin);

    }

    @Override
    protected void localDraw(Graphics context, Point parentOrigin,
                             Point screenOrigin){
        if(size() > 0){

            for(T object: gameObjects) {
                object.draw(context, new Point(0,0), screenOrigin);
                //System.out.println("Attempting to draw");
            }
        }
    }

    @Override
    public void update(){
        if(size() > 0){

            for(T object: gameObjects) {
                object.update();
            }
        }

    }


    @Override
    public void pointerPressed(Point location){
        for(T entry : gameObjects){
            entry.pointerPressed(location);
        }
    }

}
