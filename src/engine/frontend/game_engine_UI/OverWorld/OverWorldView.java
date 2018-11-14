package engine.frontend.game_engine_UI.OverWorld;

import engine.frontend.game_engine_UI.Actor.ActorUi;
import javafx.scene.layout.BorderPane;

import java.util.Collection;

public class OverWorldView implements OverWorldViewAPI {
    private Collection<ActorUi> myActors;
    private Camera myCamera;

    private BorderPane displayPane;

    public OverWorldView () {

    }

}
