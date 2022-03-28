package org.csc133.a2.views;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.mig.Grid;
import com.codename1.ui.plaf.Style;
import com.codename1.util.MathUtil;
import org.csc133.a2.GameWorld;
import org.csc133.a2.commands.*;

import javax.swing.border.Border;
import java.util.ArrayList;

public class ControlCluster extends Container {

    private GameWorld gameWorld;

    //Left Side Buttons
    private Button rightBtn;
    private Button fightBtn;
    private Button leftBtn;

    //Center Buttons
    private Button exitBtn;

    //Right Side Buttons
    private Button drinkBtn;
    private Button brakeBtn;
    private Button accelBtn;


    private final Container leftSideButtons =
            (
                    new Container(new GridLayout(1,3))
            );

    private final Container rightSideButtons =
            (
                    new Container(new GridLayout(1,3))
            );

    public ControlCluster(GameWorld referenceOfGameWorld){



        gameWorld = GameWorld.getInstance();

        leftBtn = this.buttonMaker
        (
            new TurnLeft(gameWorld),
            "Left"
        );

        rightBtn = this.buttonMaker
        (
            new TurnRight(gameWorld),
            "Right"
        );

        fightBtn = this.buttonMaker(
            new Fight(gameWorld),
            "Fight"
        );

        exitBtn = this.buttonMaker(
            new Exit(gameWorld),
            "Exit"
        );

        drinkBtn = this.buttonMaker(
            new Drink(gameWorld),
            "Drink"
        );

        brakeBtn = this.buttonMaker(
                new Brake(gameWorld),
                "Brake"
        );

        accelBtn = this.buttonMaker(
                new Accelerate(gameWorld),
                "Accel"
        );

        setButtonStyle(leftBtn);
        setButtonStyle(rightBtn);
        setButtonStyle(fightBtn);
        setButtonStyle(exitBtn);
        setButtonStyle(accelBtn);
        setButtonStyle(drinkBtn);
        setButtonStyle(brakeBtn);

        leftSideButtons.add(leftBtn);
        leftSideButtons.add(rightBtn);
        leftSideButtons.add(fightBtn);

        rightSideButtons.add(drinkBtn);
        rightSideButtons.add(brakeBtn);
        rightSideButtons.add(accelBtn);

        this.setLayout(new GridLayout(1,5));

        ArrayList<Container> emptyContainer = new ArrayList<>();
        emptyContainer.add(new Container());
        emptyContainer.add(new Container());

        this.add(leftSideButtons);
        this.add(emptyContainer.get(0));
        this.add(exitBtn);
        this.add(emptyContainer.get(1));
        this.add(rightSideButtons);

        Dimension thickBottom = this.getPreferredSize();
        thickBottom.setHeight(thickBottom.getHeight()*2);
    }

    private Button buttonMaker(Command buttonCommand, String name){
        Button outputButton = new Button(buttonCommand);
        outputButton.setText(name);

        Dimension buttonSize = outputButton.getPreferredSize();
        buttonSize.setHeight(MathUtil.round(buttonSize.getHeight()*1.5f));
        outputButton.setPreferredSize(buttonSize);

        return(outputButton);
    }

    private void setButtonStyle(Button b){

        Style buttonStyle = b.getStyle();

        buttonStyle.setBgColor(ColorUtil.rgb(200,200,200));
        buttonStyle.setBgTransparency(255);
        buttonStyle.setFont
        (
            Font.createSystemFont
            (
                Font.FACE_MONOSPACE,
                Font.STYLE_BOLD,
                //The font size could really be resizable
                Font.SIZE_MEDIUM
            )
        );
        buttonStyle.setFgColor(ColorUtil.BLUE);
    }

    @Override
    public void paint(Graphics context){
        super.paint(context);


    }

    @Override
    public Component getNextFocusDown(){
        return(getParent());
    }

    @Override
    public Component getNextFocusUp(){
        return(getParent());
    }

    @Override
    public Component getNextFocusLeft(){
        return(getParent());
    }

    @Override
    public Component getNextFocusRight(){
        return(getParent());
    }


}
