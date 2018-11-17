package engine.backend;

import java.util.List;

public class ActorManager {
    List<Actor> allActors;
    List<Actor> activeActors;
    List<Actor> inactiveActors;
    PlayerActor myPlayerActor;

    ActorManager(List<Actor> actorList){
        allActors = actorList;
    }

    public PlayerActor getPlayerActor(){
        return myPlayerActor;
    }
}
