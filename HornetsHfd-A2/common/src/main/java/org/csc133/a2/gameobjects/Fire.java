package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.interfaces.FireState;
import org.csc133.a2.states.IsBurning;
import org.csc133.a2.states.IsExtinguished;
import org.csc133.a2.states.IsNotStarted;

import java.util.Random;

public class Fire extends Fixed{

    private int size;
    private int peakSize;
    private FireState currentState;

    public Fire(){
        init();
    }

    public void init(){

        setColor(ColorUtil.MAGENTA);
        setState(new IsNotStarted());
        setSize(new Random().nextInt(150)+10);

    }

    public Fire spawnFire(){
        Fire fireSpawn = new Fire();
        fireSpawn.init();
        return(fireSpawn);
    }

    public void update(){
        currentState.fireUpdate(this);
    }

    public void grow(){

        if(this.size > 0){
            this.size += (
                    new Random()
                            .nextInt((2*this.category()))
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

    public void updatePeakSize(){
        if(this.peakSize < this.size){
            this.peakSize = this.size;
        }
    }

    public void setSize(int newSize){
        size = newSize;
    }

    public int getSize(){
        return(size);
    }

    public int category(){
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

    public int radius(){
        long longRadius = Math.round(Math.sqrt(this.size))*5;
        String radiusString = ""+(longRadius);
        //SO dirty but the clean way wasn't working
        int radiusInt = Integer.parseInt(radiusString);
        return(radiusInt);
    }

    public int peakRadius(){
        long longRadius = Math.round(Math.sqrt(this.peakSize))*5;
        String radiusString = ""+(longRadius);
        //SO dirty but the clean way wasn't working
        int radiusInt = Integer.parseInt(radiusString);
        return(radiusInt);
    }

    @Override
    public boolean nearPosition(Point pos){
        double diffX = (pos.getX() - this.getPos().getX());
        double diffY = (pos.getY() - this.getPos().getY());

        double distance = Math.sqrt((diffX*diffX) + (diffY*diffY));

        if(distance < this.radius()+20){
            return(true);
        }

        return(false);
    }

    @Override
    public void draw(Graphics gfxContext, Point containerOrigin){

        currentState.drawUpdate
        (
            this,
            gfxContext,
            containerOrigin
        );
    }

    public void drawCharred(Graphics gfxContext,
                           Point containerOrigin){

        int peakRadiusInt = this.peakRadius();
        gfxContext.setColor(ColorUtil.BLACK);
        gfxContext.fillArc(
                this.getPos().getX() + containerOrigin.getX()
                        - (peakRadiusInt),
                this.getPos().getY() + containerOrigin.getY()
                        - (peakRadiusInt),
                peakRadiusInt*2+1,
                peakRadiusInt*2+1,
                0,360
        );

    }

    public void drawBurns(Graphics gfxContext, Point containerOrigin){

        int radiusInt = this.radius();
        String radiusString = "" + this.size;

        //gfxContext.fillArc(500,500,100,100,0,100);
        gfxContext.setColor(ColorUtil.MAGENTA);

        gfxContext.fillArc(
                this.getPos().getX() + containerOrigin.getX()
                        - (radiusInt),
                this.getPos().getY() + containerOrigin.getY()
                        - (radiusInt),
                radiusInt*2+1,radiusInt*2+1,
                0,360
        );

        gfxContext.setColor(ColorUtil.MAGENTA);
        gfxContext.drawChars(radiusString.toCharArray(),0,
                radiusString.length(),
                this.getPos().getX()+containerOrigin.getX()
                        +radiusInt+20,
                this.getPos().getY()+containerOrigin.getY()
                        +radiusInt+20
        );

    }

}
