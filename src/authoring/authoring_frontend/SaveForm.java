package authoring.authoring_frontend;

import authoring.authoring_backend.GameData;
import authoring.authoring_backend.GameManager;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
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
        System.out.println("fired frontend");
        String title = gameName.getText();
        String description = gameDescript.getText();
        // TODO: do we need height and width of map?
        myManager.saveGame(title,description,gamePath);
    }
}
