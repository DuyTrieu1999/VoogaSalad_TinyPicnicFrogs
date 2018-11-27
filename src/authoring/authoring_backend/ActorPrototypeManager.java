package authoring.authoring_backend;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.backend.Message;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Michael GLushakov
 * Purpose: manages Actor Prototypes created by game developer
 */
public class ActorPrototypeManager {
    private Map<String,ActorPrototype>actorPrototypeMap;
    protected ActorPrototypeManager(){
        actorPrototypeMap= new HashMap<>();
    }

    /**
     *
     * @param data JSON representation of data entered by autjor
     * @param prototypeMessages: parsed out messages relevant to each interraction: Each spot is the list is a Map in of the Messages pertaining to the interaction
     */
    protected String createActorPrototype(JSONObject data, List<Map<String, Message>> prototypeMessages){
      testMessageParsing(prototypeMessages);
      ActorPrototype prototype = new ActorPrototype(data,prototypeMessages);
      
      actorPrototypeMap.put(prototype.getName(),prototype);
      return prototype.getName();

    }
    protected ActorPrototype getPrototype(String key){
        return actorPrototypeMap.get(key);
    }

    protected ActorPrototype getNewPrototypeInstance(String key){
        return actorPrototypeMap.get(key).clone();
    }

    private void testMessageParsing( List<Map<String, Message>> prototypeMessages){
        System.out.println("Received:");
        System.out.println(prototypeMessages.get(0).size());
        for(Map<String,Message>map:prototypeMessages){
            for(String s:map.keySet()){
                System.out.println(s+": "+map.get(s).getMessageString());
            }
        }
    }
    protected void serializeAllPrototypes(String path){
        int index=0;
        XStream serializer = new XStream(new DomDriver());
        for(ActorPrototype actor:actorPrototypeMap.values()){
            index+=1;
            String serialized= serializer.toXML(actor);
            try{
                Files.write(Paths.get(path+"prototype-"+index+".xml"),serialized.getBytes());}catch (IOException e){e.printStackTrace();}
        }
    }
    protected void loadPrototype(String key, String path){
        XStream serializer = new XStream(new DomDriver());
        ActorPrototype loadedActorPrototype=(ActorPrototype) serializer.fromXML(Paths.get(path).toFile());
        actorPrototypeMap.put(key,loadedActorPrototype);
    }

    protected void deletePrototype(String name){
        actorPrototypeMap.remove(name);
    }



}
