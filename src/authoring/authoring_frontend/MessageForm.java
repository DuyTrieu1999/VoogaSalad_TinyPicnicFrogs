package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import authoring.authoring_frontend.Forms.FormBox;
import authoring.authoring_frontend.Forms.InteractionBox;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MessageForm extends VBox {
    public static final String DEFAULT_RESOURCE = "English";
    public static int SIZE = 300;
    public static int PADDING = 10;
    private ResourceBundle myResources;
    private TextField myMessageKey;
    private TextField myMessageBody;
    private GameManager myManager;

    /**
     * Constructor
     */
    public MessageForm(GameManager manager) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
        myManager = manager;

        this.setMaxSize(SIZE, SIZE);
        this.setPadding(new Insets(PADDING));
        this.addAllFields();
    }

    public void addAllFields() {
        //TODO: specify correct sizes for the TestFields. So the key will have a smaller text field than the body?
        //To add a static label in the window
        //Now we have a window that pops up with the label for a new message
        Label newMessageKey = new Label(myResources.getString("NewMessageKey"));
        newMessageKey.setPadding(new Insets(PADDING));
        myMessageKey = new TextField();
        myMessageKey.setPrefWidth(300);
        this.getChildren().addAll(newMessageKey, myMessageKey);

        //Do the same, now for the message body
        Label newMessageBody = new Label(myResources.getString("NewMessageBody"));
        newMessageBody.setPadding(new Insets(PADDING));
        myMessageBody = new TextField();
        myMessageBody.setPrefWidth(800);
        this.getChildren().addAll(newMessageBody, myMessageBody);

        //save button stuff
        Button saveBtn = new Button(myResources.getString("Save")); // Save Button
        saveBtn.setOnAction(e -> saveFunction());

    }
    private void saveFunction() { //TODO: error check
        JSONObject myMessage = new JSONObject();

        myMessage.put("messageKey", myMessageKey.getText());
        myMessage.put("messageBody", myMessageBody.getText());

        myManager.createMessage(myMessageKey.getText(), myMessageBody.getText());

    }
}
