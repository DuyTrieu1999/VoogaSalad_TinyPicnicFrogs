package authoring.authoring_frontend;

import java.util.HashMap;

public class ActiveMap {
    //service locator class for the currently active map per window
    private static HashMap<String, String> mapHashMap = new HashMap<>();

    public static void setActiveMap(String projectName, String newActiveMap){
        mapHashMap.put(projectName, newActiveMap);
        //activeItem = newActiveItem;
    }

    public static String getActiveMap(String projectName){
        return mapHashMap.get(projectName);
        //return activeItem;
    }

    public static void removeActiveMap(String projectName){
        mapHashMap.remove(projectName);
    }
}
