package org.csc133.a2;

public class ViewOffsets {
    int topOffset;
    int bottomOffset;

    public ViewOffsets(int top, int bottom){
        topOffset = top;
        bottomOffset = bottom;
    }

    public int getTopOffset(){
        return(topOffset);
    }

    public int getBottomOffset(){
        return(bottomOffset);
    }

    public int getTotalOffset(){
        return(bottomOffset+topOffset);
    }

}
