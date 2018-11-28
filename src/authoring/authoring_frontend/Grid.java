package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Stack;

public class Grid {
    private GridPane mapGridPane;
    private Cell[][] myCells;
    private int gridWidth;
    private int gridHeight;
    private String programName;
    private GameManager gameManager;

    Grid(String name, GameManager myManager){
        this(30, 20, name, myManager);
    }

    Grid(int width, int height, String name, GameManager myManager){
        gridWidth = width;
        gridHeight = height;
        programName = name;
        gameManager = myManager;
        mapGridPane = new GridPane();
        myCells = new Cell[width][height];
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                myCells[i][j] = new Cell(i, j);
                mapGridPane.add(createCell(), i, j);
            }
        }
    }

    private StackPane createCell(){
        StackPane thisCell = new StackPane();
        thisCell.setPrefSize(18, 18);
        thisCell.setStyle("-fx-border-color: black;");
        thisCell.setOnMouseClicked(event -> {
            Actor activeActor = ActiveItem.getActiveItem(programName);
            int x = mapGridPane.getColumnIndex(thisCell);
            int y = mapGridPane.getRowIndex(thisCell);
            if(activeActor != null){
                thisCell.getChildren().add(activeActor.getActorImage());
                //thisCell.setOnMouseClicked(null);
                myCells[x][y].addActor(activeActor);
                gameManager.createActor(activeActor.getActorPrototypeID(), x, y, thisCell.getChildren().size(), 0, 0);
            }
            else {
                if(thisCell.getChildren().size() > 0){
                    thisCell.getChildren().remove(thisCell.getChildren().size()-1);
                    ArrayList<Actor> actorsOfCell = myCells[x][y].getActors();
                    gameManager.deleteActor(actorsOfCell.get(actorsOfCell.size()-1).getActorPrototypeID()+x+"-"+y+"-"+(thisCell.getChildren().size()-1));
                    myCells[x][y].removeActor();
                }
            }
        });
        return thisCell;
    }

    public GridPane getGridPane(){
        return mapGridPane;
    }

    public Cell[][] getCells(){
        return myCells;
    }

}
