package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.scene.image.Image;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ActorManager {
    private ArrayList<Actor> backgroundActors;
    private ArrayList<Actor> playableActors;
    private GameManager gameManager;

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

    public void addActor(Actor newActor, boolean isPlayable){
        if(isPlayable){
            playableActors.add(newActor);
        }
        else {
            backgroundActors.add(newActor);
        }
    }

    public ArrayList<Actor> getBackgroundActors(){
        return backgroundActors;
    }

    public ArrayList<Actor> getPlayableActors(){
        return playableActors;
    }

    public void loadDefaultActors() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("resources/default.json"));
        JSONArray defaultActors = (JSONArray)obj;
        for(int i=0;i<defaultActors.size();i++){
            JSONObject thisActor = (JSONObject)defaultActors.get(i);
            gameManager.createActorPrototype(thisActor);
            addActor(new Actor(thisActor), (boolean) thisActor.get("isPlayer"));
        }
    }

}
