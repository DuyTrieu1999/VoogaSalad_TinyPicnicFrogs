package authoring.authoring_frontend.Forms;

import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

/**
 * TextBox
 *
 * allows user to input text
 *
 * @author brookekeene
 */
public class TextBox extends FormBox {
    private TextField myText;

    public TextBox(String label) {
        super(label);
    }

    /**
     * creates TextField for user to enter text into
     */
    @Override
    public void setContent() {
        myText = new TextField();
        this.getChildren().add(myText);
    }

    /**
     * @return JSONObject storing the key and value of the user input
     */
    @Override
    public JSONObject getContent() {
        JSONObject myObject = new JSONObject();
        myObject.put("value", myText.getText());
        myObject.put("key", myKey);
        return myObject;
    }

    /**
     * error checking for valid text entry
     * @return
     */
    @Override
    public boolean invalidEntry() {
        return myText.getText().isEmpty();
    }
}
