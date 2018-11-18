package engine.backend;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.HashMap;
import java.util.Map;

public  abstract class Interaction {
    Map<String,String> animationMap;
    Map <String, Message> messageMap;
    String myName;

    //TODO: fill out defaults
    public Interaction(){

    }


    /**
     * @param data: JSON representation of data relevant to interaction
     * @param messages: Map of messages called by interaction
     */
    public Interaction(JSONObject data, Map<String, Message>messages){
        animationMap= new HashMap<>();
        messageMap= messages;
        myName=(String)data.get("name");
        loadAnimationMap((JSONArray) data.get("animations"));
    }


    /**
     * @param data
     * Assume animations look like this:
     * animations:[key:default, path:"/resource/charizard3.png"]
     */
    private void loadAnimationMap(JSONArray data){
        for(int i=0;i<data.size();i+=1){
            JSONObject animation=(JSONObject)data.get(i);
            animationMap.put((String)animation.get("key"),(String)animation.get("path"));
        }
    }

    public String getName(){return myName;}

    /**
     * for testing purposes
     */
    public void serialize(){
        System.out.println(myName);
        for(String s:animationMap.keySet()){System.out.println(s+": "+animationMap.get(s));}
        for(String s:messageMap.keySet()){
            System.out.println(s+":"+messageMap.get(s).getMessageString());
        }
    }
}
