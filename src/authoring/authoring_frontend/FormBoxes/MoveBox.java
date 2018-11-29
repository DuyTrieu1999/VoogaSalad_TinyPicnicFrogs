package authoring.authoring_frontend.FormBoxes;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MoveBox extends FormBox {
    private TextBox targetStat;
    private TextBox actorNum;
    private SelectBox actorType;
    private TextBox targetVal;
    private SelectBox targetType;
    private List<AnimationBox> myAnimations;

    public MoveBox(String label) {
        super(label);
        myAnimations = new ArrayList<>();
    }

    @Override
    public void setContent() {
        VBox myContent = new VBox();

        // Target Statistic
        targetStat = new TextBox("Target Statistic");
        targetStat.setContent();

        // Target Actor Number
        actorNum = new TextBox("Target Actor Number");
        actorNum.setContent();

        // Target Actor Type
        actorType = new SelectBox("Target Actor Type");
        actorType.setChoices(new ArrayList(List.of("friend", "enemy")));
        actorType.setContent();

        // Target Value
        targetVal = new TextBox("Target Value");
        targetVal.setContent();

        // Target Type
        targetType = new SelectBox("Target Type");
        targetType.setChoices(new ArrayList(List.of("constant", "percent")));
        targetType.setContent();

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

        myContent.getChildren().addAll(targetStat, actorNum, actorType, targetVal, targetType, animations, animationsBox);
        this.getChildren().add(myContent);
    }

    @Override
    public JSONObject getContent() {
        JSONObject myObject = new JSONObject();
        JSONArray moveAnimations = new JSONArray();

        for(AnimationBox box:myAnimations) {
            moveAnimations.add(box.getContent());
        }

        myObject.put("name", myKey);
        myObject.put("targetStat", targetStat.getContent().get("value"));
        myObject.put("targetActorNumber", actorNum.getContent().get("value"));
        myObject.put("targetActorType", actorType.getChoice());
        myObject.put("targetValue", targetVal.getContent().get("value"));
        myObject.put("targetType", targetType.getChoice());
        myObject.put("animations", moveAnimations);

        return myObject;
    }

    @Override
    public boolean invalidEntry() {
        return false;
    }
}
