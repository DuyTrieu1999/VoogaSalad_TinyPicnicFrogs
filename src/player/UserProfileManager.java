package player;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.json.simple.JSONObject;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserProfileManager {
    private boolean playerLoggedIn;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userBio;
    private ServerManager myManager;

    public UserProfileManager(){
        myManager= new ServerManager();
        playerLoggedIn=false;
    }



   public void login(String email, String password){
       try {
           JSONObject response = myManager.login(email,password);
           userEmail=(String)response.get("email");
           userPassword=password;
           userBio=(String)response.get("bio");
           userName=(String)response.get("name");
           playerLoggedIn = true;
           System.out.println(userBio);
       }catch (IOException e){e.printStackTrace();}
       catch (InterruptedException e){e.printStackTrace();}
   }

   public void register(String email,String password, String bio, String name){
        try{
            myManager.register(email, password, bio, name);
            login(email,password);

        }catch (InterruptedException e){e.printStackTrace();} catch (IOException e) {
            e.printStackTrace();
        }
   }
   public Map<String,String> getUserAttributes(){
        Map<String,String>userData=new HashMap<>();
        userData.put("email",userEmail);
        userData.put("name",userName);
        userData.put("bio",userBio);
        return userData;
   }

   public boolean isPlayerLoggedIn(){return playerLoggedIn;}

   }
