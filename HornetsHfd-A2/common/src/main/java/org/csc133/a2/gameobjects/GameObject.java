package org.csc133.a2.gameobjects;

import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import org.csc133.a2.ColorInt;
import org.csc133.a2.gameobjects.collections.ComponentCollection;

public abstract class GameObject {

    protected Point pos;
    private float rotation;
    private Dimension dimensions;
    private ColorInt color;

    public Point getPos(){
        if(pos == null){
            pos = new Point(0, 0);
        }
        return(new Point(pos.getX(),pos.getY()));
    }

    public Point setPos(Point newPos) {
        this.pos = newPos;
        return(pos);
    }

    public ColorInt getColor(){
        return(color);
    }

    public void setColor(int colorValue){
        color = new ColorInt(colorValue);
    }

    public void setDimensions(Dimension newDimensions){
        this.dimensions = newDimensions;
    }

    public Dimension getDimensions(){
        //[TODO] readdress the null dimension issue
        if(dimensions == null){
            dimensions = new Dimension(0,0);
        }
        return(dimensions);
    }

    public Point getOffsetPoint(Point offsetPoint){
        Point outputPoint = new Point(getPos().getX(), getPos().getY());
        outputPoint.setX(outputPoint.getX()+offsetPoint.getX());
        outputPoint.setY(outputPoint.getY()+offsetPoint.getY());
        return(outputPoint);
    }

    public boolean containsPoint(Point reference){
        return
        (
            reference.getY() >= this.pos.getY()
            &&
            (
                reference.getY()
                <=
                this.pos.getY()+this.dimensions.getHeight()
            )
            &&
            reference.getX() >= this.pos.getX()
            &&
            (
                reference.getX()
                <=
                this.pos.getX()+this.dimensions.getWidth()
            )
        );
    }

    public boolean radiusContainsPoint(Point pos, int detectionRadius){
        double diffX = (pos.getX() - this.getPos().getX());
        double diffY = (pos.getY() - this.getPos().getY());

        double distance = ((diffX*diffX) + (diffY*diffY));

        if(distance < (detectionRadius * detectionRadius)){
            return(true);
        }

        return(false);
    }

    @Deprecated
    public void draw(Graphics g, Point containerOrigin) {

    }

    public void draw
    (
        Graphics context,
        Point parentOrigin,
        Point screenOrigin
    ){
        //*
        containerTranslate(context, parentOrigin);

        Transform transform = Transform.makeIdentity();
        context.getTransform(transform);

        //local transforms
        rotateTransform(transform);
        context.setTransform(transform);
        localDraw(context, getPos(), screenOrigin);
        undoRotateTransform(transform);
        context.setTransform(transform);


        //undo the local transforms

        undoContainerTranslate(context, parentOrigin);

         //*/
    }

    public void update(){

    }

    protected void localDraw(Graphics context, Point parentOrigin,
                         Point screenOrigin){}

    protected void localTransforms(Transform transform){

    }

    protected void undoLocalTransforms(Transform transform){

    }

    public void setRotation(float nextRotation){
        rotation = nextRotation;
    }

    public float getRotation(){
        return(rotation);
    }

    public void incrementRotation(float rotationAdjustment){
        rotation += rotationAdjustment;
    }

    protected void rotateTransform(Transform transform){
        transform.rotate(rotation, 0, 0);
    }

    protected void undoRotateTransform(Transform transform){
        transform.rotate(-rotation, 0,0);
    }

    protected void containerTranslate(Graphics context,
                                      Point parentOrigin){
        Transform transform = Transform.makeIdentity();
        context.getTransform(transform);
        transform.translate(parentOrigin.getX(), parentOrigin.getY());
        context.setTransform(transform);
    }

    protected void undoContainerTranslate(Graphics context,
                                          Point parentOrigin){
        Transform transform = Transform.makeIdentity();
        context.getTransform(transform);
        transform.translate(-parentOrigin.getX(), -parentOrigin.getY());
        context.setTransform(transform);
    }

    protected void cn1ForwardPrimitiveTranslate(Graphics context,
                                                Dimension primitiveDimension){
        Transform transform = Transform.makeIdentity();
        context.getTransform(transform);
        transform.translate(-primitiveDimension.getWidth()/2,
                -primitiveDimension.getHeight()/2);
        context.setTransform(transform);
    }

    protected void cn1ReversePrimitiveTranslate(Graphics context,
                                                Dimension primitiveDimension){
        Transform transform = Transform.makeIdentity();
        context.getTransform(transform);
        transform.translate(primitiveDimension.getWidth()/2,
                primitiveDimension.getHeight()/2);
        context.setTransform(transform);
    }



}
