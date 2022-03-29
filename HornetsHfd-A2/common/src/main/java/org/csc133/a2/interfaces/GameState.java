package org.csc133.a2.interfaces;

import org.csc133.a2.GameWorld;

public interface GameState {

    public void update(GameWorld context);

    public String dialogTitle();

    public String dialogMessage(GameWorld context);

    public void dialogYes(GameWorld context);

    public void dialogNo(GameWorld context);

    public String yesOption();

    public String noOption();

}
