package org.csc133.a2.views;

import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;

public abstract class HudSegment extends Container {

    private Label segmentLabel;
    private Label dataValue;

    public void update(){
        //do something
    }

    public void initialize(){
        this.setLayout(new GridLayout(2,1));

        this.segmentLabel = new Label();
        this.dataValue = new Label();

        this.add(segmentLabel);
        this.add(dataValue);
    }

    public void setSegmentLabel(String value){
        this.segmentLabel.setText(value);
    }

    public void updateDataValue(String value){
        this.dataValue.setText(value);
    }
}
