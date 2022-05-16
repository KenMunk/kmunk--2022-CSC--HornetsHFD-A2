package org.csc133.a2.gameobjects.path;

import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.Components.ComponentNode;
import org.csc133.a2.gameobjects.collections.GameObjectCollection;

public class ControlGrid extends GameObjectCollection<ControlPoint> {

    private static ControlGrid gridInstance;

    private ControlGrid(){
        int segmentWidth = 1920/5;
        int halfWidth = 1920/2;
        int segmentHeight = 1440/5;
        int halfHeight = 1440/2;

        add(new ControlPoint(new Point(segmentWidth,segmentHeight)));
        add(new ControlPoint(new Point(segmentWidth*4,
                segmentHeight)));
        add(new ControlPoint(new Point(segmentWidth*4,
                segmentHeight*4)));

        add(new ControlPoint(new Point(segmentWidth,
                segmentHeight*4)));
        add(new ControlPoint(new Point(halfWidth,halfHeight)));
    }

    public static ControlGrid getInstance(){
        if(gridInstance == null){
            gridInstance = new ControlGrid();
        }
        return(gridInstance);
    }

    public ControlPoint shortestControlPoint(Point initial, Point end){
        float shortestPath = 10000;
        int indexFound = 0;
        int currentIndex = 0;
        for(ControlPoint pathControl : this){
            float totalPath = pathControl.distanceTo(initial);
            totalPath += pathControl.distanceTo(end);

            if(totalPath<shortestPath){
                shortestPath = totalPath;
                indexFound = currentIndex;
            }
            currentIndex++;
        }
        return(getAt(indexFound));
    }

    public ControlPoint secondShortestControlPoint(Point initial,
                                                   Point end){
        float shortestPath = 10000;
        float secondShortestPath = 10000;
        int indexFound = 0;
        int currentIndex = 0;
        for(ControlPoint pathControl : this){
            float totalPath = pathControl.distanceTo(initial);
            totalPath += pathControl.distanceTo(end);

            if(totalPath<shortestPath){
                shortestPath = totalPath;
            }
            currentIndex++;
        }

        currentIndex = 0;
        for(ControlPoint pathControl : this){
            float totalPath = pathControl.distanceTo(initial);
            totalPath += pathControl.distanceTo(end);

            if(totalPath<secondShortestPath){
                if(secondShortestPath>shortestPath){
                    shortestPath = totalPath;
                    indexFound = currentIndex;
                }
            }
            currentIndex++;
        }
        return(getAt(indexFound));
    }

}
