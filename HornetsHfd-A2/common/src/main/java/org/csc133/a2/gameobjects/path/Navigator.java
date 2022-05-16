package org.csc133.a2.gameobjects.path;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.util.MathUtil;
import org.csc133.a2.gameobjects.Fixed.River;
import org.csc133.a2.gameobjects.GameObject;
import org.csc133.a2.gameobjects.Movables.Helicopter;
import org.csc133.a2.gameobjects.Movables.PlayableHelicopter;
import org.csc133.a2.gameobjects.Movables.UnPlayableHelicopter;

import java.util.ArrayList;

public class Navigator extends GameObject {

    private static Navigator navigator;

    ArrayList<FlightPath> flightPlan;
    ArrayList<ControlPoint> priorityList;


    private Navigator(){
        flightPlan = new ArrayList<>();
        priorityList = new ArrayList<>();
        setPos(new Point(0,0));
    }

    public static Navigator getInstance(){
        if(navigator==null){
            navigator = new Navigator();
        }
        return(navigator);
    }

    public static void init(){
        navigator = new Navigator();
    }

    protected float factorial(int n){
        int result = 1;

        for(int i = 1; i<=n; i++){
            result *= i;
        }

        return result;
    }

    /*
    protected float bernsteinPolynomial(int i, int n, float t){
        return
        (
                (factorial(n)
            /
            (factorial(i-n) * factorial(n)))
            *
            (float)MathUtil.pow((1-t),(n-i))
        );
    }*/

    private double choose(int d, int i){

        double result = 1;
        if(d <= 0 || i >=d){
            return(result);
        }
        else{
            result=(choose(d-1, i-1) + choose(d-1,i));
        }
        return(result);

    }

    private double bernsteinD(int d, int i, float t){
        return(choose(d,i) * MathUtil.pow(t,i) * MathUtil.pow(1-t,d-i));
    }

    protected void addToFlightPlan(Point current,
                                            ControlPoint controlPoint,
                                            Point destination){
        float t = 0;
        float increment = 0.05f;
        int d = 2;
        ArrayList<Point> controlPoints = new ArrayList<>();
        Point cPoint = new Point(0,0);
        cPoint.setX(current.getX());
        cPoint.setY(current.getY());
        controlPoints.add(cPoint);
        controlPoints.add(controlPoint.getPos());
        controlPoints.add(destination);

        for(Point p : controlPoints){
            //System.out.println("Starting point: " + p);
        }

        ArrayList<FlightPath> outputPath = new ArrayList<>();
        Point startingPoint = current;

        while(t<=1){
            Point nextPoint = new Point(0,0);

            for(int i = 0; i<controlPoints.size(); i++){
                double b = bernsteinD(d,i,t);
                if(i == 0){
                    b = b/4;
                }
                //System.out.println("Bernstein d is " + b);
                double mx =
                        ((b) * ((float)controlPoints.get(i).getX()));
                double my =
                        ((b) * ((float)controlPoints.get(i).getY()));
                nextPoint.setX((int)(nextPoint.getX() + mx));
                nextPoint.setY((int)(nextPoint.getY()+my));
            }

            outputPath.add(new FlightPath(startingPoint,nextPoint));
            startingPoint = nextPoint;

            t+=increment;
            if(t>1 && t < 1+increment){
                t=1;
            }
        }

        for(FlightPath fp: outputPath){
            //System.out.println("Building flight plan with point " +
                    //fp.getPos());
            flightPlan.add(fp);
        }
    }

    private void updateFlightPath(Point current, Point destination,
                                 boolean secondary){
        //System.out.println("update flight path starting point: "
        // + current);
        if(flightPlan.size() == 0){
            if(secondary){
                addToFlightPlan
                (
                    current,
                    ControlGrid.getInstance().secondShortestControlPoint
                    (
                        current,
                        destination
                    ),
                    destination
                );
            }
            else{
                addToFlightPlan
                        (
                                current,
                                ControlGrid.getInstance().
                                        shortestControlPoint
                                (
                                        current,
                                        destination
                                ),
                                destination
                        );
            }
        }
        else{
            while(flightPlan.size() > 2){
                flightPlan.remove(2);
            }
            if(secondary){
                addToFlightPlan
                (
                    flightPlan.get(flightPlan.size()-1).getPos(),
                    ControlGrid.getInstance().
                            secondShortestControlPoint
                        (
                            current,
                            destination
                        ),
                    destination
                );
            }
            else{
                addToFlightPlan
                (
                    flightPlan.get(flightPlan.size()-1).getPos(),
                    ControlGrid.getInstance().shortestControlPoint
                        (
                            current,
                            destination
                        ),
                    destination
                );
            }
        }


    }

    public void addPriority(Point destination){
        if(UnPlayableHelicopter.getInstance().getWater() < 500){
            priorityList.add(0,
                    new ControlPoint(River.getInstance().getPos()));
            priorityList.add(1,new ControlPoint(destination));
        }
        else{
            priorityList.add(0,new ControlPoint(destination));
            priorityList.add(1,
                    new ControlPoint(River.getInstance().getPos()));
        }

        while(priorityList.size() > 4){
            priorityList.remove(4);
        }
    }
    public void addLowPriority(Point destination){
        if(UnPlayableHelicopter.getInstance().getWater() < 500){
            priorityList.add(0,
                    new ControlPoint(River.getInstance().getPos()));
            priorityList.add(new ControlPoint(destination));
        }
        else{
            priorityList.add(new ControlPoint(destination));
            priorityList.add(
                    new ControlPoint(River.getInstance().getPos()));
        }

        while(priorityList.size() > 4){
            priorityList.remove(4);
        }
    }

    public Point getWaypoint(Point reference){
        if(flightPlan.size() > 0){
            if(flightPlan.get(0).radiusContainsPoint(reference,
                    50)){
                flightPlan.remove(0);
            }
            if(flightPlan.size() > 0){
                return(flightPlan.get(0).getPos());
            }
        }
        return(reference);
    }

    public boolean atPriority(Point reference){
        if(priorityList.size() > 0){
            if(priorityList.get(0).radiusContainsPoint(reference,
                    100)){
                priorityList.remove(0);
                return(true);
            }
        }
        return(false);
    }

    public boolean firePriority(){
        return (priorityList.size() >= 3);
    }

    @Override
    protected void localDraw(Graphics context, Point parentOrigin,
                             Point screenOrigin){
        for(FlightPath fp : flightPlan){
            fp.draw(context, getPos(), screenOrigin);
        }
        for(ControlPoint cp : priorityList){
            cp.draw(context,parentOrigin,screenOrigin);
        }
    }

    //basically carrying the momentum of a prior segment of flight
    // path to the next point
    private Point inertialMotivator(){
        Point output = new Point(0,0);
        if(flightPlan.size() >= 2){
            int diffX =
                    flightPlan.get(flightPlan.size()-1).getPos().getX();
            diffX -= flightPlan.get(flightPlan.size()-2).getPos().getX();
            diffX += flightPlan.get(flightPlan.size()-1).getPos().getX();
            int diffY =
                    flightPlan.get(flightPlan.size()-1).getPos().getY();
            diffY -= flightPlan.get(flightPlan.size()-2).getPos().getY();
            diffY +=flightPlan.get(flightPlan.size()-1).getPos().getY();

            output = new Point(diffX,diffY);
        }
        else{
            output.setX
            (
                UnPlayableHelicopter.getInstance().getPos().getX() +
                UnPlayableHelicopter.getInstance().pointAdjustment().getX()*10
            );
            output.setX
            (
                UnPlayableHelicopter.getInstance().getPos().getY() +
                UnPlayableHelicopter.getInstance().pointAdjustment().getY()*10
            );
        }
        return(output);
    }

    public void checkFlightPriority(){
        //System.out.println("NPC Heli located at: " +
                //UnPlayableHelicopter.getInstance().getPos());
        if(priorityList.size() > 0) {
            if (UnPlayableHelicopter.getInstance().radiusContainsPoint
                (PlayableHelicopter.getInstance().getPos(),
                    300)) {

                updateFlightPath(UnPlayableHelicopter.getInstance().getPos(),
                    priorityList.get(0).getPos(),
                    true);


            } else {
                updateFlightPath(UnPlayableHelicopter.getInstance().getPos(),
                        priorityList.get(0).getPos(),
                        false);
            }
            for(int i = 1; i<priorityList.size(); i++){
                if(priorityList.size() > 1) {
                    ControlPoint nextControlPoint =
                        (
                            ControlGrid.getInstance().shortestControlPoint
                                (
                                    inertialMotivator(),
                                    priorityList.get(i-1).getPos()
                                )
                        );
                    addToFlightPlan
                        (
                                priorityList.get(i-1).getPos(),
                                nextControlPoint,
                                priorityList.get(i).getPos()
                        );
                }
            }
        }
    }


}
