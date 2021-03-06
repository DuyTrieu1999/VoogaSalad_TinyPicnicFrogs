package authoring.authoring_frontend.FormBoxes;

import authoring.authoring_backend.GameManager;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

/**
 * InteractionBox
 *
 * @author brookekeene
 */
public class InteractionBox extends FormBox {
    private SelectBox myType;
    private List<AnimationBox> myAnimations;
    private List<MessageBox> myMessages;
    private List<MoveBox> myMoves;
    private List<String> typeChoices;
    private List<String> messageChoices;
    private GameManager myManager;

    /**
     * Constructor
     */
    public InteractionBox(String label, GameManager manager) {
        super(label);
        myManager = manager;
        typeChoices = new ArrayList<>(List.of("Combat", "Dialogue", "Background"));;
        messageChoices = myManager.getMessageIds();
    }

    /**
     *
     */
    @Override
    public void setContent() {
        myMessages = new ArrayList<>();
        myAnimations = new ArrayList<>();
        myMoves = new ArrayList<>();
        VBox myContent = new VBox();

        // Type
        myType = new SelectBox(myResources.getString("type"));
        myType.setChoices(typeChoices );
        myContent.getChildren().addAll(myType);

        // Animations
        Label animations = new Label(myResources.getString("animations"));
        Button addAnimationBtn = new Button(myResources.getString("AddNew"));
        VBox animationsBox = new VBox();
        animationsBox.setPadding(new Insets(PADDING));
        animationsBox.getChildren().addAll(animations, addAnimationBtn);

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
        Label messages = new Label(myResources.getString("messages"));
        Button addMessageBtn = new Button(myResources.getString("AddNew"));
        VBox messagesBox = new VBox();
        messagesBox.setPadding(new Insets(PADDING));
        messagesBox.getChildren().addAll(messages, addMessageBtn);

        addMessageBtn.setOnAction(e -> {
            MessageBox temp = new MessageBox("");
            temp.setMessageChoices(messageChoices);
            temp.setContent();
            myMessages.add(temp);
            messagesBox.getChildren().add(temp);
        });

        // Moves
        Label moves = new Label(myResources.getString("moves"));
        Button addMoveBtn = new Button(myResources.getString("AddNew"));
        VBox movesBox = new VBox();
        movesBox.setPadding(new Insets(PADDING));
        movesBox.getChildren().addAll(moves, addMoveBtn);

        addMoveBtn.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle(myResources.getString("moves"));
            dialog.setHeaderText(myResources.getString("CreateNew") + myResources.getString("move"));
            dialog.setContentText(myResources.getString("EnterName") + myResources.getString("move"));
            Optional<String> result = dialog.showAndWait();
            if(result.isPresent()){
                MoveBox temp = new MoveBox(result.get());
                temp.setContent();
                myMoves.add(temp);
                movesBox.getChildren().add(temp);
            }
        });

        myContent.getChildren().addAll(animationsBox, messagesBox, movesBox);
        this.getChildren().add(myContent);
    }

    /**
     *
     * @return
     */
    @Override
    public JSONObject getJSONContent() {
        JSONObject myObject = new JSONObject();
        JSONArray myAnimationJSON = new JSONArray();
        JSONArray myMessageJSON = new JSONArray();
        JSONArray myMoveJSON = new JSONArray();

        // Animations
        for(AnimationBox box: myAnimations) {
            myAnimationJSON.add(box.getJSONContent());
        }

        // Messages
        for(MessageBox box:myMessages) {
            myMessageJSON.add(box.getJSONContent());
        }

        // Moves
        for(MoveBox box:myMoves) {
            myMoveJSON.add(box.getJSONContent());

        }

        myObject.put("name", myKey);
        myObject.put("type", myType.getChoice());
        myObject.put("animations", myAnimationJSON);
        myObject.put("messages", myMessageJSON);
        myObject.put("moves", myMoveJSON);
        return myObject;
    }

    /**
     * error checking for all fields of an interaction
     * @return true if user has input all necessary data
     */
    @Override
    public boolean hasValidEntry() { // TODO: finish
        if(!myType.hasValidEntry()) {
            return false;
        } else if (!validEntries()) {
            return false;
        }
        return true;
    }

    private boolean validEntries() {
        // Animations
        for(AnimationBox box: myAnimations) {
            if(!box.hasValidEntry()) {
                return false;
            }
        }

        // Messages
        for(MessageBox box:myMessages) {
            if(!box.hasValidEntry()) {
                return false;
            }
        }

        // Moves
        for(MoveBox box:myMoves) {
            if(!box.hasValidEntry()) {
                return false;
            }
        }
        return true;
    }
}
