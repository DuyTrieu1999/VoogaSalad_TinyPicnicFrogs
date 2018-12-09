package authoring.authoring_frontend.Forms;

import authoring.authoring_backend.GameManager;
import authoring.authoring_backend.ObservableActor;
import authoring.authoring_backend.ObservablePrototype;
import authoring.authoring_backend.SaveException;
import authoring.authoring_frontend.Actor;
import authoring.authoring_frontend.ActorManager;
import authoring.authoring_frontend.Grid;
import authoring.authoring_frontend.MapManager;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * SaveForm
 *
 * @author brookekeene
 */
public class LoadForm extends Form {
    private static final int SIZE = 300;
    private static final int FIELD_SIZE = 250;
    private TextField gameName;
    private TextArea gameDescript;
    private String gamePath;
    private ActorManager actorManager;
    private MapManager mapManager;

    /**
     * Constructor
     */
    public LoadForm(GameManager manager, ActorManager a, MapManager m) {
        super(manager);
        gamePath = "";
        actorManager = a;
        mapManager = m;

        this.setMaxSize(SIZE, SIZE);
        this.addAllFields();
    }

    /**
     * add UI elements for user to input game information
     */
    @Override
    public void addAllFields() {
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
        Button saveBtn = new Button(myResources.getString("Load"));
        saveBtn.setOnAction(e -> saveFunction());
        this.getChildren().add(saveBtn);
    }

    /**
     * creates a GameData object and calls the GameManager.saveGame method
     */
    @Override
    public void saveFunction () { //TODO: error check
        String title = gameName.getText();
        String description = gameDescript.getText();

        List<String> arr= Arrays.asList(gamePath.split("/")); // Regex for non-Mac "\\\\"));
        int index=arr.indexOf("resources");
        String path=".";
        for(int i=index;i<arr.size();i+=1){path+="/"+arr.get(i);}

        try {
            myManager.loadActors(gamePath + "actors.xml");
            myManager.loadMessages(gamePath + "messages.xml");
            myManager.loadPrototypes(gamePath + "prototypes.xml");
            parseActors(myManager.getObservableActors());
            parsePrototypes(myManager.getObservablePrototypes());
        } catch (SaveException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(myResources.getString("error"));
            alert.getDialogPane().setContent(new VBox(new Text(e.getMessage())));
            alert.showAndWait();
        }
    }

    private void parseActors(List<ObservableActor> actors){
        Grid currentGrid = mapManager.getMap(mapManager.getActiveMapName()).getGrid();
        for(ObservableActor thisActor:actors){
            currentGrid.addActorFrontendOnly(new Actor(thisActor.myId, thisActor.myView), thisActor.x, thisActor.y);
        }
    }

    private void parsePrototypes(List<ObservablePrototype> prototypes){
        for(ObservablePrototype thisPrototype:prototypes){
            actorManager.addActor(new Actor(thisPrototype.myId, thisPrototype.myView), !thisPrototype.isBackground);
        }
    }
}
