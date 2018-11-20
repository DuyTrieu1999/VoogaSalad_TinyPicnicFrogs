package authoring.authoring_frontend;

public class ActiveItem {
    private static Actor activeItem = null;

    public static void setActiveItem(Actor newActiveItem){
        activeItem = newActiveItem;
    }

    public static Actor getActiveItem(){
        return activeItem;
    }
}
