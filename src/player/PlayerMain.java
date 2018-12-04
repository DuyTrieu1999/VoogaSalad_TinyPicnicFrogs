package player;

import engine.frontend.game_engine_UI.Main;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

/**
 * There is a main method that can run the game engine
 */
public class PlayerMain extends Application {
    private SceneManager myManager;
    private Main engineMain;
    private UserProfileManager userProfileManager;
    private authoring.authoring_frontend.Main authoringMain;

 @Override
    public void start(Stage stage){

     stage.setTitle("Game Portal");
     userProfileManager= new UserProfileManager();
     myManager=new SceneManager(userProfileManager, stage);
        stage.show();

    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
