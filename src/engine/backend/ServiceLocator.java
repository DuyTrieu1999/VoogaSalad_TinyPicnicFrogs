package engine.backend;

import engine.backend.Commands.randomAI;

public class ServiceLocator {

    private static GameWorld myGameWorld;
    private static AI myAI;

    public static GameWorld getGameWorld(){
        if(myGameWorld == null){
            provideGameWorld(new GameWorld(0, 0));
        }
        return myGameWorld;
    }

    public static void provideGameWorld(GameWorld gameWorld){
        myGameWorld = gameWorld;
    }

    public static AI getAI(){
        if(myGameWorld == null){
            provideAI(new randomAI());
        }
        return myAI;
    }

    public static void provideAI(AI ai){
        myAI = ai;
    }
}
