package authoring.authoring_frontend.FormBoxes;

import authoring.authoring_backend.GameManager;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * MessageListBox
 *
 * Creates a box that allows user to specify a flexible
 * number of SelectBox objects containing all user-defined
 * messages
 *
 * @author brookekeene
 */
public class MessageListBox extends FormBox {
    private List<String> messageChoices;
    private List<SelectBox> myMessages;
    private GameManager myManager;
    private boolean invalidChoice;

    /**
     * Constructor
     */
    public MessageListBox(String label, GameManager manager) {
        super(label);
        myManager = manager;
        messageChoices = myManager.getMessageIds();
        myMessages = new ArrayList<>();
        invalidChoice = false;
    }

    /**
     * creates a VBox with a button that allows you to add more
     * SelectBox objects to the VBox
     */
    @Override
    public void setContent() {
        Button addBtn = new Button(myResources.getString("AddNew"));
        VBox myContent = new VBox();

        addBtn.setOnAction(e -> {
            SelectBox temp = new SelectBox("message");
            temp.setChoices(messageChoices);
            myMessages.add(temp);
            myContent.getChildren().add(temp);
        });

        this.getChildren().addAll(addBtn, myContent);
    }

    /**
     * @return JSONArray containing all of the unique messages chosen
     */
    public JSONArray getJSONArray() {
        JSONArray myArray = new JSONArray();
        var messages = new ArrayList<String>();

        for(SelectBox box:myMessages) {
            if(box.hasValidEntry()) {
                myArray.add(box.getChoice());
                if(!messages.contains(box.getChoice())) {
                    messages.add(box.getChoice());
                }
            }
            else {
                invalidChoice = true;
            }
        }
        return myArray;
    }

    /**
     * error checking for a valid user selections in each field
     * @return true if the user has selected valid options
     */
    @Override
    public boolean hasValidEntry() {
        return !invalidChoice;
    }

    @Override
    public JSONObject getJSONContent() {
        return null;
    } // Unused in this class
}
