package authoring.authoring_frontend;

import javafx.util.Pair;

import java.util.HashMap;

public class MapConnector {
    private Map currentMap;
    private MapManager mapManager;

    MapConnector(String mapName, MapManager manager){
        mapManager = manager;
        currentMap = mapManager.getMap(mapName);
    }

    public void selectMapDialog(){

    }

    public void selectSquaresDialog(){

    }

    public void addAnotherConnection(){

    }
}
