package authoring.authoring_frontend;

import javafx.scene.layout.BorderPane;

public class ActiveItem {
    private static BorderPane activeItem = null;

    public static void setActiveItem(BorderPane newActiveItem){
        activeItem = newActiveItem;
    }

    public static BorderPane getActiveItem(){
        return activeItem;
    }
}
