package org.csc133.a2.gameobjects;

import org.csc133.a2.gameobjects.Fixed.Building;
import org.csc133.a2.gameobjects.Fixed.Fire;

import java.util.ArrayList;

//[TODO] implement iterables for this collection, it's not going to
// be as easy as I had hoped.....
public class GameObjectCollection {

    private ArrayList<GameObject> gameObject;

    public GameObjectCollection(){
        gameObject = new ArrayList();
    }

    public GameObjectCollection(ArrayList<GameObject> existingList){
        for(GameObject go : existingList){
            gameObject.add(go);
        }
    }

    public ArrayList<Building> getBuildings(){
        //[TODO] iterable for buildings
        return new ArrayList<>();
    }

    public ArrayList<Fire> getFires(){
        //[TODO] iterable for fire
        return new ArrayList<>();
    }

    public void add(GameObject objectAddition){
        gameObject.add(objectAddition);
    }



}
