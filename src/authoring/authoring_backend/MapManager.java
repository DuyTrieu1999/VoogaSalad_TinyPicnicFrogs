package authoring.authoring_backend;

import engine.backend.Actor;
import engine.backend.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Janice Liu
 * Purpose: Breaks up the map into squares for author use, calculate global coordinates. Everything is a square
 */

public class MapManager {
    private int myMapWidth;
    private int myMapHeight;
    private int square;
    HashMap<Integer, List<Actor>> mySquares;
   // Map<Integer, Coordinate> globalCoords;
    int squareHeight;
    int squareWidth;

    protected MapManager(int mapWidth, int mapHeight, int n) { //however the front end wants their map defined from user
        myMapWidth = mapWidth;
        myMapHeight = mapHeight;
        square = n;
        squareHeight = myMapHeight / n;
        squareWidth = myMapWidth / n;
        //globalCoords = new HashMap<Integer, Coordinate>();

    }

    /**
     * The purpose is to divide the parameters given by the author into squares, and then returns the numbers to the
     * GameManager
     *
     * @param width
     * @param height
     * @param n
     * @return
     */

    public Integer divideMap(int width, int height, int n) {

        int totalSquares = width / n * height / n;
        //need to communicate with front end how the squares are being laid out
        return totalSquares;

    }

    /**
     * Whenever the author places an ActorPrototype onto the map in a specific square, this method will return the
     * coords with respect to the entire map instead of just the square. Uses the squareNum to get the relative position
     * from the whole map
     * @return
     */

    //everything initiated wrt the the top left corner
    //ask Michael: assumes that the x, y, z coords are all wrt to the square itself, and we are using coordinates
    //for the square  map

    public Coordinate calculateGlobal(int squareX, int squareY, Actor a) {
//        int row = squareNum / square;
//        int col = squareNum % square; this was assuming square labeling 1, 2, 3...

        Coordinate actorCoords = a.getCoordinate();
        int globalZ = actorCoords.getZ();

        int globalX = actorCoords.getX() + squareX * squareWidth;
        int globalY = actorCoords.getY() + squareY * squareHeight;

        Coordinate global = new Coordinate(globalX, globalY, globalZ);

        return global;
    }

}
