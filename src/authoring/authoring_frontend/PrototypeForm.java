package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import authoring.authoring_frontend.Forms.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PrototypeForm extends VBox {
    public static final String DEFAULT_RESOURCE = "English";
    public static int SIZE = 500;
    public static int PADDING = 10;
    private ResourceBundle myResources;
    private List<FormBox> myAnimationForms;
    private List<FormBox> myStatisticsForms;
    private List<FormBox> myInteractionForms;
    private TextField prototypeName;

    private GameManager myManager;

    /**
     * Constructor
     */
    public PrototypeForm(GameManager manager) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
        myAnimationForms = new ArrayList<>();
        myStatisticsForms = new ArrayList<>();
        myInteractionForms = new ArrayList<>();
        myManager = manager;

        this.setMaxSize(SIZE, SIZE);
        this.setPadding(new Insets(PADDING));
        this.addAllFields();
    }

    /**
     * adds all necessary fields to form
     */
    private void addAllFields() { //TODO: refactor and make more flexible (HBox with AddNewBtn, ex. from interactions)
        Label name = new Label(myResources.getString("name"));
        Label animations = new Label(myResources.getString("animations"));
        Label stats = new Label(myResources.getString("stats"));
        Label interactions = new Label(myResources.getString("interactions"));
        name.setPadding(new Insets(PADDING));
        animations.setPadding(new Insets(PADDING));
        stats.setPadding(new Insets(PADDING));
        interactions.setPadding(new Insets(PADDING));

        Button saveBtn = new Button(myResources.getString("Save")); // Save Button
        saveBtn.setOnAction(e -> saveFunction());

        // Name
        prototypeName = new TextField();
        this.getChildren().addAll(name, prototypeName);

        // Animations
        AnimationBox idle = new AnimationBox("Idle");
        AnimationBox up = new AnimationBox("Up");
        AnimationBox down = new AnimationBox("Down");
        AnimationBox left = new AnimationBox("Left");
        AnimationBox right = new AnimationBox("Right");
        myAnimationForms.add(idle);
        myAnimationForms.add(up);
        myAnimationForms.add(down);
        myAnimationForms.add(left);
        myAnimationForms.add(right);

        this.getChildren().addAll(animations, idle, up, down, left, right);

        // Statistics
        TextBox health = new TextBox("Health");
        myStatisticsForms.add(health);

        this.getChildren().addAll(stats, health);

        // Interactions
        Button addIBtn = new Button(myResources.getString("AddNew"));
        HBox interactionsBox = new HBox();
        interactionsBox.setPadding(new Insets(PADDING));
        this.getChildren().addAll(interactions, interactionsBox, addIBtn);

        addIBtn.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Interaction");
            dialog.setHeaderText("Create New Interaction");
            dialog.setContentText("Enter the name of the new interaction: ");
            Optional<String> result = dialog.showAndWait();
            if(result.isPresent()){
                this.getChildren().remove(saveBtn);
                InteractionBox temp = new InteractionBox(result.get());
                myInteractionForms.add(temp);
                interactionsBox.getChildren().add(temp);
                this.getChildren().addAll(saveBtn);
            }
        });

        this.getChildren().add(saveBtn);
    }

    /**
     * saves all information as a JSON object
     */
    private void saveFunction() { //TODO: error check
        JSONObject myPrototype = new JSONObject();
        JSONArray myAnimations = new JSONArray();
        JSONArray myStats = new JSONArray();
        JSONArray myInteractions = new JSONArray();

        myPrototype.put("name", prototypeName.getText());

        for(int i = 0; i < myAnimationForms.size(); i++) {
            myAnimations.add(myAnimationForms.get(i).getContent());
        }
        myPrototype.put("animations", myAnimations);

        for(int i = 0; i < myStatisticsForms.size(); i++) {
            myStats.add(myStatisticsForms.get(i).getContent());
        }
        myPrototype.put("stats", myStats);

        for(int i = 0; i < myInteractionForms.size(); i++) {
            myInteractions.add(myInteractionForms.get(i).getContent());
        }
        myPrototype.put("interactions", myInteractions);

        System.out.println(myPrototype);
        //myManager.createActorPrototype(myPrototype); //TODO: uncomment to parse actual JSON

    }
}
