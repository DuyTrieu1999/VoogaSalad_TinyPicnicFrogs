package engine.backend;

/**
 * Follows the Service Locator design pattern. Used to locate global objects
 *
 * @Christopher Lin cl349
 */
public class ServiceLocator {


    private static GameWorld myGameWorld;
    private static AI myAI;
    private static ActorManager myActorManager;

    /**
     * Defaults to a gameworld with dimensions 0
     *
     * @return the GameWorld object
     */
    public static GameWorld getGameWorld(){
        if(myGameWorld == null){
            provideGameWorld(new GameWorld(0, 0));
        }
        return myGameWorld;
    }

    /**
     * Sets the GameWorld used by the game
     * @param gameWorld the GameWorld object to be returned by getter
     */

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

    /**
     * TODO: set null default objects
     *  Sets the ActorManager used by the game
     * @param actorManager the GameWorld object to be returned by getter
     *
     */

    public static void provideActorManager(ActorManager actorManager){myActorManager = actorManager;}

    /**
     * TODO: set null default object
     * Sets the GameWorld used by the game
     * @param ai the GameWorld object to be returned by getter
     */

    public static void provideAI(AI ai){
        myAI = ai;
    }


}
