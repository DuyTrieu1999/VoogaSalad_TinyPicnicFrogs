package authoring.authoring_frontend.FormBoxes;

import javafx.scene.control.ChoiceBox;
import org.json.simple.JSONObject;

import java.util.List;

/**
 * SelectBox
 *
 * allows user to choose between multiple input options
 *
 * @author brookekeene
 */
public class SelectBox extends FormBox {
    private ChoiceBox<String> myChoice;

    public SelectBox(String label){
        super(label);
    }

    /**
     * creates a ChoiceBox with the options specified by the
     * given List
     * @param choices List of Strings as options
     */
    public void setChoices(List choices) {
        myChoice = new ChoiceBox();
        myChoice.getItems().addAll(choices);
        this.getChildren().add(myChoice);
    }

    /**
     * @return String representing the option selected
     */
    public String getChoice() {
        return myChoice.getValue();
    }

    @Override
    public void setContent() {

    }

    @Override
    public JSONObject getContent() {
        return null;
    }

    @Override
    public boolean invalidEntry() {
        return false;
    }
}
