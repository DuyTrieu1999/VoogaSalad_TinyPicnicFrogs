package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ActorMenu extends VBox {
    private GameManager myManager;

    public ActorMenu(GameManager manager) {
        myManager = manager;
        this.getChildren().add(new Label("menu"));
    }
}
