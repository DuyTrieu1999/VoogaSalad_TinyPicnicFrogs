package engine.frontend.game_engine_UI;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.backend.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.nio.file.Paths;
import java.util.ArrayList;
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

        var dummyActorList = loadActors();
        var actorMan = new ActorManager(dummyActorList);
        System.out.println("ANIMATIONS: "+actorMan.getAnimationObjects().size());
        ServiceLocator.provideActorManager(actorMan);
        var gameWorld = new GameWorld(1000, 1000);
        ServiceLocator.provideGameWorld(gameWorld);
    }


    private static List<Actor> loadActors(){
        XStream serializer = new XStream(new DomDriver());
        Map<String,Actor>loadedMap=(Map<String, Actor>) serializer.fromXML(Paths.get("resources/demo/actors.xml").toFile());
        List<Actor>actorList= new ArrayList<>();
        actorList.addAll(loadedMap.values());
        for(Actor a:actorList){
            a.serialize();
            a.setImages();
        }
        return actorList;
    }
}
