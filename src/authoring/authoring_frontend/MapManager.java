package authoring.authoring_frontend;

import java.util.HashMap;
import java.util.List;

public class MapManager {
    //stores and keeps map names to map objects
    private HashMap<String, Map> gameMaps = new HashMap<>();
    private int numMaps = 0;
    private String programName;
    private Map activeMap;

    public void createMap(String name, int width, int height){
        gameMaps.put(name, new Map(width, height, programName));
    }

    public String createMap(int width, int height){
        numMaps++;
        createMap("Map " + numMaps, width, height);
        return "Map " + numMaps;
    }

    public void removeMap(String toRemove){
        gameMaps.remove(toRemove);
    }

    public void removeMap(List<String> toRemove){
        for(String mapToRemove:toRemove){
            gameMaps.remove(mapToRemove);
        }
    }

    public Map getMap(String name){
        return gameMaps.get(name);
    }

    public void renameMap(String oldName, String newName){
        gameMaps.put(newName, gameMaps.get(oldName));
        gameMaps.remove(oldName);
    }

    public Map getActiveMap(){
        return activeMap;
    }

    public void setActiveMap(String name){
        activeMap = gameMaps.get(name);
    }
}
