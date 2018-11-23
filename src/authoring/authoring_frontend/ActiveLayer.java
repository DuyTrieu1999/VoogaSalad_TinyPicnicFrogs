package authoring.authoring_frontend;

public class ActiveLayer {
    private static Layer activeLayer = null;

    public static void setActiveLayer(Layer newActiveLayer){
        activeLayer = newActiveLayer;
    }

    public static Layer getActiveLayer(){
        return activeLayer;
    }
}
