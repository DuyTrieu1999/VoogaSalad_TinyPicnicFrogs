package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**
 * Prototype Window
 *
 * Creates a popup window that allows the user to create a
 * new Actor Prototype
 *
 * @author brookekeene
 */
public class PrototypeWindow {
    public static final String DEFAULT_RESOURCE = "English";
    //    public static final String DEFAULT_STYLESHEET = "light.css";
    public static int SIZE = 200;
    private ResourceBundle myResources;
    private Scene myWindow;
    private Group myRoot;
    private PrototypeForm myContent;
    private GameManager myManager;

    /**
     * Constructor
     */
    public PrototypeWindow(GameManager manager) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
        myRoot = new Group();
        myWindow = new Scene(myRoot, SIZE, SIZE);
        myManager = manager;

        this.addContent();
        this.display();
    }

    /**
     * creates a new Stage for a popup window with the content specified
     * by the file represented by myContent
     */
    private void display() {
        Stage window = new Stage(); //TODO: create preference width and height

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(myResources.getString("NewPrototype"));

        window.setScene(myWindow);
        window.show();
    }

    /**
     * creates the elements needed to get the data for a prototype
     */
    private void addContent() {
        ScrollPane mySP = new ScrollPane();
        myContent = new PrototypeForm(myManager);
        mySP.setContent(myContent);
        mySP.setPrefSize(SIZE, SIZE);
        myRoot.getChildren().add(mySP);
    }
}

