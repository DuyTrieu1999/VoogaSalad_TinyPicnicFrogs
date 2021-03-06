package authoring.authoring_backend;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.backend.Actor;
import engine.backend.Coordinate;
import engine.backend.DialogueTreeNode;
import engine.backend.Message;
import javafx.stage.FileChooser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    private DialogManager dialogManager;

    public GameManager(){
        actorManager=new ActorManager();
        actorPrototypeManager=new ActorPrototypeManager();
        messageManager= new MessageManager();
        mapManager= new MapManager();

        dialogManager=new DialogManager();
    }
    public DialogueTreeNode createDialog(String dialogName,JSONArray dialogNodes, JSONObject dialogTree){
        return dialogManager.createDialog(dialogName,dialogNodes,dialogTree);
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

    public void createActorPrototype(JSONObject formData){
        JSONArray interractionArr=(JSONArray)formData.get("interactions");
        Map<String, DialogueTreeNode> dialogNames = new HashMap<>();



        List<Map<String, Message>> prototypeMessageMapList= new ArrayList<Map<String, Message>>();//Each spot in the list is a map of messages sent by that interraction
        for(int i=0;i<interractionArr.size();i+=1)
        {
            JSONObject interraction=(JSONObject) interractionArr.get(i);

            JSONArray interractionMessages=(JSONArray)interraction.get("messages");

            if((interraction.get("type")).equals("dialog")){
                String dialogKey = (String)interraction.get("dialogKey");
                DialogueTreeNode dtNode =dialogManager.getDialog(dialogKey);
                dialogNames.put((String)interraction.get("interractionName"), dtNode);
        }

            Map<String,Message>messageMap=new HashMap<>();
            for(int j=0;j<interractionMessages.size();j+=1){
                JSONObject messagePair= (JSONObject)interractionMessages.get(j);
                messageMap.put((String)messagePair.get("key"),messageManager.getMessage((String)messagePair.get("messageKey")));
            }
            prototypeMessageMapList.add(messageMap);

        }
        ArrayList<Message> aMessages = new ArrayList<>();
        ArrayList<Message> dMessages = new ArrayList<>();
        JSONArray activeMessages = (JSONArray) formData.get("ActivateMessages");
        for(int i = 0; i < activeMessages.size(); i++){
            Message m = messageManager.getMessage((String)activeMessages.get(i));
            aMessages.add(m);
        }

        JSONArray deactiveMessages = (JSONArray) formData.get("DeactivateMessages");
        for(int i = 0; i < deactiveMessages.size(); i++){
            Message m = messageManager.getMessage((String)deactiveMessages.get(i));
            dMessages.add(m);
        }

        actorPrototypeManager.createActorPrototype(formData,prototypeMessageMapList,aMessages, dMessages, dialogNames);


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
     *
     * @param titleP game title
     * @param descriptionP game description
     * @param filePath file path to xml files
     */
    public void saveGame(String titleP,String descriptionP, String filePath)throws SaveException{
        filePath+="/";
        System.out.println("fired");
        GameData data = new GameData(titleP,descriptionP,filePath,mapManager.getMapWidth(),mapManager.getMapHeight(),mapManager.squareWidth,mapManager.squareHeight);
        actorManager.serializeAllActors(data.getPath());
        messageManager.serializeAllMessages(data.getPath());
        actorPrototypeManager.serializeAllPrototypes(data.getPath());
        XStream serializer =  new XStream(new DomDriver());
        String dataStr;
        try{
            File gameMap= new File("./resources/games.xml");
            if(gameMap.exists()){
                System.out.println("exists");
                Map<String,GameData>gameList=(Map<String,GameData>)serializer.fromXML(gameMap);
                gameList.put(data.getPath(),data);
                dataStr=serializer.toXML(gameList);
            }
            else{
                Map<String,GameData>gameList= new HashMap<>();
                gameList.put(data.getPath(),data);
                dataStr=serializer.toXML(gameList);
            }
            Files.write(Paths.get("./resources/"+"games.xml"),dataStr.getBytes());}catch (IOException e){throw new SaveException();}
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

    public void updateMessageMap(String key, String value){
        messageManager.createMessage(key, value);
        //TODO: fix this, then use the call in the front end
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
     * @param path path to the XML file
     */
    public void loadMessages(String path){
        messageManager.loadMessages(path);
    }

    /**
     * Loads the prototype from a pre-existing XML File
     * @param path path to the XML file
     */
    public void loadPrototypes(String path){
        actorPrototypeManager.loadPrototypes(path);
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
    /**
     * @return Observable actors for front-end use
     */

    public List<ObservableActor> getObservableActors(){return actorManager.getObservableList();}
    /**
     * @return Observable prototypes for front-end use
     */

    public List<ObservablePrototype>getObservablePrototypes(){return actorPrototypeManager.getObservableList();}




}
