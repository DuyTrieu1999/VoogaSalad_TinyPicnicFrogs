package authoring.authoring_frontend.Forms;

import authoring.authoring_backend.GameManager;
import authoring.authoring_backend.SaveException;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * SaveForm
 *
 * @author brookekeene
 */
public class SaveForm extends VBox {
    private static final String DEFAULT_RESOURCE = "English";
    private static final int SIZE = 300;
    private static final int FIELD_SIZE = 250;
    private static final int PADDING = 10;
    private ResourceBundle myResources;
    private GameManager myManager;
    private TextField gameName;
    private TextArea gameDescript;
    private String gamePath;

    /**
     * Constructor
     */
    public SaveForm(GameManager manager) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
        myManager = manager;
        gamePath = "";

        this.setMaxSize(SIZE, SIZE);
        this.setPadding(new Insets(PADDING));
        this.addAllFields();
    }

    /**
     * add UI elements for user to input game information
     */
    private void addAllFields() {
        // Labels
        Label name = new Label(myResources.getString("name"));
        Label description = new Label(myResources.getString("description"));
        Label path = new Label(myResources.getString("gamePath"));

        // Name
        gameName = new TextField();
        gameName.setMaxWidth(FIELD_SIZE);
        this.getChildren().addAll(name, gameName);

        // Description
        gameDescript = new TextArea();
        gameDescript.setWrapText(true);
        gameDescript.setPrefSize(FIELD_SIZE, FIELD_SIZE);
        this.getChildren().addAll(description, gameDescript);

        // Path
        Button addPathBtn = new Button(myResources.getString("addPath"));
        addPathBtn.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle(myResources.getString("choosePath"));
            File selectedDirectory = directoryChooser.showDialog(new Stage());

            if (selectedDirectory != null) {
                gamePath = selectedDirectory.getPath();
            }
        });

        this.getChildren().addAll(path, addPathBtn);

        // Save Button
        Button saveBtn = new Button(myResources.getString("Save"));
        saveBtn.setOnAction(e -> saveFunction());
        this.getChildren().add(saveBtn);
    }

    /**
     * creates a GameData object and calls the GameManager.saveGame method
     */
    private void saveFunction () { //TODO: error check
        String title = gameName.getText();
        String description = gameDescript.getText();

        List<String> arr= Arrays.asList(gamePath.split("/")); // Regex for non-Mac "\\\\"));
        int index=arr.indexOf("resources");
        String path=".";
        for(int i=index;i<arr.size();i+=1){path+="/"+arr.get(i);}

        try {
            myManager.saveGame(title,description,path);
        } catch (SaveException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(myResources.getString("error"));
            alert.getDialogPane().setContent(new VBox(new Text(e.getMessage())));
            alert.showAndWait();
        }
    }
}
