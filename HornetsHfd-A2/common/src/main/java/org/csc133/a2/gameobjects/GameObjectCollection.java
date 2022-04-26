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
        ArrayList<Building> returnType = new ArrayList<>();
        for(GameObject go : gameObject){
            if(go instanceof Building){
                returnType.add((Building)go);
            }
            go.update();
        }

        return(returnType);
    }

    public ArrayList<Fire> getFires(){
        ArrayList<Fire> returnType = new ArrayList<>();
        for(GameObject go : gameObject){
            if(go instanceof Fire){
                returnType.add((Fire)go);
            }
        }

        return(returnType);
    }

    public void add(GameObject objectAddition){
        gameObject.add(objectAddition);
    }



}
