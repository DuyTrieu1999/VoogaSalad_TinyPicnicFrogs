package engine.controller;

import engine.backend.AnimationObject;
import engine.backend.Commands.Command;
import engine.backend.PlayerActor;
import engine.backend.ServiceLocator;
import engine.frontend.game_engine_UI.StateView;

import java.util.Collection;
import java.util.List;

public class Controller {
    private StateView myView;

    public Controller (StateView view) {
        this.myView = view;
    }

    public Collection<AnimationObject> getAnimation () {
        return ServiceLocator.getActorManager().getAnimationObjects();
    }
    public PlayerActor getPlayer () {
        return ServiceLocator.getActorManager().getPlayerActor();
    }
    public List<Command> getActiveCommands () { return myView.getActiveCommand(); }
    public void setAllCommand(List<Command> commands) { setAllCommand(commands); }
}
