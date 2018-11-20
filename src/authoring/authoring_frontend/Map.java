package authoring.authoring_frontend;

public class Map {
    private int id;

    Map(){
        this(1);
    }

    Map(int mapID){
        id = mapID;
    }

    public int getMapID(){
        return id;
    }

    public String toString(){
        return "Map " + id;
    }
}
