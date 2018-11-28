package authoring.authoring_frontend.FormBoxes;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

import java.util.List;

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

    @Override
    public void setContent() {
        myKey = new TextField();
        myMessageKey = new ChoiceBox();
        myMessageKey.getItems().addAll(myMessageKeys);

        this.getChildren().addAll(myKey, myMessageKey);
    }

    @Override
    public JSONObject getContent() {
        JSONObject myObject = new JSONObject();
        myObject.put("key", myKey.getText());
        myObject.put("messageKey", myMessageKey.getValue());
        return myObject;
    }

    @Override
    public boolean invalidEntry() {
        return false;
    }
}
