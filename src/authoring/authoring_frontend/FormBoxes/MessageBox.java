package authoring.authoring_frontend.FormBoxes;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

import java.util.List;

/**
 * TextBox
 *
 * allows user to specify Messages for Interactions
 *
 * @author brookekeene
 */
public class MessageBox extends FormBox {
    private TextField myKey;
    private ChoiceBox myMessageKey;
    private List<String> myMessageKeys;

    public MessageBox(String label) {
        super(label);
    }

    public void setMessageChoices(List<String> keys) {
        myMessageKeys = keys;
    }

    /**
     * creates a TextField for user input message and
     * a ChoiceBox containing the defined message types
     */
    @Override
    public void setContent() {
        myKey = new TextField();
        myMessageKey = new ChoiceBox();
        myMessageKey.getItems().addAll(myMessageKeys);

        this.getChildren().addAll(myKey, myMessageKey);
    }

    /**
     * @return JSONObject storing the key, the user input,
     * and the messageKey, the type of message chosen
     */
    @Override
    public JSONObject getJSONContent() {
        JSONObject myObject = new JSONObject();
        myObject.put("key", myKey.getText());
        myObject.put("messageKey", myMessageKey.getValue());
        return myObject;
    }

    /**
     * error checking for a all fields of a key and messageKey pair
     * @return true if user has input a key and selected a message
     */
    @Override
    public boolean hasValidEntry() {
        return !(myKey.getText().isEmpty()) && (myMessageKey.getValue() != null);
    }
}
