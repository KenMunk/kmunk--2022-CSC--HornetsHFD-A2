package org.csc133.a2.gameobjects;

import org.csc133.a2.gameobjects.Fixed.Building;
import org.csc133.a2.gameobjects.Fixed.Fire;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameObjectCollection<T> extends GameObject implements Iterable<T>{

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


}
