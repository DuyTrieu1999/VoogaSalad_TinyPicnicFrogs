package authoring.authoring_frontend.Forms;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ResourceBundle;

public class PrototypeForm extends VBox {
    public static final String DEFAULT_RESOURCE = "English";
    private ResourceBundle myResources;

    /**
     * Constructor
     */
    public PrototypeForm() {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
        this.addAllFields();
    }

    /**
     * adds all necessary fields to form
     */
    private void addAllFields() {
        Label name = new Label(myResources.getString("name"));
        TextBox namebox = new TextBox(myResources.getString("name"));
        Label animations = new Label(myResources.getString("animations"));
        Label stats = new Label(myResources.getString("stats"));
        Label interactions = new Label(myResources.getString("interactions"));
        Button saveBtn = new Button();
        saveBtn.setOnAction(e -> saveFunction());

        this.getChildren().addAll(name, namebox, animations, stats, interactions);
    }

    private void saveFunction() {
        
    }

    /**
     * creates HBox to hold a name
     * @return
     */
    private HBox nameBox() {
        HBox box = new HBox();
        Label nameLbl = new Label(myResources.getString("name"));
        TextField nameFld = new TextField();
        box.getChildren().addAll(nameLbl, nameFld);
        return box;
    }

//    private VBox animationBox() {
//        VBox box = new VBox();
//        Label animateLbl = new Label(myResources.getString("animations"));
//    }

    public void getFormInfo() {

    }


}
