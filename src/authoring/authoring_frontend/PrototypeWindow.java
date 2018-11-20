package authoring.authoring_frontend;

import authoring.authoring_frontend.Forms.PrototypeForm;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
    private ResourceBundle myResources;
    private Scene myWindow;
    private Group myRoot;

    /**
     * Constructor
     */
    public PrototypeWindow() {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
        myRoot = new Group();
        myWindow = new Scene(myRoot);

        this.addContent();
        this.display();
    }

    /**
     * creates a new Stage for a popup window with the content specified
     * by the file represented by myContent
     */
    private void display() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(myResources.getString("NewPrototype"));

        window.setScene(myWindow);
        window.show();
    }

    /**
     * creates the elements needed to get the data for a prototype
     */
    private void addContent() {
        ScrollPane scroller = new ScrollPane();
        PrototypeForm myContent = new PrototypeForm();
        scroller.setContent(myContent);
        myRoot.getChildren().add(scroller);
    }

    /**
     * checks that all information necessary has been filled out
     * @return boolean, True if errors exist and False otherwise
     */
    private boolean checkErrors() {
        return false;
    }

    /**
     * saves all information as a JSON Object to be passed to backend
     */
    private void saveJSON(TextField id) {
        System.out.println(id.getText());
        System.out.println("saving...");
    }

    /**
     * returns Prototype saved as JSON Object
     */
    public void getPrototype() {

    }
}
