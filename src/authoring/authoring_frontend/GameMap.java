package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

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
                BorderPane thisCell = new BorderPane();
                thisCell.setPrefSize(16, 16);
                thisCell.setStyle("-fx-border-color: black;");
                thisCell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if(ActiveItem.getActiveItem() != null){
                            thisCell.setCenter(ActiveItem.getActiveItem());
                        }
                        else {
                            thisCell.setCenter(null);
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
