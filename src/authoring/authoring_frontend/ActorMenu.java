package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Menu for the actor that is displayed on the left side.
 *
 * @author Allen Qiu
 */
public class ActorMenu extends VBox {
    private GameManager myManager;
    private ScrollPane backgroundTilePane = new ScrollPane();
    private ScrollPane actorTilePane = new ScrollPane();
    private BorderPane selectedPane = null;
    private ActorManager actorManager;
    private String programName;

    /**
     * Constructor
     * @param manager GameManager for this game.
     * @param aManager ActorManager for this game.
     * @param name Name of the program.
     */
    public ActorMenu(GameManager manager, ActorManager aManager, String name) {
        programName = name;
        actorManager = aManager;
        myManager = manager;
        this.getChildren().add(new Label("menu"));
        setupTabs();
    }

    /**
     * Sets up the content of the two different tabs in the menu.
     */
    private void setupTabs(){
        backgroundTilePane.setContent(setupTab(actorManager.getBackgroundActors()));
        actorTilePane.setContent(setupTab(actorManager.getPlayableActors()));
    }

    /**
     * Sets up a tab and loads the actors and sets the on click actions.
     * @param actorList The list of actors to create a list from.
     * @return A FlowPane with the images of the actors loaded.
     */
    private FlowPane setupTab(ArrayList<Actor> actorList){
        backgroundTilePane.setPrefViewportWidth(200);
        backgroundTilePane.setPrefViewportHeight(400);
        backgroundTilePane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        ArrayList<BorderPane> tileImages = new ArrayList<>();
        for(Actor thisActor:actorList){
            BorderPane thisTileImage = new BorderPane(thisActor.getActorImage());
            thisTileImage.setOnMouseClicked(event -> {
                Actor currentActiveActor = ActiveItem.getActiveItem(programName);
                if(currentActiveActor == null){
                    //first tile clicked
                    ActiveItem.setActiveItem(programName, thisActor);
                    thisTileImage.setStyle("-fx-border-color: blue;");
                    selectedPane = thisTileImage;
                }
                else if(currentActiveActor.equals(thisActor)){
                    //deselect
                    selectedPane.setStyle(null);
                    selectedPane = null;
                    ActiveItem.removeActiveItem(programName);
                }
                else {
                    //replace old selection
                    selectedPane.setStyle(null);
                    ActiveItem.setActiveItem(programName, thisActor);
                    selectedPane = thisTileImage;
                    thisTileImage.setStyle("-fx-border-color: blue;");
                }
            });
            tileImages.add(thisTileImage);
        }
        FlowPane tileHolder = new FlowPane();
        tileHolder.getChildren().addAll(tileImages);
        tileHolder.setPrefWrapLength(200);
        return tileHolder;
    }

    /**
     * Gets a tab pane of the actors.
     * @return TabPane containing tabs of playable and non-playable actors.
     */
    public TabPane getActorMenu(){
        TabPane allTabs = new TabPane();
        Tab backgroundTab = new Tab();
        backgroundTab.setContent(backgroundTilePane);
        backgroundTab.setText("Backgrounds");
        Tab actorTab = new Tab();
        actorTab.setContent(actorTilePane);
        actorTab.setText("Actors");
        allTabs.getTabs().addAll(backgroundTab, actorTab);
        return allTabs;
    }
}
