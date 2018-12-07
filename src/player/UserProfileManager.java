package player;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.*;

import static player.SceneManager.DEFAULT_RESOURCE;

/**
 * @author Michael Glushakov
 * Purpose: Manages user's information
 * Dependencies: ServerManager
 * Usages: Used by userPaneManager and SceneManager
 */
public class UserProfileManager {
    private boolean playerLoggedIn;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userBio;
    private ServerManager myManager;
    private List<String> followers;
    private List<String>following;
    private List<String>gamesCreated;
    private List<String>gamesPlayed;
    private ResourceBundle myResources;

    /**
     * constructor
     */
    public UserProfileManager(){
        myResources= ResourceBundle.getBundle(DEFAULT_RESOURCE);
        myManager= new ServerManager();
        playerLoggedIn=false;
        followers= new ArrayList<>();
        following=new ArrayList<>();
        gamesCreated=new ArrayList<>();
        gamesPlayed=new ArrayList<>();
    }


    /**
     *
     * @param email
     * @param password
     * @throws ServerException caught and displayed in alert dialog
     */
   public void login(String email, String password) throws ServerException {
       try {
           JSONObject response = myManager.login(email,password);
           userEmail=(String)response.get("email");
           userPassword=password;
           userBio=(String)response.get("bio");
           userName=(String)response.get("name");
           parseArray((JSONArray) response.get("followers"),followers);
           parseArray((JSONArray)response.get("following"),following);
           parseArray((JSONArray) response.get("gamesCreated"),gamesCreated);
           parseArray((JSONArray)response.get("gamesPlayed"),gamesPlayed);
           playerLoggedIn = true;
           System.out.println(userBio);
       }catch (IOException e){throw new ServerException(e);}
       catch (InterruptedException e){throw new ServerException(e);} catch (ParseException e) {
       throw new ServerException(e);
       }
   }

    /**
     *
     * @param email
     * @param password
     * @param bio
     * @param name
     * @throws ServerException caught and displayed in alert dialog
     */
   public void register(String email,String password, String bio, String name) throws ServerException {
        try{
            myManager.register(email, password, bio, name);
            login(email,password);

        }catch (InterruptedException e){throw new ServerException(e);} catch (IOException e) {
            throw new ServerException(e);
        }
   }

    /**
     *
     * @return map of user's attributes
     */
   public Map<String,String> getUserAttributes(){
        Map<String,String>userData=new HashMap<>();
        userData.put(myResources.getString("email"),userEmail);
        userData.put(myResources.getString("name"),userName);
        userData.put(myResources.getString("bio"),userBio);
        return userData;
   }

    /**
     * Used during logout
     */
   public void clear(){
        userEmail=null;
        userBio=null;
        userPassword=null;
        playerLoggedIn=false;
   }

    /**
     * @return followers
     */
   public List<String>getFollowers(){return followers;}
    /**
     * @return following
     */
   public List<String>getFollowing(){return following;}
   public List<String>getGamesCreated(){return gamesCreated;}
   private List<String>getGamesPlayed(){return gamesPlayed;}

    /**
     *
     * @return if player is logged in
     */
   public boolean isPlayerLoggedIn(){return playerLoggedIn;}

    /**
     * Utility
     * @param arr JSONARRAY of data
     * @param list where that data is to be stored
     */
   public void parseArray(JSONArray arr,List<String>list){
        list.clear();
        if(arr==null||arr.size()==0){return;}
        for(int i=0;i<arr.size();i+=1){
            list.add((String)arr.get(i));
        }
    }

    /**
     *
     * @param name
     * @param bio
     * @throws ServerException
     */
    public void updateInfo(String name, String bio) throws ServerException {
        try {
            JSONObject data=myManager.updateUser(userEmail,userPassword,bio,name);
            userBio=(String)data.get("bio");
            userName=(String)data.get("name");

        } catch (Exception e) {
            throw new ServerException(e);
        }

    }

    /**
     *
     * @param name
     * @return
     * @throws ServerException
     */
    public List<JSONObject>lookUpUsers(String name) throws ServerException {
        List<JSONObject>users= new ArrayList<>();
        try {
            JSONArray arr = myManager.lookUpUsers(name);
            if(arr==null||arr.size()==0){return users;}
            for(int i=0;i<arr.size();i+=1){
                users.add((JSONObject)arr.get(i));
            }
        } catch (Exception e) {
            throw new ServerException(e);
        }
        return users;
    }

    /**
     *
     * @param targetEmail email of person user wants to follow
     * @throws ServerException
     */
    public void followUser(String targetEmail) throws ServerException {
        try {
            JSONArray arr= myManager.followUser(targetEmail,userEmail);
            parseArray(arr,following);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }
   }

