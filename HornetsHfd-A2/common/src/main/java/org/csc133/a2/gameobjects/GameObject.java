package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
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
    protected ColorInt color;

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

        //local transforms
        containerTranslate(context, parentOrigin);
        rotateTransform(context);

        if(color != null){
            context.setColor(getColor().getValue());
        }

        localTransforms(context);
        localDraw(context, getPos(), screenOrigin);
        undoLocalTransforms(context);

        //undo the local transforms

        undoRotateTransform(context);
        undoContainerTranslate(context, parentOrigin);

         //*/
    }

    public void update(){

    }

    protected void localDraw(Graphics context, Point parentOrigin,
                         Point screenOrigin){}

    protected void localTransforms(Graphics context){

        Transform transform = Transform.makeIdentity();
        context.getTransform(transform);
        transform.translate(getPos().getX(),getPos().getY());
        context.setTransform(transform);
    }

    protected void undoLocalTransforms(Graphics context){

        Transform transform = Transform.makeIdentity();
        context.getTransform(transform);
        transform.translate(-getPos().getX(),-getPos().getY());
        context.setTransform(transform);
    }

    public void setRotation(float nextRotation){
        rotation = nextRotation;
    }

    public float getRotation(){
        return(rotation);
    }

    public void incrementRotation(float rotationAdjustment){
        rotation += rotationAdjustment;
        rotation %= 360;
    }

    protected void rotateTransform(Graphics context){
        Transform transform = Transform.makeIdentity();
        context.getTransform(transform);
        transform.rotate(rotation, 0,0);
        context.setTransform(transform);
    }

    protected void undoRotateTransform(Graphics context){
        Transform transform = Transform.makeIdentity();
        context.getTransform(transform);
        transform.rotate(-rotation, 0,0);
        context.setTransform(transform);
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
    
    public int getWidth(){
        return(getDimensions().getWidth());
    }
    
    public int getHeight(){
        return(getDimensions().getHeight());
    }

    public void scaleTransform(Graphics context, float scaleX,
                               float scaleY){
        Transform transform = Transform.makeIdentity();
        context.getTransform(transform);
        transform.scale(scaleX,scaleY);
        context.setTransform(transform);
    }

    public void undoScaleTransform(Graphics context, float scaleX,
                               float scaleY){
        Transform transform = Transform.makeIdentity();
        context.getTransform(transform);
        transform.scale(1/scaleX,1/scaleY);
        context.setTransform(transform);
    }



}
