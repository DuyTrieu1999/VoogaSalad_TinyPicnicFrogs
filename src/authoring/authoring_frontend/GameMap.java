package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class GameMap extends BorderPane {
    private GameManager myManager;
    GridPane myGrid = new GridPane();

    public GameMap(GameManager manager) {
        myManager = manager;
        this.getChildren().add(new Label("map"));
        createGrid();
    }

    public void createGrid(){
        for(int i=0;i<30;i++){
            for(int j=0;j<20;j++){
                StackPane thisCell = new StackPane();
                thisCell.setPrefSize(18, 18);
                thisCell.setStyle("-fx-border-color: black;");
                thisCell.setOnMouseClicked(event -> {
                    Actor activeActor = ActiveItem.getActiveItem();
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

    public GridPane getGameMap(){
        return myGrid;
    }
}
