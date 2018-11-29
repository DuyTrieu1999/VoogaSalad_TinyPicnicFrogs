package authoring.authoring_frontend;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Actor {
    private Image actorImage;
    private String actorPrototypeID;

    Actor(JSONObject actor){
        actorPrototypeID = (String)actor.get("name");
        JSONArray animations = (JSONArray)actor.get("animations");
        JSONObject defaultSprite = (JSONObject)animations.get(0);
        String imageURL = (String)defaultSprite.get("path");
        System.out.println(actorPrototypeID + ": " + imageURL);
        actorImage = new Image(imageURL);

    }

    //todo: remove later after testing after we make the json files
    Actor(Image aImage, String name){
        actorImage = aImage;
        actorPrototypeID = name;
    }

    public ImageView getActorImage(){
        return new ImageView(actorImage);
    }

    public String getActorPrototypeID(){
        return actorPrototypeID;
    }
}
