package org.csc133.a2.gameobjects.collections;

import org.csc133.a2.gameobjects.Fixed.Helipad;
import org.csc133.a2.gameobjects.Fixed.River;
import org.csc133.a2.gameobjects.GameObject;

public class WorldObjectCollection extends GameObjectCollection<GameObject>{

    public WorldObjectCollection(){
        super();
    }

    public BuildingCollection getBuildings(){
        //First, check if there is an existing BuildingCollection
        //If there is an existing building collection, nothing else
        //Is to be done
        for(GameObject go: gameObjects){
            if(go instanceof BuildingCollection){
                return((BuildingCollection)go);
            }
        }

        //If no building collection is found, then add one
        //[Note] this is means as a pseudo singleton to allow
        //for me to reduce the amount of code in the GameWorld class
        BuildingCollection someBuildings = new BuildingCollection();
        add(someBuildings);
        return(someBuildings);
    }

    public FireCollection getFires(){
        //First, check if there is an existing FireCollection
        //If there is an existing fire collection, nothing else
        //Is to be done
        for(GameObject go: gameObjects){
            if(go instanceof FireCollection){
                return((FireCollection)go);
            }
        }

        //If no fire collection is found, then add one
        //[Note] this is means as a pseudo singleton to allow
        //for me to reduce the amount of code in the GameWorld class
        FireCollection someFires = new FireCollection();
        add(someFires);
        return(null);
    }

    public River getRiver(){
        //Similar to the helipad, the expectation is for there
        //to be only a single river
        //if rivers were to follow a bezier curve and be
        //comprised of many smaller rectangles, then yes,
        //a collection of rivers would be what is sought after
        //but here, there is no need for such a thing
        for(GameObject go: gameObjects){
            if(go instanceof River){
                return((River)go);
            }
        }

        //this is expected to throw an error
        return(null);
    }

    public Helipad getHelipad(){
        //The assumption here is that since it was
        //clarified that there will only be a single
        //helipad, that it is safe to assume that
        //there will be no need for a helipad collection
        //otherwise, one would have been implemented.
        //Therefore, the priority here is to look for the only
        //Helipad expected in the game
        for(GameObject go: gameObjects){
            if(go instanceof Helipad){
                return((Helipad)go);
            }
        }

        //This is expected to throw an error
        return(null);
    }

    //[Note]  Since not all things are collections contained, checks
    //need to be performed to ensure stability

    public boolean hasHelipad(){
        return(!(getHelipad() == null));
    }

    public boolean hasRiver(){
        return(!(getRiver() == null));
    }

}
