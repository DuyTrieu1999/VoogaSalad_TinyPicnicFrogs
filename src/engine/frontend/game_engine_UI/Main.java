package engine.frontend.game_engine_UI;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.backend.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        var dummyActorList = loadActors();
        var actorMan = new ActorManager(dummyActorList);
        ServiceLocator.provideActorManager(actorMan);
    }


    private static List<Actor> loadActors(){
        XStream serializer = new XStream(new DomDriver());
        Map<String,Actor>loadedMap=(Map<String, Actor>) serializer.fromXML(Paths.get("./resources/demo/actors.xml").toFile());
        List<Actor>actorList= new ArrayList<>();
        actorList.addAll(loadedMap.values());
        for(Actor actor:actorList){actor.serialize();}
        return actorList;
    }
}
