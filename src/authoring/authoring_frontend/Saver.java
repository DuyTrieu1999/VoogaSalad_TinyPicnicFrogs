package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Saver class to save the program state.
 *
 * @author Allen Qiu
 */
public class Saver {
    private ActorManager actorManager;
    private MapManager mapManager;
    private GameManager gameManager;

    /**
     * Constructor
     * @param a ActorManager of the game
     * @param m MapManager of the game
     * @param g GameManager of the game
     */
    Saver(ActorManager a, MapManager m, GameManager g){
        actorManager = a;
        mapManager = m;
        gameManager = g;
        saveFile();
    }

    /**
     * Saves the file
     */
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

    /**
     * Gets the file chooser.
     * @return Selected file.
     */
    private File getFileChooser(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Save Game");
        return directoryChooser.showDialog(new Stage());
    }
}
