package authoring.authoring_frontend;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Pair;

import javax.sound.midi.SysexMessage;
import java.util.HashMap;

public class Map {
    private int id;
    private GridPane myGrid;
    private int width;
    private int height;
    private String programName;
    private HashMap<Pair<Integer, Integer>, String> connectedPoints;

    Map(int mapWidth, int mapHeight, String pName){
        this(1, mapWidth, mapHeight, pName);
    }

    Map(int mapID, int mapWidth, int mapHeight, String pName){
        id = mapID;
        width = mapWidth;
        height = mapHeight;
        programName = pName;
        myGrid = new GridPane();
        connectedPoints = new HashMap<>();
        createMap();
    }

    public int getMapID(){
        return id;
    }

    public String toString(){
        return "Map " + id;
    }

    public void createMap(){
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                StackPane thisCell = new StackPane();
                thisCell.setPrefSize(18, 18);
                thisCell.setStyle("-fx-border-color: black;");
                thisCell.setOnMouseClicked(event -> {
                    Actor activeActor = ActiveItem.getActiveItem(programName);
                    if(activeActor != null){
                        BorderPane activeTile = new BorderPane(activeActor.getActorImage());
                        activeTile.setOnMouseClicked(null);
                        thisCell.getChildren().add(activeTile);
                    }
                    else {
                        if(thisCell.getChildren().size() > 0){
                            thisCell.getChildren().remove(thisCell.getChildren().size()-1);
                        }
                    }
                });
                myGrid.add(thisCell, i, j);
            }
        }
    }

    public GridPane getMyGrid(){
        return myGrid;
    }
}
