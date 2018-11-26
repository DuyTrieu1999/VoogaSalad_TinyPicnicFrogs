package authoring.authoring_frontend;

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

    private MenuBar myMenu;
    private ResourceBundle myResources;
    private MapManager mapManager;

    /**
     * Constructor
     */
    public TopMenu(MapManager maps) {
        myMenu = new MenuBar();
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
        mapManager = maps;

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

        newGame.setOnAction(e -> {
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("New Game");
            dialog.setHeaderText("Enter the dimensions of the map:");
            ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));
            TextField xSize = new TextField();
            xSize.setPromptText("Width");
            TextField ySize = new TextField();
            ySize.setPromptText("Height");

            grid.add(new Label("Width:"), 0, 0);
            grid.add(xSize, 1, 0);
            grid.add(new Label("Height:"), 0, 1);
            grid.add(ySize, 1, 1);

            dialog.getDialogPane().setContent(grid);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(xSize.getText(), ySize.getText());
                }
                return null;
            });

            Optional<Pair<String, String>> result = dialog.showAndWait();

            result.ifPresent(widthHeight -> {
                Stage newAuthoringView = new Stage();
                AuthoringView environment = new AuthoringView(Integer.parseInt(widthHeight.getKey()), Integer.parseInt(widthHeight.getValue()));
                newAuthoringView.setTitle(environment.getProjectName());
                newAuthoringView.setScene(environment.getScene());
                newAuthoringView.show();
            });
        });

        newActor.setOnAction(e -> {
            PrototypeWindow makeNewActor = new PrototypeWindow();
            System.out.println("Open Popup Window"); //TODO: replace this with code
        });

        newSubmenu.getItems().add(newGame);
        newSubmenu.getItems().add(newActor);

        MenuItem openItem = new MenuItem(myResources.getString("Open"));

        openItem.setOnAction(e -> {
            System.out.println("Open FileChooser"); //TODO: replace this with code
        });

        fileMenu.getItems().add(newSubmenu);
        fileMenu.getItems().add(openItem);

        myMenu.getMenus().add(fileMenu);
    }

    /**
     * creates new Edit menu with choices: Save
     */
    private void addEditTab() {
        Menu editMenu = new Menu(myResources.getString("Edit"));

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
