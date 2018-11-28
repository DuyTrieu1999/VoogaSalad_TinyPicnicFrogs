package authoring.authoring_frontend;

import java.util.HashMap;

public class ActiveItem {
    //private static Actor activeItem = null;
    private static HashMap<String, Actor> actorHashMap = new HashMap<>();

    public static void setActiveItem(String projectName, Actor newActiveItem){
        actorHashMap.put(projectName, newActiveItem);
        //activeItem = newActiveItem;
    }

    public static Actor getActiveItem(String projectName){
        return actorHashMap.get(projectName);
        //return activeItem;
    }

    public static void removeActiveItem(String projectName){
        actorHashMap.remove(projectName);
    }
}
