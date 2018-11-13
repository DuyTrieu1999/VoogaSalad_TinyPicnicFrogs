package engine.backend;

public class ServiceLocator {

    private static GameWorld myGameWorld;
    public static GameWorld getGameWorld(){
        if(myGameWorld == null){
            provideGameWorld(new GameWorld(0, 0));
        }
        return myGameWorld;
    }

    public static void provideGameWorld(GameWorld gameWorld){
        myGameWorld = gameWorld;
    }
}
