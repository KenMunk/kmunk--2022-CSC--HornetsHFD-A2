package org.csc133.a2.gameobjects;

import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.collections.ComponentCollection;

public class ComponentNode extends GameObject{

    private ComponentCollection components;

    protected void initComponents() {
        components = new ComponentCollection();
    }

    protected ComponentCollection getComponents() {
        if (components == null) {
            initComponents();
        }
        return (components);
    }

    @Override
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

        getComponents().draw(context,getPos(), screenOrigin);
        localDraw(context, getPos(), screenOrigin);

        //undo the local transforms

        undoContainerTranslate(context, parentOrigin);

        //*/
    }
}
