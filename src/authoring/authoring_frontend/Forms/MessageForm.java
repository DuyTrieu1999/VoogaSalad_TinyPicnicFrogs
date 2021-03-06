package authoring.authoring_frontend.Forms;

import authoring.authoring_backend.GameManager;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

/**
 * author: @Janice Liu
 * This class handles information about a new Message the author would want to put in, to then save it
 */

public class MessageForm extends Form {
    private static int SIZE_ONE = 300;
    private static int SIZE_TWO = 800;
    private TextField myMessageKey;
    private TextField myMessageBody;

    /**
     * Constructor
     */
    public MessageForm(GameManager manager) {
        super(manager);

        this.setMaxSize(SIZE_ONE, SIZE_ONE);
        this.addAllFields();
    }

    @Override
    public void addAllFields() {
        //TODO: specify correct sizes for the TestFields. So the key will have a smaller text field than the body?
        //To add a static label in the window
        //Now we have a window that pops up with the label for a new message
        Label newMessageKey = new Label(myResources.getString("NewMessageKey"));
        newMessageKey.setPadding(new Insets(PADDING));
        myMessageKey = new TextField();
        myMessageKey.setPrefWidth(SIZE_ONE);
        this.getChildren().addAll(newMessageKey, myMessageKey);

        //Do the same, now for the message body
        Label newMessageBody = new Label(myResources.getString("NewMessageBody"));
        newMessageBody.setPadding(new Insets(PADDING));
        myMessageBody = new TextField();
        myMessageBody.setPrefWidth(SIZE_TWO);
        this.getChildren().addAll(newMessageBody, myMessageBody);

        //save button stuff
        Button saveBtn = new Button(myResources.getString("Save")); // Save Button
        saveBtn.setOnAction(e -> saveFunction());
        this.getChildren().add(saveBtn);
    }

    @Override
    public void saveFunction() { //TODO: error check
        JSONObject myMessage = new JSONObject();

        myMessage.put("messageKey", myMessageKey.getText());
        myMessage.put("messageBody", myMessageBody.getText());

        myManager.createMessage(myMessageKey.getText(), myMessageBody.getText());
    }
}
