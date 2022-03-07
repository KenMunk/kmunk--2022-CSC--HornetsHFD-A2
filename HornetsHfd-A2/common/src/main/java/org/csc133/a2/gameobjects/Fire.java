package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.interfaces.FireState;

public class Fire extends Fixed{

    private int size;
    private int peakSize;
    private FireState currentState;

    public Fire(){

    }

    public void grow(){
        /**
        if(this.size > 0){
            this.size += (
                    new Random()
                            .nextInt((10*this.category())+1)
            );

        }//*/
    }

    public int getSize(){
        return(size);
    }

    public int category(){
        return(((this.size - (this.size % 2000))/2000)+1);
    }

    public void extinguish(int amount){
        this.size -= amount / this.category();

        if(this.size < 0){
            this.size = 0;
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
