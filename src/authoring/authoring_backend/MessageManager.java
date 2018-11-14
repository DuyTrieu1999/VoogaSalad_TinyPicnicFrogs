package authoring.authoring_backend;

import engine.backend.Message;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael Glushakov
 * Purpose: maages all messages created by game author
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
}
