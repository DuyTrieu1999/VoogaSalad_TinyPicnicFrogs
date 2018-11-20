package authoring.authoring_frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.text.html.StyleSheet;

public class MapMenu extends HBox {
    private VBox mapList = new VBox();
    private ListView<Map> mapView = new ListView<>();
    private HBox buttonView = new HBox();
    int numberOfMaps = 1;

    public MapMenu() {
        this.getChildren().add(new Label("map"));
    }

    public ListView<Map> setupList(){
        mapView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        mapView.getItems().add(new Map());
        mapView.setPrefHeight(200);
        return mapView;
    }

    public HBox setupButtons(){
        Button newMap = new Button("New Map");
        newMap.setOnAction(event -> {
            numberOfMaps++;
            mapView.getItems().add(0, new Map(numberOfMaps));
        });
        Button deleteMap = new Button("Delete Map");
        deleteMap.setOnAction(event -> {
            ObservableList<Map> selectedMaps = mapView.getSelectionModel().getSelectedItems();
            mapView.getItems().removeAll(selectedMaps);
        });
        buttonView.getChildren().addAll(newMap, deleteMap);
        return buttonView;
    }

    public Tab getMapList(){
        mapList.getChildren().addAll(setupButtons(), setupList());
        Tab layerTab = new Tab();
        layerTab.setText("Maps");
        layerTab.setContent(mapList);
        return layerTab;
    }

    public Map getCurrentMap(){
        ObservableList<Map> selectedMaps = mapView.getSelectionModel().getSelectedItems();
        if(selectedMaps.size() > 0){
            return selectedMaps.get(0);
        }
        return null;
    }
}
