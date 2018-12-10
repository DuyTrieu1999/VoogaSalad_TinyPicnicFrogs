package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
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
    private static final int DEFAULT_WIDTH  = 30;
    private static final int DEFAULT_HEIGHT = 20;
    private int cellWidth;
    private int cellHeight;
    private ArrayList<ArrayList<Cell>> myCells;
    private int gridWidth;
    private int gridHeight;
    private String programName;
    private GameManager gameManager;
    private StackPane lastSelectedCell = null;

    /**
     * Default constructor makes a 30x20 grid.
     * @param name Program name.
     * @param myManager GameManager of current game.
     */
    Grid(String name, int cellWidth, int cellHeight, GameManager myManager){
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT, cellWidth, cellHeight, name, myManager);
    }

    /**
     * Constructor
     * @param width Width in squares.
     * @param height Height in squares.
     * @param name Program name.
     * @param myManager GameManager of current game.
     */
    Grid(int width, int height, int cWidth, int cHeight, String name, GameManager myManager){
        cellWidth = cWidth + 2;
        cellHeight = cHeight + 2;
        gridWidth = width;
        gridHeight = height;
        programName = name;
        gameManager = myManager;
        mapGridPane = new GridPane();
        //myCells = new Cell[width][height];
        myCells = new ArrayList<>();
        for(int i=0;i<height;i++){
            myCells.add(new ArrayList<>());
            for(int j=0;j<width;j++){
                //System.out.println("Initializing a cell at (" + j + ", " + i + ")");
                myCells.get(i).add(new Cell(j, i));
                //myCells[i][j] = new Cell(i, j);
                mapGridPane.add(createCell(), j, i);
            }
        }
    }

    /**
     * Creates a cell.
     * @return A StackPane with the cell contents.
     */
    private StackPane createCell(){
        StackPane thisCell = new StackPane();
        //System.out.println(cellWidth + " " + cellHeight);
        thisCell.setMinSize(cellWidth, cellHeight);
        thisCell.setStyle("-fx-border-color: black;");

        thisCell.setOnMouseClicked(event -> {
            Actor activeActor = ActiveItem.getActiveItem(programName);
            int x = GridPane.getColumnIndex(thisCell);
            int y = GridPane.getRowIndex(thisCell);
            if(activeActor != null){
                if(event.isShiftDown()){
                    if(lastSelectedCell != null){
                        //System.out.println("Trying to place multiple tiles!");
                        int lastX = GridPane.getColumnIndex(lastSelectedCell);
                        int lastY = GridPane.getRowIndex(lastSelectedCell);
                        //System.out.println(x + " " + y + " " + lastX + " " + lastY);
                        //draw a square
                        if(x > lastX){
                            if(y > lastY){
                                for(int i=lastX;i<=x;i++){
                                    for(int j=lastY;j<=y;j++){
                                        //System.out.println("Trying to find the StackPane at " + i + ", " + j);
                                        StackPane currentCell = (StackPane)getGridPaneNodeAt(i, j);
                                        addActor(currentCell, activeActor, i, j);
/*                                        currentCell.getChildren().add(activeActor.getActorImage());
                                        myCells.get(j).get(i).addActor(activeActor);
                                        gameManager.createActor(activeActor.getActorPrototypeID(), i, j, currentCell.getChildren().size(), 0, 0);*/
                                    }
                                }
                            }
                            else if(y < lastY){
                                for(int i=lastX;i<=x;i++){
                                    for(int j=y;y<=lastY;j++){
                                        StackPane currentCell = (StackPane)getGridPaneNodeAt(i, j);
                                        addActor(currentCell, activeActor, i, j);
/*                                        currentCell.getChildren().add(activeActor.getActorImage());
                                        myCells.get(i).get(j).addActor(activeActor);
                                        gameManager.createActor(activeActor.getActorPrototypeID(), i, j, currentCell.getChildren().size(), 0, 0);*/
                                    }
                                }
                            }
                        }
                        else if(x < lastX){
                            if(y > lastY){
                                for(int i=x;i<=lastX;i++){
                                    for(int j=lastY;j<=y;j++){
                                        StackPane currentCell = (StackPane)getGridPaneNodeAt(i, j);
                                        addActor(currentCell, activeActor, i, j);
/*                                        currentCell.getChildren().add(activeActor.getActorImage());
                                        myCells.get(i).get(j).addActor(activeActor);
                                        gameManager.createActor(activeActor.getActorPrototypeID(), i, j, currentCell.getChildren().size(), 0, 0);*/
                                    }
                                }
                            }
                            else if(y < lastY){
                                for(int i=x;i<=lastX;i++){
                                    for(int j=y;j<=lastY;j++){
                                        StackPane currentCell = (StackPane)getGridPaneNodeAt(i, j);
                                        addActor(currentCell, activeActor, i, j);
/*                                        currentCell.getChildren().add(activeActor.getActorImage());
                                        myCells.get(i).get(j).addActor(activeActor);
                                        gameManager.createActor(activeActor.getActorPrototypeID(), i, j, currentCell.getChildren().size(), 0, 0);*/
                                    }
                                }
                            }
                        }
                    }
                }
                else {
                    addActor(thisCell, activeActor, x, y);
/*                    thisCell.getChildren().add(activeActor.getActorImage());
                    //myCells[x][y].addActor(activeActor);
                    myCells.get(x).get(y).addActor(activeActor);
                    gameManager.createActor(activeActor.getActorPrototypeID(), x, y, thisCell.getChildren().size(), 0, 0);*/
                }
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
            lastSelectedCell = thisCell;
        });

        return thisCell;
    }

    public void addActor(StackPane cell, Actor actor, int x, int y){
        cell.getChildren().add(actor.getActorImage());
        myCells.get(x).get(y).addActor(actor);
        gameManager.createActor(actor.getActorPrototypeID(), cellWidth*x, cellHeight*y, cell.getChildren().size(), x, y);
    }

    public void addActor(Actor actor, int x, int y){
        StackPane cell = (StackPane)getGridPaneNodeAt(x, y);
        addActor(cell, actor, x, y);
    }

    public void addActorFrontendOnly(Actor actor, int x, int y){
        StackPane cell = (StackPane)getGridPaneNodeAt(x, y);
        cell.getChildren().add(actor.getActorImage());
        myCells.get(x).get(y).addActor(actor);
    }

    /**
     * Gets the GridPane of this grid.
     * @return GridPane
     */
    GridPane getGridPane(){
        return mapGridPane;
    }

    ScrollPane getScrollPane(){
        ScrollPane thisMap = new ScrollPane(mapGridPane);
        thisMap.setPrefViewportHeight(575);
        thisMap.setPrefViewportWidth(725);
        return thisMap;
    }

    /**
     * Gets matrix of cells in this grid.
     * @return Matrix of cells contained in this grid.
     */
    ArrayList<ArrayList<Cell>> getCells(){
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

    void changeDimensions(int newWidth, int newHeight){
        //System.out.println("Before we start, myCells has a height of " + myCells.size() + " and the first row of myCells has a width of " + myCells.get(0).size());
        if(newWidth < gridWidth){
            for (ArrayList<Cell> myCell : myCells) {
                myCell.subList(newWidth - 1, myCell.size() - 1).clear();
            }
        }
        else if(newWidth > gridWidth){
            for(int i=0;i<myCells.size();i++){
                for(int j=0;j<newWidth-gridWidth;j++){
                    //System.out.println("Just added a new cell to (" + myCells.get(i).size() + ", " + i + ")!");
                    myCells.get(i).add(new Cell(myCells.get(i).size()-1, i));
                    mapGridPane.add(createCell(), myCells.get(i).size()-1, i);
                }
            }
        }

        if(newHeight < gridHeight){
            myCells.subList(newHeight-1, myCells.size()-1).clear();
        }
        else if(newHeight > gridHeight){
            for(int i=0;i<newHeight-gridHeight;i++){
                myCells.add(new ArrayList<>());
                for(int j=0;j<newWidth;j++){
                    //System.out.println("There are " + myCells.size() + " rows so the next row will be added at row " + (myCells.size()-1));
                    myCells.get(myCells.size()-1).add(new Cell(j, myCells.size()-1));
                    mapGridPane.add(createCell(), j, myCells.size()-1);
                }
            }
        }
        //System.out.println("After height adjustments, myCells has a height of " + myCells.size() + " and the first row of myCells has a width of " + myCells.get(0).size());

        //System.out.println("After all adjustments, myCells has a height of " + myCells.size() + " and the first row of myCells has a width of " + myCells.get(0).size());
        gridWidth = newWidth;
        gridHeight = newHeight;
        ObservableList<Node> children = mapGridPane.getChildren();
        //System.out.println(children.size());

        ArrayList<Node> toRemove = new ArrayList<>();
        for(Iterator<Node> iter = children.iterator();iter.hasNext();){
            Node n = iter.next();
            if(GridPane.getColumnIndex(n) > newWidth-1 || mapGridPane.getRowIndex(n) > newHeight-1){
                toRemove.add(n);
                iter.remove();
            }
        }
        mapGridPane.getChildren().removeAll(toRemove);

    }

    public Node getGridPaneNodeAt(int x, int y){
        Node result = null;
        ObservableList<Node> gridPaneChildren = mapGridPane.getChildren();
        //System.out.println(gridPaneChildren.size());
        for(Node n:gridPaneChildren){
            if(mapGridPane.getColumnIndex(n) == x && mapGridPane.getRowIndex(n) == y){
                result = n;
                break;
            }
        }
        return result;
    }

}
