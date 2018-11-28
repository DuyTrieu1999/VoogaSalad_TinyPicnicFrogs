package engine.frontend.game_engine_UI;

import engine.backend.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main extends Application {
    public static void main(String[] args) {
        initialize();
        launch(args);
    }

    public void start(Stage stage) {
        new StateView(stage);
    }

    private static void initialize(){
        ServiceLocator.provideAI(new RandomAI());
        //Values should be loaded from the file
        var gameWorld = new GameWorld(10, 10);
        ServiceLocator.provideGameWorld(gameWorld);
        var dummyActorList = new ArrayList<Actor>();
        var actorMan = new ActorManager(dummyActorList);
        ServiceLocator.provideActorManager(actorMan);
    }
}
