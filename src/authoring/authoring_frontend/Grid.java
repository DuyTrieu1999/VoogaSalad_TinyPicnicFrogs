package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Grid class to store the total grid in a map.
 *
 * @author Allen
 */
public class Grid {
    private GridPane mapGridPane;
    //private Cell[][] myCells;
    private ArrayList<ArrayList<Cell>> myCells;
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
        //myCells = new Cell[width][height];
        myCells = new ArrayList<>();
        for(int i=0;i<width;i++){
            myCells.add(new ArrayList<>());
            for(int j=0;j<height;j++){
                myCells.get(i).add(new Cell(i, j));
                //myCells[i][j] = new Cell(i, j);
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
                //myCells[x][y].addActor(activeActor);
                myCells.get(x).get(y).addActor(activeActor);
                gameManager.createActor(activeActor.getActorPrototypeID(), x, y, thisCell.getChildren().size(), 0, 0);
            }
            else {
                if(thisCell.getChildren().size() > 0){
                    thisCell.getChildren().remove(thisCell.getChildren().size()-1);
                    ArrayList<Actor> actorsOfCell = myCells.get(x).get(y).getActors();
                    //ArrayList<Actor> actorsOfCell = myCells[x][y].getActors();
                    gameManager.deleteActor(actorsOfCell.get(actorsOfCell.size()-1).getActorPrototypeID()+x+"-"+y+"-"+(thisCell.getChildren().size()-1));
                    myCells.get(x).remove(y);
                    //myCells[x][y].removeActor();
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
    public ArrayList<ArrayList<Cell>> getCells(){
        return myCells;
    }


    /**
     * Note: the method is probably in the wrong class
     *
     * when the user hovers over an actor in the Grid, ideally we could get info on that actor
     * @param actorIV
     */
    public void hoverInfo(ImageView actorIV){
        Group cell = new Group();

        Tooltip tooltip = new Tooltip();
        tooltip.setText(
                "\n Actor ID, coordinates, name" //TODO: get the right info for the actors
        );

        //tooltip.setFont(new Font("Arial", 16));
        Button actor = new Button("", actorIV);
        actor.setTooltip(new Tooltip(tooltip.getText()));

        cell.getChildren().add(actor);



    }

    public void changeDimensions(int newWidth, int newHeight){
        if(newWidth < gridWidth){
            for(int i=0;i<myCells.size();i++){
                myCells.get(i).subList(newWidth, myCells.get(i).size()-1).clear();
            }
        }
        else if(newWidth > gridWidth){
            for(int i=0;i<myCells.size();i++){
                for(int j=0;j<newWidth-gridWidth;j++){
                    myCells.get(i).add(new Cell(i, myCells.get(i).size()-1));
                    mapGridPane.add(createCell(), i, myCells.get(i).size()-1);
                }
            }
        }
        if(newHeight < gridHeight){
            myCells.subList(newHeight, myCells.size()-1).clear();
        }
        else if(newHeight > gridHeight){
            for(int i=0;i<newHeight-gridHeight;i++){
                myCells.add(new ArrayList<>());
                for(int j=0;j<newWidth;j++){
                    myCells.get(myCells.size()-1).add(new Cell(myCells.size()-1, j));
                    mapGridPane.add(createCell(), myCells.size()-1, j);
                }
            }
        }
        gridWidth = newWidth;
        gridHeight = newHeight;
        ObservableList<Node> children = mapGridPane.getChildren();
        //System.out.println(children.size());
        ArrayList<Node> toRemove = new ArrayList<>();
        for(Iterator<Node> iter = children.iterator();iter.hasNext();){
            Node n = iter.next();
            if(mapGridPane.getRowIndex(n) > newWidth-1 || mapGridPane.getColumnIndex(n) > newHeight-1){
                toRemove.add(n);
                iter.remove();
            }
        }
        mapGridPane.getChildren().removeAll(toRemove);
    }

}
