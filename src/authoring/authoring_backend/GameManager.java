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
 * @author Michael Glushakov, Janice Liu
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

    /**
     * @param key key where to store created message in a map
     * @param value the String constitution the body of the message
     */
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

    public String createActorPrototype(JSONObject formData){
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

        return actorPrototypeManager.createActorPrototype(formData,prototypeMessageMapList);

    }

    /**
     *
     * @param actorPrototypeID unique identifier
     * @param x x coordinate in a rectangle
     * @param y y coordinate in a rectangle
     * @param z z coordinate in a rectangle
     * @param row row of a rectangle in a global map
     * @param col column of a rectangle in a global map
     */
    public void createActor(String actorPrototypeID, int x, int y, int z,int row,int col){
        int[] globalCoords = mapManager.calculateGlobal(x,y,row,col);
        actorManager.createActor(actorPrototypeManager.getNewPrototypeInstance(actorPrototypeID),globalCoords[0],globalCoords[1],z);
    }

    /**
     *
     * @param id unique id of the actor in the map
     * @return Actor associated with a particular id
     */
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

    /**
     * This method is called when the front end Map form information is saved so then the model can take care of setting
     * up the map.
     * @param width
     * @param height
     * @param row
     * @param col
     */

    public void setUpMap(int width, int height, int row, int col){
        mapManager.divideMap(width, height, row, col);
    }


    /**
     * Loads the Actors from a pre-existing XML File
     * @param path path to the XML file
     */
    public void loadActors(String path){
        actorManager.loadActors(path);
    }

    /**
     * Loads the message from a pre-existing XML File
     * @param key where to put the message in the message map
     * @param path path to the XML file
     */
    public void loadMessage(String key, String path){
        messageManager.loadMessage(key, path);
    }

    /**
     * Loads the prototype from a pre-existing XML File
     * @param key: where to put the prototype in the prototype map
     * @param path path to the XML file
     */
    public void loadPrototype(String key, String path){
        actorPrototypeManager.loadPrototype(key, path);
    }

    /**
     *
     * @return the list of Author defined Message ID's
     */
    public List<String>getMessageIds(){return messageManager.getMessageId();}

    /**
     * deletes a prototype with a given name/id
     * @param name name of the prototype corresponding to the key in the map
     */
    public void deletePrototype(String name){actorPrototypeManager.deletePrototype(name);}

    /**
     * deletes an actor with a given name/id
     * @param name name of the actor corresponding to the key in the map
     */
    public void deleteActor(String name){actorManager.deleteActor(name);}
    /**
     * deletes a message with a given name/id
     * @param id id of the message corresponding to the key in the map
     */
    public void deleteMessage(String id){messageManager.deleteMessage(id);}



}
