package authoring.authoring_backend;


/**
 * @author Janice Liu
 * Purpose: Breaks up the map into squares for author use, calculate global coordinates. Everything is square
 */

public class MapManager {
    int squareHeight;
    int squareWidth;
    int mapWidth;
    int mapHeight;

    protected MapManager() { //however the front end wants their map defined from user
    }

    /**
     * The purpose is to divide the parameters given by the author into squares, and then returns the numbers to the
     * GameManager
     *
     * @param width
     * @param height
     * @param
     * @return
     */


    //TODO: doublecheck
    public Integer divideMap(int width, int height, int rows, int cols) {

        int totalSquares = rows * cols;
        mapHeight=height;
        mapWidth=width;
        squareHeight = height / cols;
        squareWidth = width / rows;
        return totalSquares;

    }

    /**
     * Whenever the author places an ActorPrototype onto the map in a specific square, this method will return the
     * coords with respect to the entire map instead of just the square. Uses the squareNum to get the relative position
     * from the whole map
     * @return
     */

    //everything initiated wrt the the top left corner
    //this is for the game engine, which does everything by pixels

    public int[] calculateGlobal(int X, int Y,int row, int col) {

        int[] global = new int[2];
        int globalX = X + X * squareWidth;
        int globalY = Y + Y * squareHeight;

        global[0] = globalX;
        global[1] = globalY;

        return global;
    }

    protected int getMapWidth(){return mapWidth;}
    protected int getMapHeight(){return mapHeight;}
}
