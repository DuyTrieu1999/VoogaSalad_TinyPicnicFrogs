package engine.backend;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.HashMap;
import java.util.Map;

public  abstract class Interaction {
    Map<String,String> animationMap;
    Map <String, Message> messageMap;

    /**
     *
     * @param data: JSON representation of data relevant to interaction
     * @param messages: Map of messages called by interaction
     */
    public Interaction(JSONObject data, Map<String, Message>messages){
        animationMap= new HashMap<>();
        messageMap= messages;
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
}
