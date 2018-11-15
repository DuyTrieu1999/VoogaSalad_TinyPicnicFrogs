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
    Map<String,String>animationMap;
    Map<String, Interaction>interractionMap;
    Map<String, Integer>myStats;
    private String name;
    protected ActorPrototype(JSONObject data, List<Map<String, Message>> prototypeMessages){
        name=(String)data.get("name");
        animationMap=parseAnimations(data);
        myStats=parseStats((JSONArray) data.get("stats"));
        interractionMap= new HashMap<>();
        parseInterractions((JSONArray)data.get("Interactions"),prototypeMessages);

    }
    protected String getName(){return name;}

    /**
     *
     * @param data: the original JSON of the entire prototype
     * @return the List of Animations for this ActorPrototype in overworld
     */
    private Map<String,String>parseAnimations(JSONObject data){
        JSONArray animations= (JSONArray)data.get("animations");
        Map<String,String>map= new HashMap<>();
        for(int i=0;i<animations.size();i+=1){
            JSONObject animation=(JSONObject) animations.get(i);
            map.put((String)animation.get("key"),(String)animation.get("path"));
        }
        return map;
    }
    private Map<String, Integer>parseStats(JSONArray stats){
        Map<String, Integer> statsMap = new HashMap<>();
        for(int i=0;i<stats.size();i+=1){
            JSONObject stat=(JSONObject)stats.get(i);
            statsMap.put((String)stat.get("key"),Integer.parseInt(String.valueOf(stat.get("value"))));
        }
        return statsMap;

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
            myInteraction= new CombatInteraction(ineractionJSON,interactionMessages);
            interractionMap.put(myInteraction.getName(),myInteraction);
        }
        else if(((String)ineractionJSON.get("type")).equals("collectible")){
            //create new collectible interaction
        }
        else if(((String)ineractionJSON.get("type")).equals("background")){
            //create new background interaction
        }
    }

    /**
     * For testing purposes
     */
    public void serialize(){
        System.out.println(name);
       for(String s:animationMap.keySet()){System.out.print(s+":"+animationMap.get(s)+" | ");}
       System.out.println(" ");
        for(String s:myStats.keySet()){System.out.println(s+": "+myStats.get(s));}
        for (String s:interractionMap.keySet()){
            System.out.println(s);
            interractionMap.get(s).serialize();
        }
    }
}
