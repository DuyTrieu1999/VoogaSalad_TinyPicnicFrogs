package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import java.util.ResourceBundle;

/**
 * AuthoringView
 *
 * main frontend class that connects all UI elements
 * of the Game Authoring Environment
 *
 * @author brookekeene
 */
public class AuthoringView {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;
    public static final Color DEFAULT_BACKGROUND = Color.WHITE;
    private Group myRoot;
    private Scene myScene;
    private BorderPane myMainView;

    private GameManager myManager;

    /**
     * Constructor
     */
    public AuthoringView() {
        myManager = new GameManager();
        this.initializeUI();
    }

    /**
     *
     * @return
     */
    public Scene getScene() {
        return myScene;
    }

    /**
     * creates main components needed for JavaFx layouts and accesses
     * appropriate resources
     */
    private void initializeUI() {
        myRoot = new Group();
        myScene = new Scene(myRoot, WIDTH, HEIGHT, DEFAULT_BACKGROUND);
        myMainView = new BorderPane();

        ActorMenu selectActors = new ActorMenu(myManager);
        TopMenu topBar = new TopMenu();
        GameMap maps = new GameMap(myManager);

        myMainView.setTop(topBar);
        myMainView.setRight(selectActors.getActorMenu());
        //myRoot.getChildren().add(selectActors.getActorMenu());
        myMainView.setLeft(maps.getGameMap());
        //myRoot.getChildren().add(maps.getGameMap());

        myRoot.getChildren().add(myMainView);
//        myResources = ResourceBundle.getBundle(RESOURCE_PACKAGE + UI_TEXT);
//        myScene.getStylesheets().add(STYLESHEET);
    }

    /**
     *
     */
    private void makeTopMenu() {

    }

    /**
     *
     */
    private void makeActorMenu() {

    }

    /**
     *
     */
    private void makeGameMap() {

    }

}
