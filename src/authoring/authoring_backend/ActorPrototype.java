package authoring.authoring_backend;
import engine.backend.CombatInteraction;
import engine.backend.Interaction;
import engine.backend.Message;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Michael Glushakov
 * Purpose: contains actor data without world coordinates
 * Dependencies: Interraction
 */
public class ActorPrototype {
    List<String>animationList;
    Map<String, Interaction>interractionMap;
    private String name;
    protected ActorPrototype(JSONObject data, List<Map<String, Message>> prototypeMessages){
        name=(String)data.get("name");
        animationList=parseAnimations(data);
        interractionMap= new HashMap<>();
        parseInterractions((JSONArray)data.get("Interactions"),prototypeMessages);
    }
    protected String getName(){return name;}

    /**
     *
     * @param data: the original JSON of the entire prototype
     * @return the List of Animations for this ActorPrototype in overworld
     */
    private List<String>parseAnimations(JSONObject data){
        JSONArray animations= (JSONArray)data.get("animations");
        List<String>animationsList= new ArrayList<>();
        for(int i=0;i<animations.size();i+=1){
            animationsList.add((String)animations.get(i));
        }
        return animationsList;
    }
    private void parseInterractions(JSONArray data,List<Map<String, Message>> prototypeMessages){
        for(int i=0;i<data.size();i+=1){
            parseInteraction((JSONObject)data.get(i),prototypeMessages.get(i));
        }
    }

    /**
     * Constructs the interraction object
     * @param ineractionJSON: JSON or interraction related data
     * @param interactionMessages: messages that this interraction fires
     */
    private void parseInteraction(JSONObject ineractionJSON, Map<String, Message>interactionMessages){
        Interaction myInteraction;
        if(((String)ineractionJSON.get("type")).equals("fight")){
        }
        else if(((String)ineractionJSON.get("type")).equals("collectible")){
            //create new collectible interaction
        }
        else if(((String)ineractionJSON.get("type")).equals("background")){
            //create new background interaction
        }
    }
}
