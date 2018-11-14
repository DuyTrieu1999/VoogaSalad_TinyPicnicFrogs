package engine.frontend.game_engine_UI.OverWorld;

import engine.backend.Actor;
import engine.backend.PlayerActor;
import engine.frontend.Animation;
import javafx.scene.layout.BorderPane;

import java.util.Collection;

public class OverWorldView implements OverWorldViewAPI {
    private Collection<Actor> myActors;
    private Collection<Animation> myAnimations;

    private Camera myCamera;

    private BorderPane displayPane;
    private PlayerActor myPlayer;

    public OverWorldView () {

    }

}
