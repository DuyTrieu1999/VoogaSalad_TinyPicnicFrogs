package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.util.ArrayList;

/**
 * Grid class to store the total grid in a map.
 *
 * @author Allen
 */
public class Grid {
    private GridPane mapGridPane;
    private Cell[][] myCells;
    private int gridWidth;
    private int gridHeight;
    private String programName;
    private GameManager gameManager;

    /**
     * Default constructor makes a 30x20 grid.
     * @param name Program name.
     * @param myManager GameManager of current game.
     */
    Grid(String name, GameManager myManager){
        this(30, 20, name, myManager);
    }

    /**
     * Constructor
     * @param width Width in squares.
     * @param height Height in squares.
     * @param name Program name.
     * @param myManager GameManager of current game.
     */
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

    /**
     * Creates a cell.
     * @return A StackPane with the cell contents.
     */
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

    /**
     * Gets the GridPane of this grid.
     * @return GridPane
     */
    public GridPane getGridPane(){
        return mapGridPane;
    }

    /**
     * Gets matrix of cells in this grid.
     * @return Matrix of cells contained in this grid.
     */
    public Cell[][] getCells(){
        return myCells;
    }


    /**
     * when the user hovers over an actor in the Grid, ideally we could get info on that actor
     * @param actorIV
     */
    public void hoverInfo(ImageView actorIV){

        Tooltip tooltip = new Tooltip();
        tooltip.setText(
                "\n Actor ID, coordinates, name" //TODO: get the right info
        );

        //tooltip.setFont(new Font("Arial", 16));
        Button actor = new Button("", actorIV);
        actor.setTooltip(new Tooltip(tooltip.getText()));

//        actor.setOnAction(event -> actorIV.selectHandler().onSelect());
//        menu.getChildren().add(actor);

        actor.setBackground(Background.EMPTY);

        actor.setOnMouseEntered(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                actor.setStyle("-fx-background-color:#dae7f3;");
            }
        });

        actor.setOnMouseExited(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                actor.setStyle("-fx-background-color:transparent;");
            }
        });


    }

}
