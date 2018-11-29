package authoring.authoring_frontend;

import java.util.HashMap;

/**
 * Active Item
 *
 * Service locator class to get the active item 
 */
public class ActiveItem {
    private static HashMap<String, Actor> actorHashMap = new HashMap<>();

    public static void setActiveItem(String projectName, Actor newActiveItem){
        actorHashMap.put(projectName, newActiveItem);
    }

    public static Actor getActiveItem(String projectName){
        return actorHashMap.get(projectName);
    }

    public static void removeActiveItem(String projectName){
        actorHashMap.remove(projectName);
    }
}
