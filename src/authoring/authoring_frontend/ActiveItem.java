package authoring.authoring_frontend;

import javafx.scene.layout.BorderPane;

public class ActiveItem {
    private static BorderPane activeItem = null;

    public static void setActiveItem(BorderPane newActiveItem){
        //newActiveItem.setOnMouseClicked(null);
        activeItem = newActiveItem;
    }

    public static BorderPane getActiveItem(){
        return activeItem;
    }
}
