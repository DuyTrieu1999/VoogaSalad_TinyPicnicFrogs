package authoring.authoring_frontend.Forms;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InteractionBox extends FormBox {
    private TextField myType;
    private Map<String, String> myAnimations;
    private Map<String, TextField> myMessages;
    private List<MoveBox> myMoves;

    public InteractionBox(String label) {
        super(label);
    }

    @Override
    public void setContent() {
        myMessages = new HashMap<>();
        myAnimations = new HashMap<>();
        VBox myContent = new VBox();

        // Type
        Label type = new Label(myResources.getString("type"));
        myType = new TextField();
        myContent.getChildren().addAll(type, myType);

        // Animations
        FileChooser myFC = new FileChooser();
        myFC.setTitle(myResources.getString("NewFile"));
        ImageView fileIm = new ImageView();

        Button fileBtn = new Button(myResources.getString("NewFile"));

        fileBtn.setOnAction(e -> {
            File file = myFC.showOpenDialog(getScene().getWindow());
            //TODO: error check
            if(file.toString().contains(".png") || file.toString().contains(".jpeg")) {
                String fileName = file.toString();
                myAnimations.put("default", fileName);
                fileIm.setImage(new Image(file.toURI().toString()));

            }
        });

        myContent.getChildren().addAll(fileBtn, fileIm);

        // Messages
        // author defines key, selects from a list of possible messages
        // user defines key and body outside of prototype and then the body is added to a list of possible messages
        // get method from GameManager to get all messages
        Label victoryM = new Label(myResources.getString("victory"));
        TextField prototypeVictory = new TextField();
        myMessages.put("victory", prototypeVictory);
        Label defeatM = new Label(myResources.getString("defeat"));
        TextField prototypeDefeat = new TextField();
        myMessages.put("defeat", prototypeDefeat);
        myContent.getChildren().addAll(victoryM, prototypeVictory, defeatM, prototypeDefeat);

        // Moves
        //TODO: Complete this


        this.getChildren().add(myContent);
    }

    @Override
    public JSONObject getContent() {
        JSONObject myObject = new JSONObject();
        JSONArray myAnimationJSON = new JSONArray();
        JSONArray myMessageJSON = new JSONArray();
        JSONArray myMoveJSON = new JSONArray();

        // Animations
        JSONObject animation1JSON = new JSONObject();
        animation1JSON.put("key", "default");
        animation1JSON.put("path", myAnimations.get("default"));

        // Messages
        // TODO: createMessage(String key, String messageBody) will save message to messageMap in GameManager
        JSONObject message1JSON = new JSONObject();
        message1JSON.put("key", "prototypeVictory");
        message1JSON.put("messageKey", myMessages.get("victory").getText());

        JSONObject message2JSON = new JSONObject();
        message2JSON.put("key", "prototypeDefeat");
        message2JSON.put("messageKey", myMessages.get("defeat").getText());

        myMessageJSON.add(message1JSON);
        myMessageJSON.add(message2JSON);

        // Moves
        for(int i = 0; i < myMoves.size(); i++) {
            myMoves.get(i);
        }
        //TODO: complete this

        myObject.put("name", myKey);
        myObject.put("type", myType.getText());
        myObject.put("animations", myAnimationJSON);
        myObject.put("messages", myMessageJSON);
        myObject.put("moves", myMoveJSON);
        return myObject;
    }


    @Override
    public boolean invalidEntry() {
        return false;
    }
}
