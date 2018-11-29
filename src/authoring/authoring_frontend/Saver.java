package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Saver {
    private ActorManager actorManager;
    private MapManager mapManager;
    private GameManager gameManager;

    Saver(ActorManager a, MapManager m, GameManager g){
        actorManager = a;
        mapManager = m;
        gameManager = g;
        saveFile();
    }

    private void saveFile(){
        File selectedDirectory =getFileChooser();

        if(selectedDirectory != null) {

            System.out.println(selectedDirectory.getPath());
            /*
            XStream serializer = new XStream(new DomDriver());
            //String serializedActors = serializer.toXML(actorManager);
            String serializedMaps = serializer.toXML(mapManager.getGameMaps());
            try {
                //Files.write(Paths.get(selectedDirectory.getAbsolutePath() + "actors.xml"), serializedActors.getBytes());
                Files.write(Paths.get(selectedDirectory.getAbsolutePath() + "maps.xml"), serializedMaps.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            */
            gameManager.saveGame("resources/", "resources/");
            System.out.println("Saved!");
        }
    }

    private File getFileChooser(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Save Game");
        return directoryChooser.showDialog(new Stage());
    }
}
