package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javax.swing.border.Border;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapManager {
    //stores and keeps map names to map objects
    private HashMap<String, Map> gameMaps = new HashMap<>();
    private int numMaps = 0;
    private String programName;
    private BorderPane activeMap = new BorderPane();
    private ArrayList<Portal> portals = new ArrayList<>();
    private GameManager gameManager;

    MapManager(String pName, GameManager myManager){
        programName = pName;
        gameManager = myManager;
    }

    public void createMap(String name, int width, int height){
        gameMaps.put(name, new Map(width, height, programName, gameManager));
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

    public BorderPane getActiveMap(){
        return activeMap;
    }

    public void setActiveMap(String name){
        if(gameMaps.containsKey(name)){
            activeMap.setCenter(gameMaps.get(name).getGridPane());
        }
    }

    public HashMap<String, Map> getGameMaps(){
        return gameMaps;
    }

    public List<String> getMapList(){
        return new ArrayList<>(gameMaps.keySet());
    }

    public void addPortal(Portal toAdd){
        portals.add(toAdd);
    }

    public void removePortal(Portal toRemove){
        portals.remove(toRemove);
    }
}
