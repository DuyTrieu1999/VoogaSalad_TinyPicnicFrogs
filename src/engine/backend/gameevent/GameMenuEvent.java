package engine.backend.gameevent;

import engine.backend.Commands.Command;

public class GameMenuEvent extends GameEvent{

    Command myOption;
    public GameMenuEvent(Command opt, InputSource src){
        super(src);
        myOption = opt;
    }

    public Command getOption(){
        return myOption;
    }

}
