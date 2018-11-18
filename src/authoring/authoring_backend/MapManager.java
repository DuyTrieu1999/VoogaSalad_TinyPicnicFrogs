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
        //globalCoords = new HashMap<Integer, Coordinate>();

    }

    /**
     * The purpose is to divide the parameters given by the author into squares, and then create an empty
     * map which assigns those squares to a list of Actor Objects.
     *
     * @param width
     * @param height
     * @param n
     * @return
     */

    public Map<Integer, List<Actor>> divideMap(int width, int height, int n) {
        mySquares = new HashMap<Integer, List<Actor>>();
        int totalSquares = width / n * height / n;
        squareHeight = height / n;
        squareWidth = width / n;

        //need to communicate with front end how the squares are being laid out
        for (int i = 0; i < totalSquares; i++) {
            mySquares.put(i, new ArrayList<Actor>());
        }
        return mySquares;

    }

    /**
     * Whenever the author places an ActorPrototype onto the map in a specific square, this method will return the
     * coords with respect to the entire map instead of just the square. Uses the squareNum to get the relative position
     * from the whole map
     * @return
     */

    //everything initiated wrt the the top left corner
    //ask Michael: assumes that the x, y, z coords are all wrt to the square itself, and that we are numbering
    //the squares in a specific way
    public Coordinate calculateGlobal(int squareNum, Actor a) {
        int row = squareNum / square;
        int col = squareNum % square;


        Coordinate actorCoords = a.getCoordinate();
        int globalZ = actorCoords.getZ();

        int globalX = actorCoords.getX() + row * squareWidth;
        int globalY = actorCoords.getY() + col*squareHeight;

        Coordinate global = new Coordinate(globalX, globalY, globalZ);

        return global;
    }

    public void updateMap(Actor a, int squareNum){
        //everytime the author puts something on the map, the map must be updated, mySquares
        mySquares.get(squareNum).add(a);


    }
    //update GameManager to communicate coords and updated map


}
