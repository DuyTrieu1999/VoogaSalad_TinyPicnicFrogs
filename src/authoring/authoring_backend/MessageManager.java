package authoring.authoring_backend;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.backend.Actor;
import engine.backend.Message;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Michael Glushakov
 * Purpose: manages all messages created by game author
 */
public class MessageManager {
    Map<String, Message> messageMap;
    protected MessageManager(){
        messageMap= new HashMap<>();
    }

    /**
     *
     * @param key key of message in the map
     * @return message in the map
     */
  protected Message getMessage(String key){
        return messageMap.get(key);
  }

    /**
     *
     * @param key
     * @param messageBody
     */
  protected void createMessage(String key, String messageBody){
        messageMap.put(key,new Message(messageBody));
  }

    /**
     * Serializes all messages to XML file
     * @param path path to folder of xml file
     */
  protected void serializeAllMessages(String path){
      int index=0;
      XStream serializer = new XStream(new DomDriver());
      for(Message message:messageMap.values()){
          index+=1;
          String serialized= serializer.toXML(message);
          try{
              Files.write(Paths.get(path+"message-"+index+".xml"),serialized.getBytes());}catch (IOException e){e.printStackTrace();}
      }

  }

    /**
     * Loads message from an XML file
     * @param key key for the message to be saved under
     * @param path path to the message XML file
     */
    protected void loadMessage(String key,String path){
        XStream serializer = new XStream(new DomDriver());
        Message loadedMessage=(Message)serializer.fromXML(Paths.get(path).toFile());
        messageMap.put(key,loadedMessage);
    }
    protected List<String> getMessageId(){
      List<String>messageList = new ArrayList<>();
      messageList.addAll(messageMap.keySet());
      return messageList;
    }

    /**
     * Removes message from map
     * @param id id of the message to be removed
     */
    protected void deleteMessage(String id){messageMap.remove(id);}

}
