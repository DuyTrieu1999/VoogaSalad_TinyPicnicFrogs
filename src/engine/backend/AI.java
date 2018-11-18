package engine.backend;

import engine.backend.Commands.Command;

import java.util.List;

/**
 * Chooses an option from a list of commands
 * @author Christopher Lin cl349
 */
public abstract class AI {
    protected List<Command> myOptions;

    public void setOptions(List<Command> options){
        myOptions = options;
    }

    public abstract Command getOption();

}
