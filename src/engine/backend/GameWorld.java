package engine.backend;

/**
 * @author Christopher Lin cl349
 */
public class GameWorld {

    private static int myMapHeight;
    private static int myMapWidth;
    private String myName;

    GameWorld(int mapHeight, int mapWidth){
        myMapHeight = mapHeight;
        myMapWidth = mapWidth;
        myName = "Game";
    }

    public static int getMapHeight(){
        return myMapHeight;
    }
    public static int getMapWidth(){
        return myMapWidth;
    }
    public String getMyName() { return myName; }




}
