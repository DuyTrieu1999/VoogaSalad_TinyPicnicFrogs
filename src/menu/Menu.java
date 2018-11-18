package menu;

import engine.backend.Commands.Command;

import java.util.List;

public abstract class Menu{
    protected List<Command> myOptions;
    Menu(List<Command> options){
        myOptions = options;
    }

    public abstract List<Command> getChoices();
}
