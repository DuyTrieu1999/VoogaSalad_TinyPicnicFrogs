package engine.backend;

import java.util.List;

public class ActorManager {
    List<Actor> allActors;
    List<Actor> activeActors;
    List<Actor> inactiveActors;

    ActorManager(List<Actor> actorList){
        allActors = actorList;
    }


}
