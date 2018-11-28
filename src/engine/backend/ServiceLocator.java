package engine.backend;

public class ServiceLocator {

    private static GameWorld myGameWorld;
    private static AI myAI;
    private static ActorManager myActorManager;

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
            provideAI(new RandomAI());
        }
        return myAI;
    }

    public static ActorManager getActorManager(){
        if(myActorManager == null){
            provideActorManager(new ActorManager(null));
        }
        return myActorManager;
    }

    public static void provideActorManager(ActorManager actorManager){myActorManager = actorManager;}
    public static void provideAI(AI ai){
        myAI = ai;
    }


}
