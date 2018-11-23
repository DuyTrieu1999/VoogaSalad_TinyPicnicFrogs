package authoring.authoring_frontend;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;


public class Actor {
    private Image actorImage;

    Actor(Image image){
        actorImage = image;
    }

    public ImageView getActorImage(){
        return new ImageView(actorImage);
    }
}
