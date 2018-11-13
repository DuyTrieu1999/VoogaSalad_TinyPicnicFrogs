package engine.backend;

public class GameWorld {

    private static int myMapHeight;
    private static int myMapWidth;

    GameWorld(int mapHeight, int mapWidth){
        myMapHeight = mapHeight;
        myMapWidth = mapWidth;
    }

    public static int getMapHeight(){
        return myMapHeight;
    }
    public static int getMapWidth(){
        return myMapWidth;
    }




}
