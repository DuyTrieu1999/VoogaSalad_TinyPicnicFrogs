package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class GameMap extends BorderPane {
    private GameManager myManager;

    public GameMap(GameManager manager) {
        myManager = manager;
        this.getChildren().add(new Label("map"));
    }
}
