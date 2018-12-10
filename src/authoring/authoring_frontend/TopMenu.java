package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import authoring.authoring_frontend.PopupWindows.PopupWindow;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.util.Pair;

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
class TopMenu extends HBox {
    private static final String DEFAULT_RESOURCE = "English";
    private static final int PADDING_TEN = 10;
    private static final int PADDING_TWENTY = 20;
    private static final int PADDING_ONEFIFTY = 150;

    private GameManager myManager;
    private MenuBar myMenu;
    private ResourceBundle myResources;
    private MapManager mapManager;
    private ActorManager actorManager;
    private String programName;
    private Scene thisScene;

    /**
     * Constructor
     */
    TopMenu(GameManager manager, MapManager maps, ActorManager actor, String pName, Scene scene) {
        myManager = manager;
        myMenu = new MenuBar();
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
        mapManager = maps;
        actorManager = actor;
        programName = pName;
        thisScene = scene;

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

        // New Submenu
        Menu newSubmenu = new Menu(myResources.getString("New"));
        MenuItem newGame = new MenuItem(myResources.getString("Game"));
        MenuItem newActor = new MenuItem(myResources.getString("Prototype"));
        MenuItem newMessage = new MenuItem(myResources.getString("Message"));

        newSubmenu.getItems().addAll(newGame, newActor, newMessage);

        newGame.setOnAction(e -> {
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("New Game");
            dialog.setHeaderText("Enter the dimensions of the map:");
            ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
            GridPane grid = new GridPane();
            grid.setHgap(PADDING_TEN);
            grid.setVgap(PADDING_TEN);
            grid.setPadding(new Insets(PADDING_TWENTY, PADDING_ONEFIFTY, PADDING_TEN, PADDING_TEN));
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
            PopupWindow myNewActor = PopupFactory.getPopup("prototype", myManager, actorManager, mapManager, programName);
        });

        newMessage.setOnAction(e -> {
            PopupWindow myNewMessage = PopupFactory.getPopup("message", myManager, actorManager, mapManager, programName);
        });

        // Open Submenu
        MenuItem openItem = new MenuItem(myResources.getString("Open"));

        openItem.setOnAction(e -> {
            System.out.println("Open FileChooser"); //TODO: replace this with code
            PopupFactory.getPopup("open", myManager, actorManager, mapManager, programName);
        });

        // Save Submenu
        MenuItem saveGame = new MenuItem(myResources.getString("Save"));

        saveGame.setOnAction(e -> {
            PopupWindow mySaver = PopupFactory.getPopup("save", myManager, actorManager, mapManager, programName);
        });

        fileMenu.getItems().addAll(newSubmenu, openItem, saveGame);

        myMenu.getMenus().add(fileMenu);
    }


    /**
     * creates new Edit menu with choices: Save
     */
    private void addEditTab(){
        Menu editMenu = new Menu(myResources.getString("Edit"));

        // Save
        MenuItem mapItem = new MenuItem(myResources.getString("Map"));
        MenuItem actorItem = new MenuItem(myResources.getString("Prototype"));

        mapItem.setOnAction(e -> {
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Edit Map");
            dialog.setHeaderText("Enter the new dimensions of the map:");
            ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
            GridPane grid = new GridPane();
            grid.setHgap(PADDING_TEN);
            grid.setVgap(PADDING_TEN);
            grid.setPadding(new Insets(PADDING_TWENTY, PADDING_ONEFIFTY, PADDING_TEN, PADDING_TEN));
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
                int newWidth = Integer.parseInt(widthHeight.getKey());
                int newHeight = Integer.parseInt(widthHeight.getValue());
                //System.out.println(newWidth + " " + newHeight);
                Map currentMap = mapManager.getMap(mapManager.getActiveMapName());
                //System.out.println(newWidth + " " + newHeight + " " + currentMap.getWidth() + " " + currentMap.getHeight());
                if(newWidth <= 0 || newHeight <= 0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Incorrect Dimensions");
                    alert.setContentText("Width or height must be above 0!");

                    alert.showAndWait();
                }
                else if(newWidth < currentMap.getWidth() || newHeight < currentMap.getHeight()){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirm Data Loss");
                    alert.setHeaderText("The new map width or height has decreased: some clipping will occur.");
                    alert.setContentText("Are you ok with this?");

                    Optional<ButtonType> clippingResult = alert.showAndWait();
                    if (clippingResult.isPresent() && clippingResult.get() == ButtonType.OK){
                        // ... user chose OK
                        mapManager.changeMapDimensions(newWidth, newHeight);
                    }
                }
                else {
                    mapManager.changeMapDimensions(newWidth, newHeight);
                }
            });
        });

        actorItem.setOnAction(event -> {
            PopupFactory.getPopup("editActors", myManager, actorManager, mapManager, programName);
        });

        editMenu.getItems().addAll(mapItem, actorItem);

        myMenu.getMenus().add(editMenu);
    }

    /**
     * creates new View menu with choices: Theme (Light, Dark)
     */
    private void addViewTab(){
        Menu viewMenu = new Menu(myResources.getString("View"));

        // Theme
        Menu themeSubmenu = new Menu(myResources.getString("Theme"));
        MenuItem lightTheme = new MenuItem(myResources.getString("Light"));
        MenuItem darkTheme = new MenuItem(myResources.getString("Dark"));

        lightTheme.setOnAction(e -> {
            //System.out.println("Change stylesheet to light version"); //TODO: replace this with code
            thisScene.getStylesheets().remove("dark.css");
        });

        darkTheme.setOnAction(e -> {
            //System.out.println("Change stylesheet to dark version"); //TODO: replace this with code
            thisScene.getStylesheets().add("dark.css");
        });

        themeSubmenu.getItems().addAll(lightTheme, darkTheme);
        viewMenu.getItems().add(themeSubmenu);

        myMenu.getMenus().add(viewMenu);
    }

    /**
     * creates new Game menu with the option for the user to Run their game
     */
    private void addGameTab(){
        Menu gameMenu = new Menu(myResources.getString("Game"));

        MenuItem run = new MenuItem(myResources.getString("Run"));

        run.setOnAction(e -> {
            System.out.println("run the game"); //TODO: replace this with code

            for(String mapName:mapManager.getMapList()){
                Map thisMap = mapManager.getMap(mapName);
                //Grid thisGrid = thisMap.getGrid();
                /*
                Cell[][] theseCells = thisGrid.getCells();
                for(int i=0;i<theseCells.length;i++){
                    for(int j=0;j<theseCells[i].length;j++){
                        System.out.println("This cell at (" + i + ", " + j + ") has " + theseCells[i][j].getActors().size() + " actors!");
                    }
                }
                */
            }
        });

        gameMenu.getItems().add(run);

        myMenu.getMenus().add(gameMenu);
    }
}
