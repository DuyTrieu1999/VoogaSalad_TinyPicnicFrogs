package engine.controller;

import engine.backend.ServiceLocator;
import engine.frontend.game_engine_UI.StateView;

public class Controller {
    private StateView myView;
    private ServiceLocator myLocator;

    public Controller (StateView view) {
        this.myView = view;
    }
}
