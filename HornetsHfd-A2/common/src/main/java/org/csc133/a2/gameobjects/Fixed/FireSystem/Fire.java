package org.csc133.a2.gameobjects.Fixed.FireSystem;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.Components.Component;
import org.csc133.a2.gameobjects.Fixed.Fixed;
import org.csc133.a2.interfaces.FireState;

import java.util.Random;

public class Fire extends Fixed{

    private class Bubble extends Component {

        int radius;

        public Bubble(int newRadius, int color){
            init(newRadius, color);
        }

        private void init(int newRadius, int color){
            setRadius(newRadius);
            setColor(color);
        }

        public void setRadius(int newRadius){
            radius = newRadius;
            setDimensions(new Dimension(radius*2, radius*2));
        }

        @Override
        protected void localDraw(Graphics context,
                                 Point parentOrigin,
                                 Point screenOrigin){

            //context.setColor(getColor().getValue());

            cn1ForwardPrimitiveTranslate(context, getDimensions());
            if(radius > 1){
                //System.out.println("Drawing burn");
                context.fillArc(
                    0,0,
                    getDimensions().getWidth(),
                    getDimensions().getHeight(),
                    0,360
                );
            }
            cn1ReversePrimitiveTranslate(context, getDimensions());
        }

    }

    private class BurnBubble extends Bubble {
        public BurnBubble(){
            super(2,ColorUtil.GRAY);
        }
    }

    private class FireBubble extends Bubble{
        public FireBubble(){
            super(2, ColorUtil.MAGENTA);
        }
    }

    private int size;
    private int peakSize;
    private FireState currentState;

    public Fire(){
        init();
    }

    private void init(){
        setPos(new Point(0,0));
        setColor(ColorUtil.MAGENTA);
        setState(new IsNotStarted());
        setSize(new Random().nextInt(300)+10);
        initPeakSize();

        initComponents();
        getComponents().add(new BurnBubble());
        getComponents().add(new FireBubble());
        System.out.println("Fire components count = "+getComponents().size());
    }

    public void update(){
        currentState.fireUpdate(this);
    }

    public void grow(){

        if(this.size > 0){
            this.size += (
                    new Random()
                            .nextInt((this.category())+1)
            );

        }//*/

        updatePeakSize();

    }

    private void setState(FireState someState){

        currentState = someState;

    }

    public void start(){
        setState(new IsBurning());
    }

    private void updatePeakSize(){
        if(this.peakSize < this.size){
            this.peakSize = this.size;
        }
    }

    private void initPeakSize(){
        this.peakSize = 0;
    }

    private void setSize(int newSize){
        size = newSize;
    }

    public int getSize(){
        return(size);
    }

    private int category(){
        return(((this.size - (this.size % 2000))/2000)+1);
    }

    public void extinguish(int amount){
        if(!(currentState instanceof IsNotStarted)){
            this.size -= amount / this.category();

            if(this.size < 0){
                this.size = 0;
            }
        }

        if(this.size <= 0){
            setState(new IsExtinguished());
        }
    }

    private int radius(){
        long longRadius = Math.round(Math.sqrt(this.size))*5;
        String radiusString = ""+(longRadius);
        //SO dirty but the clean way wasn't working
        int radiusInt = Integer.parseInt(radiusString);
        return(radiusInt/3);
    }

    private int peakRadius(){
        long longRadius = Math.round(Math.sqrt(this.peakSize))*5;
        String radiusString = ""+(longRadius);
        //SO dirty but the clean way wasn't working
        int radiusInt = Integer.parseInt(radiusString);
        return(radiusInt/3);
    }

    public boolean nearPosition(Point pos){
        return nearPosition(pos,200);

    }

    public boolean nearPosition(Point pos, int margin){
        boolean isNear = radiusContainsPoint
                (
                        pos,
                        (this.radius())+margin
                );

        return(isNear);
    }

    @Override
    protected void localDraw(Graphics context, Point parentOrigin,
                                 Point screenOrigin){
        currentState.localDraw(this,context, parentOrigin,
                screenOrigin);

    }

    protected void localDrawCharred(Graphics context,
                                  Point parentOrigin,
                                 Point screenOrigin){

        int peakRadiusInt = this.peakRadius();
        for(Component c : getComponents()){
            if(c instanceof BurnBubble){
                ((BurnBubble)c).setRadius(peakRadiusInt-1);
                c.draw(context,new Point(0,0),screenOrigin);
            }
        }

    }

    protected void localDrawBurns(Graphics context,
                                  Point parentOrigin,
                               Point screenOrigin){

        int radiusInt = this.radius();
        for(Component c : getComponents()){
            if(c instanceof FireBubble){
                ((FireBubble)c).setRadius(radiusInt);
                c.draw(context,new Point(0,0),screenOrigin);
            }
        }

        String currentSize = ""+getSize();

        scaleTransform(context,1,-1);
        context.drawChars(currentSize.toCharArray(),0,
                currentSize.length(),
                0, -100 + radiusInt);
        undoScaleTransform(context, 1,-1);

    }

    public int getPeakSize(){
        return(peakSize);
    }

    public boolean isBurning() {
        return(currentState instanceof IsBurning);
    }

    public boolean isReady(){
        return(currentState instanceof IsNotStarted);
    }


    @Override
    protected void drawAllLocal
    (
        Graphics context,
        Point parentOrigin,
        Point screenOrigin
    ) {
        localDraw(context, parentOrigin, screenOrigin);
    }

    @Override
    public void pointerPressed(Point location){
        if(nearPosition(location,0)){
            System.out.println("Fire at pos (" + getPos() + ") has " +
                    "been selected");
        }
    }


}
