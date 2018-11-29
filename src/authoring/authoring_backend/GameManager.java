package authoring.authoring_backend;


import engine.backend.Actor;
import engine.backend.Coordinate;
import engine.backend.Message;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Michael Glushakov
 * Purpose: Controller for authoring back-end, the only class that the front-end communicates with
 * Dependencies: ActorManager, ActorPrototypeManager, MessageManager, MapManager
 * Usages: Front-end should pass in the user-input through availible public methods. Return values TBD
 */
public class GameManager {
    private ActorManager actorManager;
    private ActorPrototypeManager actorPrototypeManager;
    private MessageManager messageManager;
    private MapManager mapManager;

    public GameManager(){
        actorManager=new ActorManager();
        actorPrototypeManager=new ActorPrototypeManager();
        messageManager= new MessageManager();
        mapManager= new MapManager();
    }


    public void createMessage(String key, String value){
        messageManager.createMessage(key,value);
    }
    public Message getMessage(String key){return messageManager.getMessage(key);}
    public void createMap(JSONObject mapData){}

    /**
     * @param formData passed from front-end: JSON representation of the game developer's input
     * Parses the messages out before calling the ActorPrototypeManager createActorPrototype method
     * See JSON helper file for what data would look like
     */

    public void createActorPrototype(JSONObject formData){
        JSONArray interractionArr=(JSONArray)formData.get("interactions");
        List<Map<String, Message>> prototypeMessageMapList= new ArrayList<Map<String, Message>>();//Each spot in the list is a map of messages sent by that interraction
        for(int i=0;i<interractionArr.size();i+=1)
        {
            JSONObject interraction=(JSONObject) interractionArr.get(i);
            JSONArray interractionMessages=(JSONArray)interraction.get("messages");

            Map<String,Message>messageMap=new HashMap<>();
            for(int j=0;j<interractionMessages.size();j+=1){
                JSONObject messagePair= (JSONObject)interractionMessages.get(j);
                messageMap.put((String)messagePair.get("key"),messageManager.getMessage((String)messagePair.get("messageKey")));
            }
            prototypeMessageMapList.add(messageMap);
        }

        actorPrototypeManager.createActorPrototype(formData,prototypeMessageMapList);

    }

    public void createActor(String actorPrototypeID, int x, int y, int z,int row,int col){
        int[] globalCoords = mapManager.calculateGlobal(x,y,row,col);
        actorManager.createActor(actorPrototypeManager.getNewPrototypeInstance(actorPrototypeID),globalCoords[0],globalCoords[1],z);
    }
    public Actor getActor(String id){return actorManager.getActor(id);}
    public ActorPrototype getPrototype(String id){return actorPrototypeManager.getPrototype(id);


    }



    /**
     * Saves all created actors and messages
     * @param gamePath: path of the folder to which the game data is saved
     * @param authoringPath: path of the folder to which the authoring data is saved
     */
    public void saveGame(String gamePath, String authoringPath){
        actorManager.serializeAllActors(gamePath);
        messageManager.serializeAllMessages(gamePath);
        actorPrototypeManager.serializeAllPrototypes(authoringPath);
    }

    public void loadActors(String path){
        actorManager.loadActors(path);
    }
    public void loadMessage(String key, String path){
        messageManager.loadMessage(key, path);
    }
    public void loadPrototype(String key, String path){
        actorPrototypeManager.loadPrototype(key, path);
    }





}
