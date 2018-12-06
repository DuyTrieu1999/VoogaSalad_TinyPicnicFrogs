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
    /**
     * path to game Files to be loaded
     */
    private static String gameFilePath="./resources/testSave/";//"./resources/demo/";
    public static void main(String[] args) {
        initialize();
        launch(args);
    }

    /**
     *
     * @param filePath filepath to the folder where actos and messages xml files are
     */
    public void setFilePath(String filePath){gameFilePath=filePath;}

    public void start(Stage stage) {
        new StateView(stage);
    }

    private static void initialize(){
        ServiceLocator.provideAI(new RandomAI());
        //Values should be loaded from the file

        var dummyActorList = loadActors(gameFilePath);
        var actorMan = new ActorManager(dummyActorList);
        System.out.println("ANIMATIONS: "+actorMan.getAnimationObjects().size());
        ServiceLocator.provideActorManager(actorMan);
        var gameWorld = new GameWorld(1000, 1000);
        ServiceLocator.provideGameWorld(gameWorld);
    }


    private static List<Actor> loadActors(String path){
        XStream serializer = new XStream(new DomDriver());
        Map<String,Actor>loadedMap=(Map<String, Actor>) serializer.fromXML(Paths.get(path+"actors.xml").toFile());
        List<Actor>actorList= new ArrayList<>();
        actorList.addAll(loadedMap.values());
        for(Actor a:actorList){
            a.serialize();
            a.setImages(50,50);
        }
        return actorList;
    }
    private static List<Message>loadMessages(String path){
        XStream serializer = new XStream(new DomDriver());
        Map<String,Message>loadedMap=(Map<String, Message>) serializer.fromXML(Paths.get(path+"messages.xml").toFile());
        List<Message>messageList= new ArrayList<>();
        messageList.addAll(loadedMap.values());
        return messageList;
    }
}
