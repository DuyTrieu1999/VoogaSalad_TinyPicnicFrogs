package authoring.authoring_backend;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.backend.*;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    /**
     * Saves all actors
     * @param path: path of the folder where to store actors
     */
    protected void serializeAllActors(String path){
        int index=0;
        XStream serializer = new XStream(new DomDriver());
        for(Actor actor:actorMap.values()){
            index+=1;
            String serialized= serializer.toXML(actor);
            try{
            Files.write(Paths.get(path+"actor-"+index+".xml"),serialized.getBytes());}catch (IOException e){e.printStackTrace();}
        }
    }
    protected void createActor(ActorPrototype actorPrototype, int x, int y, int z){
        Actor actor= new Actor(actorPrototype,x,y,z);
        addActor(actor,actorPrototype.getName());
    }
    protected void serializeActor(String id){
        actorMap.get(id).serialize();
    }
    protected void loadActor(String key, String path){
        XStream serializer = new XStream(new DomDriver());
        Actor loadedActor=(Actor) serializer.fromXML(Paths.get(path).toFile());
        actorMap.put(key,loadedActor);
    }

}
