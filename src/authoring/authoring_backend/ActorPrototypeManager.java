package authoring.authoring_backend;

import engine.backend.Message;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @author Michael GLushakov
 * Purpose: manages Actor Prototypes created by game developer
 */
public class ActorPrototypeManager {
    private Map<String,ActorPrototype>actorPrototypeMap;
    protected ActorPrototypeManager(){}

    /**
     *
     * @param data JSON representation of data entered by autjor
     * @param prototypeMessages: parsed out messages relevant to each interraction: Each spot is the list is a Map in of the Messages pertaining to the interaction
     */
    protected void createActorPrototype(JSONObject data, List<Map<String, Message>> prototypeMessages){
      testMessageParsing(prototypeMessages);
      ActorPrototype prototype = new ActorPrototype(data,prototypeMessages);

     // actorPrototypeMap.put(prototype.getName(),prototype);

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
}
