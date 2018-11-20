package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ActorMenu extends VBox {
    private GameManager myManager;
    private ScrollPane tilePane = new ScrollPane();
    private BorderPane selectedPane = null;

    public ActorMenu(GameManager manager) {
        myManager = manager;
        this.getChildren().add(new Label("menu"));
        setupMenu();
    }

    public void setupMenu(){
        //todo: sprite number detection
        tilePane.setPrefViewportWidth(200);
        tilePane.setPrefViewportHeight(400);
        tilePane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        ArrayList<BorderPane> tileImages = new ArrayList<>();
            for(int i=1;i<43;i++) {
                Actor thisActor = new Actor(new Image(i + ".png"));
                BorderPane thisTileImage = new BorderPane(thisActor.getActorImage());
                thisTileImage.setOnMouseClicked(event -> {
                    Actor currentActiveActor = ActiveItem.getActiveItem();
                    if(currentActiveActor == null){
                        //first tile clicked
                        ActiveItem.setActiveItem(thisActor);
                        thisTileImage.setStyle("-fx-border-color: blue;");
                        selectedPane = thisTileImage;
                    }
                    else if(currentActiveActor.equals(thisActor)){
                        //deselect
                        selectedPane.setStyle(null);
                        selectedPane = null;
                        ActiveItem.setActiveItem(null);
                    }
                    else {
                        //replace old selection
                        selectedPane.setStyle(null);
                        ActiveItem.setActiveItem(thisActor);
                        selectedPane = thisTileImage;
                        thisTileImage.setStyle("-fx-border-color: blue;");
                    }
                });
                tileImages.add(thisTileImage);
            }
        FlowPane tileHolder = new FlowPane();
        tileHolder.getChildren().addAll(tileImages);
        tileHolder.setPrefWrapLength(200);
        tilePane.setContent(tileHolder);
    }

    public ScrollPane getActorMenu(){
        return tilePane;
    }
}
