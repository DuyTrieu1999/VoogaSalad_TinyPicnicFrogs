package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**
 * @author janice
 */

public class MessageWindow {
    public static final String DEFAULT_RESOURCE = "English";
    public static int WIDTH = 500;
    public static int HEIGHT = 170;
    private ResourceBundle myResources;
    private Scene myWindow;
    private Group myRoot;
    private MessageForm myMessageForm;
    private GameManager myManager;

    public MessageWindow(GameManager manager) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
        myRoot = new Group();
        myWindow = new Scene(myRoot, WIDTH, HEIGHT);
        myManager = manager;

        this.display();
        this.addContent();
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
        myMessageForm = new MessageForm(myManager);
        mySP.setContent(myMessageForm);
        mySP.setMaxSize(WIDTH,HEIGHT);
        myRoot.getChildren().add(mySP);
    }
}
