package authoring.authoring_frontend;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Actor class to represent any item that has any picture or interaction.
 *
 * @author Allen Qiu
 */
public class Actor {
    private Image actorImage;
    private String actorPrototypeID;

    /**
     * Constructor for the actor.
     * @param actor JSON object of the actor.
     */
    Actor(JSONObject actor){
        actorPrototypeID = (String)actor.get("name");
        JSONArray animations = (JSONArray)actor.get("animations");
        JSONObject defaultSprite = (JSONObject)animations.get(0);
        String imageURL = (String)defaultSprite.get("path");
        System.out.println(actorPrototypeID + ": " + imageURL);
        actorImage = new Image(imageURL);

    }

    /**
     * Gets an image representation of the actor.
     * @return
     */
    public ImageView getActorImage(){
        return new ImageView(actorImage);
    }

    /**
     * Gets the ID.
     * @return The ID of the actor prototype.
     */
    public String getActorPrototypeID(){
        return actorPrototypeID;
    }
}
