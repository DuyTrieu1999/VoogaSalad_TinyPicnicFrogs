package authoring.authoring_frontend.FormBoxes;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
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
    private String fileName;
    private int myRows;
    private int myCols;
    private boolean btnClicked;

    public AnimationBox(String label) {
        super(label);
        fileName = null;
        btnClicked = false;
    }

    /**
     * creates FileChooser and Button for user to select an image
     */
    @Override
    public void setContent() {
        FileChooser myFC = new FileChooser();
        FileChooser.ExtensionFilter filter =new FileChooser.ExtensionFilter("Image Files","*.bmp", "*.gif", "*.jpeg", "*.png");

        myFC.setTitle(myResources.getString("NewFile"));
        myFC.getExtensionFilters().add(filter);

        ImageView fileIm = new ImageView();

        Button fileBtn = new Button(myResources.getString("NewFile"));
        fileBtn.setOnAction(e -> {
            File file = myFC.showOpenDialog(getScene().getWindow());
            if(file != null) {
                fileName = file.toString();
                String[]arr=fileName.split("/"); // Regex for non-Mac "\\\\"));
                fileName=arr[arr.length-1];
                System.out.println(fileName);
                fileIm.setImage(new Image(file.toURI().toString()));

            }
        });

        Button spritButn= new Button(myResources.getString("setBtn"));
        spritButn.setOnAction(event -> {
            btnClicked = true;
            launchDialog();
        });

        this.getChildren().addAll(fileBtn, fileIm,spritButn);
    }

    /**
     * @return JSONObject storing the key and value of the user input
     */
    @Override
    public JSONObject getContent() {
        JSONObject myObject = new JSONObject();
        myObject.put("path", fileName);
        myObject.put("key", myKey);
        myObject.put("spriteRows",myRows);
        myObject.put("spriteCols",myCols);
        return myObject;
    }

    /**
     * error checking for a valid file format and sprite dimensions set
     * @return true if user has selected a file and set dimensions
     */
    @Override
    public boolean hasValidEntry() {
        return fileName != null && btnClicked;
    }

    private void launchDialog(){
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        TextField rowText=new TextField(myResources.getString("spriteRows"));
        TextField colText=new TextField(myResources.getString("spriteCols"));
        Button setBtn= new Button(myResources.getString("setBtn"));
        setBtn.setOnAction(event -> {
            try{
                myRows=Integer.parseInt(rowText.getText());
                myCols=Integer.parseInt(colText.getText());}
            catch (NumberFormatException e){
                Alert alert1= new Alert(Alert.AlertType.ERROR);
                alert1.setHeaderText(myResources.getString("numErrorHeader"));
                alert1.setContentText(myResources.getString("numErrorBody"));
                alert1.showAndWait();
            }
        });
        VBox vBox= new VBox();
        vBox.getChildren().addAll(rowText,colText,setBtn);
        alert.getDialogPane().setContent(vBox);
        alert.showAndWait();

    }
}
