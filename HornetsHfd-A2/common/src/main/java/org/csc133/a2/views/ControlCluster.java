package org.csc133.a2.views;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import org.csc133.a2.GameWorld;
import org.csc133.a2.commands.Accelerate;

import javax.swing.border.Border;

public class ControlCluster extends Container {

    private GameWorld gameWorld;

    //Left Side Buttons
    Button leftBtn;
    Button rightBtn;
    Button fightBtn;

    //Center Buttons
    Button exitBtn;

    //Right Side Buttons
    Button drinkBtn;
    Button brakeBtn;
    Button accelBtn;

    public ControlCluster(GameWorld referenceOfGameWorld){

        gameWorld = GameWorld.getInstance();

        leftBtn = this.buttonMaker
        (
            new Accelerate(gameWorld),
            "Accel"
        );

        setButtonStyle(leftBtn);

        this.setLayout(new BorderLayout());

        this.add(BorderLayout.WEST,leftBtn);

    }

    private Button buttonMaker(Command buttonCommand, String name){
        Button outputButton = new Button(buttonCommand);
        outputButton.setText(name);



        return(outputButton);
    }

    private void setButtonStyle(Button b){

        Style buttonStyle = b.getAllStyles();

        buttonStyle.setBgColor(ColorUtil.rgb(100,100,100));
        buttonStyle.setFont
        (
            Font.createSystemFont
            (
                Font.FACE_MONOSPACE,
                Font.STYLE_BOLD,
                Font.SIZE_MEDIUM
            )
        );
        buttonStyle.setFgColor(ColorUtil.BLUE);
    }

    @Override
    public void paint(Graphics context){
        super.paint(context);



    }


}
