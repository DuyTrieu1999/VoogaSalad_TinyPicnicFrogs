package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import java.util.HashMap;

/**
 * Map class that stores the grid and cells.
 *
 * @author Allen Qiu
 */
public class Map {
    private int id;
    private Grid myGrid;
    private int width;
    private int height;
    private String programName;
    private HashMap<Pair<Integer, Integer>, String> connectedPoints;

    /**
     * Default constructor that starts ordering at ID 1.
     * @param mapWidth Width of map in squares.
     * @param mapHeight Height of map in squares.
     * @param pName Name of the program.
     * @param myManager GameManager of the game.
     */
    Map(int mapWidth, int mapHeight, String pName, GameManager myManager){
        this(1, mapWidth, mapHeight, pName, myManager);
    }

    /**
     * Constructor
     * @param mapID ID of this map
     * @param mapWidth Width of map in squares.
     * @param mapHeight Height of map in squares.
     * @param pName Name of the program.
     * @param myManager GameManager of the game.
     */
    Map(int mapID, int mapWidth, int mapHeight, String pName, GameManager myManager){
        id = mapID;
        width = mapWidth;
        height = mapHeight;
        programName = pName;
        myGrid = new Grid(width, height, programName, myManager);
        connectedPoints = new HashMap<>();
    }

    /**
     * Gets the ID of the map.
     * @return The ID of the map.
     */
    public int getMapID(){
        return id;
    }

    /**
     * String print function.
     * @return Map and its ID.
     */
    public String toString(){
        return "Map " + id;
    }

    /**
     * Gets a GridPane of the map.
     * @return GridPane of the map.
     */
    public GridPane getGridPane(){
        return myGrid.getGridPane();
    }

    /**
     * Gets the grid in this map.
     * @return Grid contained in this map.
     */
    public Grid getGrid(){
        return myGrid;
    }
}
