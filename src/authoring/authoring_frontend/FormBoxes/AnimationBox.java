package authoring.authoring_frontend.FormBoxes;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * AnimationBox
 *
 * allows user to choose a file for animation
 *
 * @author brookekeene
 */
public class AnimationBox extends FormBox {
    private String fileName;

    public AnimationBox(String label) {
        super(label);
        fileName = null;
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
            if(file != null) {
                fileName = file.toString();
                String[]arr=fileName.split("\\\\");
                fileName=arr[arr.length-1];
                //System.out.println(fileName);
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
