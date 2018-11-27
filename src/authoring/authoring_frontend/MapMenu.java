package authoring.authoring_frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import javax.swing.text.html.StyleSheet;
import java.util.Optional;

public class MapMenu extends HBox {
    private VBox mapList = new VBox();
    private ListView<String> mapView = new ListView<>();
    private HBox buttonView = new HBox();
    private String programName;
    private MapManager mapManager;

    public MapMenu(String pName, MapManager manager) {
        this.getChildren().add(new Label("map"));
        programName = pName;
        mapManager = manager;
    }

    public ListView<String> setupList(){
        mapView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        String newMap = mapManager.createMap(30, 20);
        mapView.getItems().add(newMap);
        //ActiveMap.setActiveMap(programName, newMap);
        mapManager.setActiveMap(newMap);
        mapView.setPrefHeight(200);
        mapView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 2){
                    mapManager.setActiveMap(mapView.getSelectionModel().getSelectedItem());
                }
            }
        });
        return mapView;
    }

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
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("You have no maps to connect!");

                alert.showAndWait();
            }
        });
        buttonView.getChildren().addAll(newMap, deleteMap, connectMaps);
        return buttonView;
    }

    public Tab getMapList(){
        mapList.getChildren().addAll(setupButtons(), setupList());
        Tab layerTab = new Tab();
        layerTab.setText("Maps");
        layerTab.setContent(mapList);
        return layerTab;
    }

    public VBox getMapPane(){
        mapList.getChildren().addAll(setupButtons(), setupList());
        return mapList;
    }

    public Map getCurrentMap(){
        ObservableList<String> selectedMaps = mapView.getSelectionModel().getSelectedItems();
        if(selectedMaps.size() > 0){
            return mapManager.getMap(selectedMaps.get(0));
        }
        return null;
    }
}
