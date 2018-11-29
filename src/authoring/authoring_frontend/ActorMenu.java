package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ActorMenu extends VBox {
    private final int NUM_ACTORS = 43;
    private GameManager myManager;
    private ScrollPane backgroundTilePane = new ScrollPane();
    private ScrollPane actorTilePane = new ScrollPane();
    private BorderPane selectedPane = null;
    private ActorManager actorManager;
    private String programName;

    public ActorMenu(GameManager manager, ActorManager aManager, String name) {
        programName = name;
        actorManager = aManager;
        myManager = manager;
        this.getChildren().add(new Label("menu"));
        setupTabs();
    }

    private void setupTabs(){
        backgroundTilePane.setContent(setupTab(actorManager.getBackgroundActors()));
        actorTilePane.setContent(setupTab(actorManager.getPlayableActors()));
    }

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
