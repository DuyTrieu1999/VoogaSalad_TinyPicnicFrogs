package engine.backend;

import java.util.ArrayList;
import java.util.List;

public class ActorManager {
    List<Actor> allActors;
    List<Actor> activeActors;
    List<Actor> inactiveActors;
    PlayerActor myPlayerActor;

    ActorManager(List<Actor> actorList){
        allActors = actorList;
        inactiveActors = allActors;
        activeActors = new ArrayList<>();
    }

    public PlayerActor getPlayerActor(){
        return myPlayerActor;
    }

    public List<Actor> getActiveActors() { return activeActors;}

    public List<Actor> getInactiveActors() { return inactiveActors;}

    /**
     * changes an actor's state
     * @param removeList
     * @param addList
     * @param actor
     */
    private void changeActorState(List<Actor> removeList, List<Actor> addList, Actor actor) {
        removeList.remove(actor);
        addList.add(actor);
    }

    /**
     * activates a given actor
     * @param actor
     */
    public void activate(Actor actor) {
        changeActorState(inactiveActors, activeActors, actor);
    }

    /**
     * inactivates a given actor
     * @param actor
     */
    public void inactivate(Actor actor) {
        changeActorState(activeActors, inactiveActors, actor);
    }


}
