package engine.backend;

import engine.backend.AI.AI;
import engine.backend.AI.RandomAI;
import engine.controller.Controller;

/**
 * Follows the Service Locator design pattern. Used to locate global objects
 *
 * @Christopher Lin cl349
 */
public class ServiceLocator {


    private static GameWorld myGameWorld;
    private static AI myAI;
    private static ActorManager myActorManager;
    private static CombatManager myCombatManager;
    private static Controller myController;
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
//            System.out.println("NULL");
            provideActorManager(new ActorManager(null));
        }
        return myActorManager;
    }

    public static CombatManager getCombatManager(){
//        if(myCombatManager == null){
//            //myCombatManager = new CombatManager(null, null, null);
//            return null;
//        }
        return myCombatManager;
    }


    public static Controller getController() {
        return myController;
    }

    public static void provideCombatManager(CombatManager combatManager){
        myCombatManager = combatManager;
    }

    public static void provideActorManager(ActorManager actorManager){myActorManager = actorManager;}

    public static void provideController(Controller controller){myController = controller;}

    /**
     * TODO: set null default object
     * Sets the GameWorld used by the game
     * @param ai the GameWorld object to be returned by getter
     */

    public static void provideAI(AI ai){
        myAI = ai;
    }


}
