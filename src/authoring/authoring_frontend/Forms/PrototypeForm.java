package authoring.authoring_frontend.Forms;

import authoring.authoring_backend.GameManager;
import authoring.authoring_frontend.Actor;
import authoring.authoring_frontend.ActorManager;
import authoring.authoring_frontend.FormBoxes.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * PrototypeForm
 *
 * Creates a VBox that contains all of the FormBox
 * elements and that saves a JSON Object representing
 * the ActorPrototype created
 *
 * @author brookekeene
 */
public class PrototypeForm extends VBox {
    private static final String DEFAULT_RESOURCE = "English";
    private static int SIZE = 500;
    private static int PADDING = 10;
    private static int FIELD_SIZE = 150;
    private ResourceBundle myResources;
    private List<FormBox> myAnimationForms;
    private List<FormBox> myStatisticsForms;
    private List<FormBox> myInteractionForms;
    private TextField prototypeName;
    private CheckBox isPlayer;
    private BoundsBox myBounds;

    private GameManager myManager;
    private ActorManager actorManager;

    /**
     * Constructor
     */
    public PrototypeForm(GameManager manager, ActorManager a) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
        myAnimationForms = new ArrayList<>();
        myStatisticsForms = new ArrayList<>();
        myInteractionForms = new ArrayList<>();
        myManager = manager;
        actorManager = a;

        //this.setMaxSize(SIZE, SIZE);
        this.setPadding(new Insets(PADDING));
        this.addAllFields();
    }

    /**
     * adds all necessary fields to form
     */
    private void addAllFields() {
        Label name = new Label(myResources.getString("name"));
        Label animations = new Label(myResources.getString("animations"));
        Label stats = new Label(myResources.getString("stats"));
        Label interactions = new Label(myResources.getString("interactions"));
        name.setPadding(new Insets(PADDING));
        animations.setPadding(new Insets(PADDING));
        stats.setPadding(new Insets(PADDING));
        interactions.setPadding(new Insets(PADDING));

        HBox saveBox = new HBox();
        saveBox.setPrefWidth(SIZE);
        saveBox.setPadding(new Insets(PADDING));
        saveBox.setAlignment(Pos.CENTER);
        Button saveBtn = new Button(myResources.getString("Save")); // Save Button
        saveBox.getChildren().add(saveBtn);
        saveBtn.setOnAction(e -> saveFunction());

        // Name
        prototypeName = new TextField();
        prototypeName.setMaxWidth(FIELD_SIZE);
        this.getChildren().addAll(name, prototypeName);

        // isPlayer
        HBox boolBox = new HBox();
        isPlayer = new CheckBox();
        isPlayer.setText(myResources.getString("isPlayer"));
        boolBox.getChildren().add(isPlayer);
        boolBox.setPadding(new Insets(PADDING));
        this.getChildren().add(boolBox);

        // Animations
        AnimationBox idle = new AnimationBox("Idle");
        AnimationBox up = new AnimationBox("Top");
        AnimationBox down = new AnimationBox("Bottom");
        AnimationBox left = new AnimationBox("Left");
        AnimationBox right = new AnimationBox("Right");
        myAnimationForms.add(idle);
        myAnimationForms.add(up);
        myAnimationForms.add(down);
        myAnimationForms.add(left);
        myAnimationForms.add(right);
        for(FormBox box:myAnimationForms) {
            box.setContent();
        }

        this.getChildren().addAll(animations, idle, up, down, left, right);

        // Bounds
        myBounds = new BoundsBox(myResources.getString("bounds"));
        myBounds.setContent();
        this.getChildren().addAll(myBounds);

        // Statistics
        Button addSBtn = new Button(myResources.getString("AddNew"));
        VBox statisticsBox = new VBox();
        statisticsBox.setPadding(new Insets(PADDING));
        this.getChildren().addAll(stats, statisticsBox, addSBtn);

        addSBtn.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle(myResources.getString("stats"));
            dialog.setHeaderText(myResources.getString("CreateNew") + myResources.getString("stat"));
            dialog.setContentText(myResources.getString("EnterName") + myResources.getString("stat"));
            Optional<String> result = dialog.showAndWait();
            if(result.isPresent()){
                TextBox temp = new TextBox(result.get());
                temp.setContent();
                myStatisticsForms.add(temp);
                statisticsBox.getChildren().add(temp);
            }
        });

        // Interactions
        Button addIBtn = new Button(myResources.getString("AddNew"));
        VBox interactionsBox = new VBox();
        interactionsBox.setPadding(new Insets(PADDING));
        this.getChildren().addAll(interactions, interactionsBox, addIBtn);

        addIBtn.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle(myResources.getString("interactions"));
            dialog.setHeaderText(myResources.getString("CreateNew") + myResources.getString("interaction"));
            dialog.setContentText(myResources.getString("EnterName") + myResources.getString("interaction"));
            Optional<String> result = dialog.showAndWait();
            if(result.isPresent()){
                InteractionBox temp = new InteractionBox(result.get(), myManager);
                temp.setContent();
                myInteractionForms.add(temp);
                interactionsBox.getChildren().add(temp);
            }
        });

        this.getChildren().add(saveBox);
    }

    /**
     * saves all information as a JSON object
     */
    private void saveFunction() { //TODO: make error checking more efficient
        JSONObject myPrototype = new JSONObject();
        JSONArray myAnimations = new JSONArray();
        JSONArray myStats = new JSONArray();
        JSONArray myInteractions = new JSONArray();

        if(prototypeName.getText().isEmpty()) {
            invalidDataAlert("name");
            return;
        }
        myPrototype.put("name", prototypeName.getText());

        for(int i = 0; i < myAnimationForms.size(); i++) {
            if(!myAnimationForms.get(i).hasValidEntry()) {
                invalidDataAlert("animations");
                return;
            }
            myAnimations.add(myAnimationForms.get(i).getContent());
        }
        myPrototype.put("animations", myAnimations);

        if(!myBounds.hasValidEntry()) {
            invalidDataAlert("bounds");
            return;
        }
        myPrototype.put("bounds", myBounds.getContent());

        for(int i = 0; i < myStatisticsForms.size(); i++) {
            if(!myStatisticsForms.get(i).hasValidEntry()) {
                invalidDataAlert("statistics");
                return;
            }
            myStats.add(myStatisticsForms.get(i).getContent());
        }
        myPrototype.put("stats", myStats);

        myPrototype.put("isPlayer", isPlayer.isSelected());

        for(int i = 0; i < myInteractionForms.size(); i++) {
            if(!myInteractionForms.get(i).hasValidEntry()) {
                invalidDataAlert("interactions");
                return;
            }
            myInteractions.add(myInteractionForms.get(i).getContent());
        }
        myPrototype.put("interactions", myInteractions);

        System.out.println(myPrototype); // TESTING
        myManager.createActorPrototype(myPrototype);
        actorManager.addActor(new Actor(myPrototype), (boolean)myPrototype.get("isPlayer"));
        actorManager.setupTabs();
    }

    /**
     * creates an AlertBox with the appropriate message when the user inputs data incorrectly
     */
    private void invalidDataAlert(String issue) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText(myResources.getString("error"));
        errorAlert.setContentText(myResources.getString("errorMessage") + issue);
        errorAlert.showAndWait();
    }
}
