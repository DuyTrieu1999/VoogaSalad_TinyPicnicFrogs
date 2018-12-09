package authoring.authoring_frontend;

import javafx.scene.control.Tooltip;
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
    private ImageView actorImage;
    private String actorPrototypeID;

    /**
     * Constructor for the actor.
     * @param actor JSON object of the actor.
     */
    public Actor(JSONObject actor){
        actorPrototypeID = (String)actor.get("name");
        JSONArray animations = (JSONArray)actor.get("animations");
        JSONObject defaultSprite = (JSONObject)animations.get(0);
        String imageURL = (String)defaultSprite.get("path");
        System.out.println(actorPrototypeID + ": " + imageURL);
        actorImage = new ImageView(new Image(imageURL));

    }

    public Actor(String id, ImageView image){
        actorPrototypeID = id;
        actorImage = image;
    }

    /**
     * Gets an image representation of the actor.
     * @return
     */
    public ImageView getActorImage(){
        return actorImage;
    }

    /**
     * Gets the ID.
     * @return The ID of the actor prototype.
     */
    public String getActorPrototypeID(){
        return actorPrototypeID;
    }

}
