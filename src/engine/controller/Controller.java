package engine.controller;

import engine.backend.Actor;
import engine.backend.AnimationObject;
import engine.backend.PlayerActor;
import engine.backend.ServiceLocator;
import engine.frontend.game_engine_UI.StateView;

import java.util.ArrayList;
import java.util.Collection;

public class Controller {
    private StateView myView;
    private ServiceLocator myLocator;

    public Controller (StateView view) {
        this.myView = view;
    }

    public Collection<Actor> getActor () {
        return new ArrayList<Actor>();
    }
    public Collection<AnimationObject> getAnimation () {
        return new ArrayList<AnimationObject>();
    }
    public PlayerActor getPlayer () {
        return new PlayerActor();
    }
}
