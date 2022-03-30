package org.csc133.a2.states;

import com.codename1.ui.Dialog;
import org.csc133.a2.GameWorld;
import org.csc133.a2.interfaces.GameDialogState;

public class GameDialogActive implements GameDialogState {

    public GameDialogActive(GameWorld context){
        if
        (
            Dialog.show
            (
                context.getGameState().dialogTitle(),
                context.getGameState().dialogMessage(context),
                context.getGameState().yesOption(),
                context.getGameState().noOption()
            )
        )
        {
            context.getGameState().dialogYes(context);
        }
        else{
            context.getGameState().dialogNo(context);
        }
    }

}
