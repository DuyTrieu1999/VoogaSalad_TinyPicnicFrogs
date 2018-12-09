package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Manages maps created by the user.
 *
 * @author Allen Qiu
 */
public class MapManager {
    //stores and keeps map names to map objects
    private HashMap<String, Map> gameMaps = new HashMap<>();
    private int numMaps = 0;
    private String programName;
    private String activeMapName;
    private BorderPane activeMap = new BorderPane();
    private ArrayList<Portal> portals = new ArrayList<>();
    private GameManager gameManager;

    /**
     * Constructor
     * @param pName Program name.
     * @param myManager GameManager of the game.
     */
    MapManager(String pName, GameManager myManager){
        programName = pName;
        gameManager = myManager;
    }

    /**
     * Creates a new map.
     * @param name Name of the map.
     * @param width Width of map in cells.
     * @param height Height of map in cells.
     */
    public void createMap(String name, int width, int height){
        gameMaps.put(name, new Map(width, height, programName, gameManager));
    }

    /**
     * Creates map but auto increments name.
     * @param width Width of map in cells.
     * @param height Height of map in cells.
     * @return
     */
    public String createMap(int width, int height){
        numMaps++;
        createMap("Map " + numMaps, width, height);
        return "Map " + numMaps;
    }

    /**
     * Removes a map.
     * @param toRemove Name of map to remove.
     */
    public void removeMap(String toRemove){
        gameMaps.remove(toRemove);
    }

    /**
     * Removes multiple maps.
     * @param toRemove List of map name to remove.
     */
    public void removeMap(List<String> toRemove){
        for(String mapToRemove:toRemove){
            gameMaps.remove(mapToRemove);
        }
    }

    /**
     * Gets a map from its name.
     * @param name Name of the map
     * @return The map.
     */
    public Map getMap(String name){
        return gameMaps.get(name);
    }

    /**
     * Renames a map
     * @param oldName The current name of the map.
     * @param newName The new name of the map.
     */
    public void renameMap(String oldName, String newName){
        gameMaps.put(newName, gameMaps.get(oldName));
        gameMaps.remove(oldName);
    }

    /**
     * Gets the currently active map.
     * @return Cureently active map.
     */
    public ScrollPane getActiveMap(){
        return new ScrollPane(activeMap);
    }

    /**
     * Sets the currently active map.
     * @param name Name of the new active map.
     */
    public void setActiveMap(String name){
        if(gameMaps.containsKey(name)){
            activeMapName = name;
            activeMap.setCenter(gameMaps.get(name).getScrollPane());
        }
    }

    /**
     * Gets a list of names to maps.
     * @return Map of names to maps.
     */
    public HashMap<String, Map> getGameMaps(){
        return gameMaps;
    }

    /**
     * Gets names of all maps.
     * @return List of map names.
     */
    public List<String> getMapList(){
        return new ArrayList<>(gameMaps.keySet());
    }

    /**
     * Adds a portal.
     * @param toAdd Portal to add.
     */
    public void addPortal(Portal toAdd){
        portals.add(toAdd);
    }

    /**
     * Removes a portal.
     * @param toRemove Portal to remove.
     */
    public void removePortal(Portal toRemove){
        portals.remove(toRemove);
    }

    public String getActiveMapName(){
        return activeMapName;
    }

    public void findNewActiveMap(List<String> deletedMaps){
        for(String mapName:gameMaps.keySet()){
            if(!deletedMaps.contains(mapName)){
                setActiveMap(mapName);
            }
        }
    }

    public void changeMapDimensions(int newWidth, int newHeight){
        if(newWidth < getMap(activeMapName).getWidth() || newHeight < getMap(activeMapName).getHeight()){
            //clipping
            for(Iterator<Portal> iter = portals.iterator(); iter.hasNext();){
                Portal p = iter.next();
                if(p.withinWidth(newWidth) || p.withinHeight(newHeight)){
                    //delete portal
                    iter.remove();
                }
            }

            //delete actors on map
            Grid thisGrid = getMap(activeMapName).getGrid();
            for(int i=0;i<thisGrid.getCells().size();i++){
                for(int j=0;j<thisGrid.getCells().get(i).size();j++){
                    if((j+1 > newWidth || i+1 > newHeight) && thisGrid.getCells().get(i).get(j).getActors().size() > 0){
                        //delete all these actors
                        for(Actor thisActor:thisGrid.getCells().get(i).get(j).getActors()){
                            gameManager.deleteActor(thisActor.getActorPrototypeID()+i+"-"+j+"-"+(thisGrid.getCells().get(i).get(j).getActors().size()-1));
                        }
                    }
                }
            }
        }
        getMap(activeMapName).changeWidth(newWidth);
        getMap(activeMapName).changeHeight(newHeight);

        //System.out.println("Calling changedimensions");
        getMap(activeMapName).getGrid().changeDimensions(newWidth, newHeight);

        //change map size in map object

        //change view
        setActiveMap(activeMapName);
    }

}
