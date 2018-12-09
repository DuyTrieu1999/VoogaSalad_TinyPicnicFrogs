package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * AuthoringView
 *
 * main frontend class that connects all UI elements
 * of the Game Authoring Environment
 *
 * @author brookekeene, Allen Qiu
 */
public class AuthoringView {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 620;
    public static final Color DEFAULT_BACKGROUND = Color.WHITE;
    private Group myRoot;
    private Scene myScene;
    private BorderPane myMainView;

    private GameManager myManager;
    private MapManager mapManager;
    private ActorManager actorManager;
    private String projectName;
    private int numberUnsavedProjects = 1;

    /**
     * Constructor
     */
    public AuthoringView() {
        myManager = new GameManager();
        projectName = "Project " + numberUnsavedProjects;
        numberUnsavedProjects++;
        this.initializeUI();
}

    public AuthoringView(int width, int height){
        myManager = new GameManager();
        projectName = "Project " + numberUnsavedProjects;
        numberUnsavedProjects++;
        this.initializeUI(width, height);
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
        mapManager = new MapManager(projectName, myManager);
        actorManager = new ActorManager(myManager, projectName);

        //ActorMenu selectActors = new ActorMenu(myManager, actorManager, projectName);
        LayerMenu myLayers = new LayerMenu();
        MapMenu myMaps = new MapMenu(projectName, mapManager, myManager);
        VBox leftSide = new VBox();
        leftSide.setMaxHeight(600);
        leftSide.setMaxWidth(400);
        /*
        TabPane layersAndMaps = new TabPane();
        layersAndMaps.getTabs().addAll(myMaps.getMapList());
        layersAndMaps.setSide(Side.BOTTOM);
        */
        leftSide.getChildren().addAll(actorManager.getActorMenu(), myMaps.getMapPane());
        TopMenu topBar = new TopMenu(myManager, mapManager, actorManager);

        myMainView.setCenter(new ScrollPane(mapManager.getActiveMap()));
        myMainView.setLeft(leftSide);
        myMainView.setTop(topBar);
        myRoot.getChildren().add(myMainView);

    }

    private void initializeUI(int width, int height) {
        myRoot = new Group();
        myScene = new Scene(myRoot, WIDTH, HEIGHT, DEFAULT_BACKGROUND);
        myMainView = new BorderPane();
        mapManager = new MapManager(projectName, myManager);
        actorManager = new ActorManager(myManager, projectName);

        //ActorMenu selectActors = new ActorMenu(myManager, actorManager, projectName);
        LayerMenu myLayers = new LayerMenu();
        MapMenu myMaps = new MapMenu(projectName, mapManager, myManager);
        VBox leftSide = new VBox();
        leftSide.setMaxHeight(600);
        leftSide.setMaxWidth(400);
        /*
        TabPane layersAndMaps = new TabPane();
        layersAndMaps.getTabs().addAll(myMaps.getMapList());
        layersAndMaps.setSide(Side.BOTTOM);
        */
        leftSide.getChildren().addAll(actorManager.getActorMenu(), myMaps.getMapPane());
        TopMenu topBar = new TopMenu(myManager, mapManager, actorManager);

        myMainView.setLeft(leftSide);
        myMainView.setCenter(mapManager.getActiveMap());
        myMainView.setTop(topBar);
        myRoot.getChildren().add(myMainView);
    }

    /**
    private void makeTopMenu() {

    }

    private void makeActorMenu() {

    }

    private void makeGameMap() {

    }
    */

    public String getProjectName(){
        return projectName;
    }

}
