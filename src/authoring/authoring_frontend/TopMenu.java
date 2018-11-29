package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * TopMenu
 *
 * Creates a MenuBar that allows user to select from various menus
 * of settings and basic functions
 *
 * @author brookekeene
 */
public class TopMenu extends HBox {
    public static final String DEFAULT_RESOURCE = "English";

    private GameManager myManager;
    private MenuBar myMenu;
    private ResourceBundle myResources;
    private PrototypeWindow myNewActor;
    private MapManager mapManager;
    private ActorManager actorManager;

    /**
     * Constructor
     */
    public TopMenu(GameManager manager, MapManager maps, ActorManager actor) {
        myManager = manager;
        myMenu = new MenuBar();
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
        mapManager = maps;
        actorManager = actor;

        this.getChildren().add(myMenu);
        this.addAllMenus();
    }

    /**
     * calls methods which create dropdown menus within myMenu
     */
    private void addAllMenus() {
        addFileTab();
        addEditTab();
        addViewTab();
        addGameTab();
    }

    //TODO: refactor so that each add...Tab() calls an instance of a MenuChoice

    /**
     * creates new File menu with choices: New (Game, Prototype), Open
     */
    private void addFileTab() {
        Menu fileMenu = new Menu(myResources.getString("File"));

        Menu newSubmenu = new Menu(myResources.getString("New"));
        MenuItem newGame = new MenuItem(myResources.getString("Game"));
        MenuItem newActor = new MenuItem(myResources.getString("Prototype"));

        // New
        newGame.setOnAction(e -> {
            System.out.println("Open New AuthoringView"); //TODO: replace this with code
        });

        newActor.setOnAction(e -> {
            myNewActor = new PrototypeWindow(myManager);
        });

        newSubmenu.getItems().add(newGame);
        newSubmenu.getItems().add(newActor);

        // Open
        MenuItem openItem = new MenuItem(myResources.getString("Open"));

        openItem.setOnAction(e -> {
            System.out.println("Open FileChooser"); //TODO: replace this with code
        });

        MenuItem saveGame = new MenuItem("Save");

        saveGame.setOnAction(event -> new Saver(actorManager, mapManager, myManager));

        fileMenu.getItems().addAll(newSubmenu, openItem, saveGame);

        myMenu.getMenus().add(fileMenu);
    }

    /**
     * creates new Edit menu with choices: Save
     */
    private void addEditTab() {
        Menu editMenu = new Menu(myResources.getString("Edit"));

        // Save
        MenuItem saveItem = new MenuItem(myResources.getString("Save"));

        saveItem.setOnAction(e -> {
            System.out.println("Save current game"); //TODO: replace this with code
        });

        editMenu.getItems().add(saveItem);

        myMenu.getMenus().add(editMenu);
    }

    /**
     * creates new View menu with choices: Theme (Light, Dark)
     */
    private void addViewTab() {
        Menu viewMenu = new Menu(myResources.getString("View"));

        // Theme
        Menu themeSubmenu = new Menu(myResources.getString("Theme"));
        MenuItem lightTheme = new MenuItem(myResources.getString("Light"));
        MenuItem darkTheme = new MenuItem(myResources.getString("Dark"));

        lightTheme.setOnAction(e -> {
            System.out.println("Change stylesheet to light version"); //TODO: replace this with code
        });

        darkTheme.setOnAction(e -> {
            System.out.println("Change stylesheet to dark version"); //TODO: replace this with code
        });

        themeSubmenu.getItems().add(lightTheme);
        themeSubmenu.getItems().add(darkTheme);
        viewMenu.getItems().add(themeSubmenu);

        myMenu.getMenus().add(viewMenu);
    }

    private void addGameTab(){
        Menu gameMenu = new Menu(myResources.getString("Game"));

        MenuItem run = new MenuItem(myResources.getString("Run"));

        run.setOnAction(e -> {
            System.out.println("run the game"); //TODO: replace this with code

            for(String mapName:mapManager.getMapList()){
                Map thisMap = mapManager.getMap(mapName);
                Grid thisGrid = thisMap.getGrid();
                Cell[][] theseCells = thisGrid.getCells();
                for(int i=0;i<theseCells.length;i++){
                    for(int j=0;j<theseCells[i].length;j++){
                        System.out.println("This cell at (" + i + ", " + j + ") has " + theseCells[i][j].getActors().size() + " actors!");
                    }
                }
            }
        });

        gameMenu.getItems().add(run);

        myMenu.getMenus().add(gameMenu);
    }
}
