package org.csc133.a2.gameobjects.Components;

import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.GameObject;
import org.csc133.a2.gameobjects.collections.ComponentCollection;

public class ComponentNode extends GameObject {

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
    protected void drawAllLocal(Graphics context, Point parentOrigin, Point screenOrigin) {
        super.drawAllLocal(context, parentOrigin, screenOrigin);

        getComponents().draw(context,getPos(), screenOrigin);
    }
}
