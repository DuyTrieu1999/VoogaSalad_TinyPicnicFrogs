package authoring.authoring_frontend;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
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
    private FlowPane myContent;

    public PrototypeWindow() {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
        myRoot = new Group();
        myWindow = new Scene(myRoot);
        myContent = new FlowPane();

        myRoot.getChildren().add(myContent);

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

    private void addContent() {
        Label IDLbl = new Label(myResources.getString("PrototypeID"));
        TextField prototypeID = new TextField();
        Button saveBtn = new Button(myResources.getString("Save"));

        myContent.getChildren().addAll(IDLbl, prototypeID, saveBtn);
    }

}
