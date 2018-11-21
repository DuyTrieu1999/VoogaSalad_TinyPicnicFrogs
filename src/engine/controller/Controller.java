package engine.controller;

import engine.backend.Actor;
import engine.backend.AnimationObject;
import engine.backend.Commands.Command;
import engine.backend.PlayerActor;
import engine.backend.ServiceLocator;
import engine.frontend.game_engine_UI.StateView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Controller {
    private StateView myView;
    private ServiceLocator myLocator;

    public Controller (StateView view) {
        this.myView = view;
    }

    public Collection<AnimationObject> getAnimation () {
        return new ArrayList<AnimationObject>();
    }
    public PlayerActor getPlayer () {
        return new PlayerActor();
    }
    public List<Command> getActiveCommands () { return myView.getActiveCommand(); }
    public void setAllCommand(List<Command> commands) { setAllCommand(commands); }
}
