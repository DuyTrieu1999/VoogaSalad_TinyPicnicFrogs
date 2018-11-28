package engine.backend;

public class ServiceLocator {

    private static GameWorld myGameWorld;
    private static AI myAI;
    private static ActorManager myActorManager;
    private static CombatManager myCombatManager;
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


    public static ActorManager getActorManager(){
        if(myActorManager == null){
            provideActorManager(new ActorManager(null));
        }
        return myActorManager;
    }

    public static CombatManager getCombatManager(){
        if(myCombatManager == null){
            myCombatManager = new CombatManager(null, null, null);
        }
        return myCombatManager;
    }

    public static void provideCombatManager(CombatManager combatManager){
        myCombatManager = combatManager;
    }

    public static void provideActorManager(ActorManager actorManager){myActorManager = actorManager;}
    public static void provideAI(AI ai){
        myAI = ai;
    }


}
