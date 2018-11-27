package authoring.authoring_frontend.Forms;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.json.simple.JSONObject;

import java.io.File;

/**
 * AnimationBox
 *
 * allows user to choose a file for animation
 *
 * @author brookekeene
 */
public class AnimationBox extends FormBox {
    private String fileName; //TODO: check if this should be stored as a Path object

    public AnimationBox(String label) {
        super(label);
        fileName = "";
    }

    /**
     * creates FileChooser and Button for user to select an image
     */
    @Override
    public void setContent() {
        FileChooser myFC = new FileChooser();
        myFC.setTitle(myResources.getString("NewFile"));
        ImageView fileIm = new ImageView();

        Button fileBtn = new Button(myResources.getString("NewFile"));

        fileBtn.setOnAction(e -> {
            File file = myFC.showOpenDialog(getScene().getWindow());
            //TODO: error check
            if(file.toString().contains(".png") || file.toString().contains(".jpeg")) {
                fileName = file.toString();
                fileIm.setImage(new Image(file.toURI().toString()));

            }
        });

        this.getChildren().addAll(fileBtn, fileIm);
    }

    /**
     * @return JSONObject storing the key and value of the user input
     */
    @Override
    public JSONObject getContent() {
        JSONObject myObject = new JSONObject();
        myObject.put("path", fileName);
        myObject.put("key", myKey);
        return myObject;
    }

    /**
     * error checking
     * @return
     */
    @Override
    public boolean invalidEntry() {
        return false;
    }
}