package authoring.authoring_backend;

import engine.backend.Actor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael Glushakov
 * Purpose: Contains a Map of all Actors in the game, all actor management done through this class
 */

public class ActorManager {
    Map<String, Actor> actorMap;
    protected ActorManager(){
        actorMap = new HashMap<>();
    }
    protected String addActor(Actor actor, String id){
        actorMap.put(id,actor);
        return id;
    }
    protected Actor getActor(String id){
        return actorMap.get(id);
    }
    protected void serializeAllActors(){
        for(Actor actor:actorMap.values()){
            actor.serialize();
        }
    }
    protected void serializeActor(String id){
        actorMap.get(id).serialize();
    }
}
