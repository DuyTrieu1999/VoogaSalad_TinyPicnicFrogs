package authoring.authoring_frontend;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class Portal {
    private Pair<Integer, Integer> fromCoordinate;
    private Pair<Integer, Integer> toCoordinate;
    private boolean reversable;
    private String mapFrom;
    private String mapTo;
    private MapManager mapManager;

    Portal(Pair<Integer, Integer> from, String fromMap, Pair<Integer, Integer> to, String toMap, boolean reverse, MapManager manager){
        fromCoordinate = from;
        toCoordinate = to;
        reversable = reverse;
        mapFrom = fromMap;
        mapTo = toMap;
        mapManager = manager;
    }
}
