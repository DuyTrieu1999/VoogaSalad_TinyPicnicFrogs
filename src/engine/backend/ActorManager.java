package engine.backend;

import java.util.ArrayList;
import java.util.List;

public class ActorManager {
    List<Actor> allActors;
    List<Actor> activeActors;
    List<Actor> inactiveActors;
    Actor myPlayerActor;


    public ActorManager(List<Actor> actorList){
        allActors = actorList;
        inactiveActors = new ArrayList<>();
        activeActors = allActors;
        setPlayerActor();
    }

    public Actor getPlayerActor(){
        return myPlayerActor;
    }

<<<<<<< HEAD
    public void setPlayerActor(PlayerActor player) {
        myPlayerActor = player;
    }
=======
    public void setPlayerActor(PlayerActor player){
        myPlayerActor = player;
    }

>>>>>>> 945f63682c85cc63ff9b8fefd4ef5e4178d1322a
    /**
     * sets myPlayerActor
     */
    private void setPlayerActor(){
        for(Actor actor : allActors){
            if(actor.getIsPlayerActor()){
                myPlayerActor = actor;
                break;
            }
        }
    }

    public List<Actor> getInactiveActors() { return inactiveActors;}

    /**
     * @return
     */
    public List<AnimationObject> getAnimationObjects() {
        List<AnimationObject> activeAnimationObjects = new ArrayList<>();
        System.out.println("ACTIVE ACTORS:"+activeActors.size());
        for(Actor actor : activeActors) {
            activeAnimationObjects.add(actor.getActiveAnimation());
        }
        return activeAnimationObjects;
    }

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

    public List<Actor> getActiveActors() {
        return activeActors;
    }
}
