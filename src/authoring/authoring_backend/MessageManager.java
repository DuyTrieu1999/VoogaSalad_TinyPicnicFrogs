package authoring.authoring_backend;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.backend.Actor;
import engine.backend.Message;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

}
