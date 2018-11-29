package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Class to manage the different actors in the frontend.
 *
 * @author Allen Qiu
 */
public class ActorManager {
    private ArrayList<Actor> backgroundActors;
    private ArrayList<Actor> playableActors;
    private GameManager gameManager;

    /**
     * Constructor for the actor manager.
     * @param gm The GameManager for this game.
     */
    ActorManager(GameManager gm){
        backgroundActors = new ArrayList<>();
        playableActors = new ArrayList<>();
        gameManager = gm;
        try {
            loadDefaultActors();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds an actor to the actor manager.
     * @param newActor The actor to add.
     * @param isPlayable Whether or not this actor is playable.
     */
    public void addActor(Actor newActor, boolean isPlayable){
        if(isPlayable){
            playableActors.add(newActor);
        }
        else {
            backgroundActors.add(newActor);
        }
    }

    /**
     * Gets a list of actors that are not playable.
     * @return List of non-playable actors.
     */
    public ArrayList<Actor> getBackgroundActors(){
        return backgroundActors;
    }

    /**
     * Gets a list of actors that are playable.
     * @return List of playable actors.
     */
    public ArrayList<Actor> getPlayableActors(){
        return playableActors;
    }

    /**
     * Loads the default 40 or so actors.
     * @throws IOException
     * @throws ParseException
     */
    public void loadDefaultActors() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(new File("./resources/default.json")));
        JSONArray defaultActors = (JSONArray)obj;
        for(int i=0;i<defaultActors.size();i++){
            JSONObject thisActor = (JSONObject)defaultActors.get(i);
            gameManager.createActorPrototype(thisActor);
            addActor(new Actor(thisActor), (boolean) thisActor.get("isPlayer"));
        }
    }

}
