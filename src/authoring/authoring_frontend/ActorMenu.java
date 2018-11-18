package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ActorMenu extends VBox {
    private GameManager myManager;
    private FlowPane tilePane = new FlowPane(Orientation.HORIZONTAL, 5, 5);
    private BorderPane activeTile = null;

    public ActorMenu(GameManager manager) {
        myManager = manager;
        this.getChildren().add(new Label("menu"));
        //setupMenu();
    }

    public void setupMenu(){
        //todo: sprite number detection
        tilePane.setPrefWrapLength(200);
        tilePane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        ArrayList<BorderPane> tileImages = new ArrayList<>();
        for(int j=0;j<40;j++){
            for(int i=1;i<5;i++) {
                BorderPane thisTileImage = new BorderPane(new ImageView(new Image(i + ".png")));
                thisTileImage.setOnMouseClicked(event -> {
                    if(activeTile == null){
                        //first tile clicked
                        activeTile = thisTileImage;
                        activeTile.setStyle("-fx-border-color: blue;");
                        ActiveItem.setActiveItem(activeTile);
                    }
                    else if(activeTile == thisTileImage){
                        //deselect
                        activeTile.setStyle(null);
                        ActiveItem.setActiveItem(null);
                    }
                    else {
                        //replace old selection
                        activeTile.setStyle(null);
                        activeTile = thisTileImage;
                        activeTile.setStyle("-fx-border-color: blue;");
                        ActiveItem.setActiveItem(activeTile);
                    }
                });
                tileImages.add(thisTileImage);
            }
        }
        tilePane.getChildren().addAll(tileImages);
    }

    public FlowPane getActorMenu(){
        return tilePane;
    }

    public BorderPane getActiveTile(){
        return activeTile;
    }
}
