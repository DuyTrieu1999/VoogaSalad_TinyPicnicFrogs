package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class MessageWindow {
    public static final String DEFAULT_RESOURCE = "English";
    public static int SIZE = 500;
    private ResourceBundle myResources;
    private Scene myWindow;
    private Group myRoot;
    private PrototypeForm myContent;
    private GameManager myManager;

    public MessageWindow(GameManager manager) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
        myRoot = new Group();
        myWindow = new Scene(myRoot, SIZE, SIZE);
        myManager = manager;

        this.display();
    }

    private void display() {
        Stage window = new Stage(); //TODO: create preference width and height

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(myResources.getString("messages"));

        window.setScene(myWindow);
        window.show();
    }

    private void addContent() {
        ScrollPane mySP = new ScrollPane();
        myContent = new PrototypeForm(myManager);
        mySP.setContent(myContent);
        mySP.setMaxSize(SIZE,SIZE);
        myRoot.getChildren().add(mySP);
    }
}
