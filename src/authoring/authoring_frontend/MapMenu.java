package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import java.util.Optional;

/**
 * Menu of maps on the left side of the window.
 *
 * @author Allen Qiu
 */
public class MapMenu extends HBox {
    private VBox mapList = new VBox();
    private ListView<String> mapView = new ListView<>();
    private HBox buttonView = new HBox();
    private String programName;
    private MapManager mapManager;
    private GameManager gameManager;

    /**
     * Constructor
     * @param pName Program name
     * @param manager MapManager of the game
     * @param gm GameManager of the game
     */
    public MapMenu(String pName, MapManager manager, GameManager gm) {
        this.getChildren().add(new Label("map"));
        programName = pName;
        mapManager = manager;
        gameManager = gm;
    }

    /**
     * Initializes the list for the first time.
     * @return ListView of map names.
     */
    public ListView<String> setupList(int width, int height){
        mapView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        String newMap = mapManager.createMap(width, height);
        gameManager.setUpMap(30, 20, 1, 1);
        mapView.getItems().add(newMap);
        mapManager.setActiveMap(newMap);
        mapView.setPrefHeight(200);
        mapView.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2){
                mapManager.setActiveMap(mapView.getSelectionModel().getSelectedItem());
            }
        });
        return mapView;
    }

    /**
     * Creates the buttons.
     * @return HBox with buttons.
     */
    public HBox setupButtons(){
        Button newMap = new Button("New Map");
        newMap.setOnAction(event -> {
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("New Map");
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
                String thisNewMap = mapManager.createMap(Integer.parseInt(widthHeight.getKey()), Integer.parseInt(widthHeight.getValue()));
                mapView.getItems().add(0, thisNewMap);
                mapManager.setActiveMap(thisNewMap);
            });
        });
        Button deleteMap = new Button("Delete Map");
        deleteMap.setOnAction(event -> {
            ObservableList<String> selectedMaps = mapView.getSelectionModel().getSelectedItems();
            if(selectedMaps.contains(mapManager.getActiveMapName())){
                mapManager.findNewActiveMap(selectedMaps);
            }
            mapView.getItems().removeAll(selectedMaps);
            mapManager.removeMap(selectedMaps);
        });
        Button connectMaps = new Button("Connect Maps");
        connectMaps.setOnAction(event -> {
            if(mapManager.getMapList().size() >= 1){
                new MapConnector(mapManager);
            }
            else {
                //error
                Alert alert = new Alert(Alert.AlertType.ERROR);
                StringBuilder lol = new StringBuilder();
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("You have no maps to connect!");

                alert.showAndWait();
            }
        });
        buttonView.getChildren().addAll(newMap, deleteMap, connectMaps);
        return buttonView;
    }

    /**
     * Gets pane with all the tabs.
     * @return VBox with all the tabs.
     */
    public VBox getMapPane(int width, int height){
        mapList.getChildren().addAll(setupButtons(), setupList(width, height));
        return mapList;
    }

    /**
     * Get the currently selected map.
     * @return Currently selected map.
     */
    public Map getCurrentMap(){
        ObservableList<String> selectedMaps = mapView.getSelectionModel().getSelectedItems();
        if(selectedMaps.size() > 0){
            return mapManager.getMap(selectedMaps.get(0));
        }
        return null;
    }
}
