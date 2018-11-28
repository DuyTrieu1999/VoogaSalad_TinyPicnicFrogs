package authoring.authoring_frontend.FormBoxes;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.*;

public class InteractionBox extends FormBox {
    private SelectBox myType;
    private List<AnimationBox> myAnimations;
    private List<MessageBox> myMessages;
    private List<MoveBox> myMoves;

    public InteractionBox(String label) {
        super(label);
    }

    @Override
    public void setContent() {
        myMessages = new ArrayList<>();
        myAnimations = new ArrayList<>();
        myMoves = new ArrayList<>();
        VBox myContent = new VBox();

        // Type
        ArrayList<String> types = new ArrayList<>(List.of("fight"));
        myType = new SelectBox(myResources.getString("type"));
        myType.setChoices(types);
        myContent.getChildren().addAll(myType);

        // Animations
        Label animations = new Label(myResources.getString("animations"));
        Button addAnimationBtn = new Button(myResources.getString("AddNew"));
        VBox animationsBox = new VBox();
        animationsBox.setPadding(new Insets(PADDING));
        animationsBox.getChildren().addAll(animations, addAnimationBtn);
        this.getChildren().add(animationsBox);

        addAnimationBtn.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle(myResources.getString("animations"));
            dialog.setHeaderText(myResources.getString("CreateNew") + myResources.getString("animation"));
            dialog.setContentText(myResources.getString("EnterName") + myResources.getString("animation"));
            Optional<String> result = dialog.showAndWait();
            if(result.isPresent()){
                AnimationBox temp = new AnimationBox(result.get());
                temp.setContent();
                myAnimations.add(temp);
                animationsBox.getChildren().add(temp);
            }
        });

        // Messages
        // author defines key, selects from a list of possible messages
        // user defines key and body outside of prototype and then the body is added to a list of possible messages
        // get method from GameManager to get all messages
        Label messages = new Label(myResources.getString("messages"));
        Button addMessageBtn = new Button(myResources.getString("AddNew"));
        VBox messagesBox = new VBox();
        messagesBox.setPadding(new Insets(PADDING));
        messagesBox.getChildren().addAll(messages, addMessageBtn);
        this.getChildren().add(messagesBox);

        addMessageBtn.setOnAction(e -> {
            MessageBox temp = new MessageBox("");
            temp.setMessageChoices(new ArrayList<>(List.of("onVictory", "onDefeat")));
            temp.setContent();
            myMessages.add(temp);
            messagesBox.getChildren().add(temp);
        });

        // Moves
        //TODO: Complete this

        myContent.getChildren().addAll(animationsBox, messagesBox);
        this.getChildren().add(myContent);
    }

    @Override
    public JSONObject getContent() {
        JSONObject myObject = new JSONObject();
        JSONArray myAnimationJSON = new JSONArray();
        JSONArray myMessageJSON = new JSONArray();
        JSONArray myMoveJSON = new JSONArray();

        // Animations
        for(AnimationBox box: myAnimations) {
            myAnimationJSON.add(box.getContent());
        }

        // Messages
        // TODO: createMessage(String key, String messageBody) will save message to messageMap in GameManager

        for(int i = 0; i < myMessages.size(); i++) {
            myMessageJSON.add(myMessages.get(i).getContent());
        }

        // Moves
        //TODO: complete this
        for(int i = 0; i < myMoves.size(); i++) {
            myMoveJSON.add(myMoves.get(i).getContent());
        }

        myObject.put("name", myKey);
        myObject.put("type", myType.getChoice());
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
