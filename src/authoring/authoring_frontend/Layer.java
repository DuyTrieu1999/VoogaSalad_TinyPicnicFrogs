package authoring.authoring_frontend;

public class Layer {
    private int id;

    Layer(){
        this(1);
    }

    Layer(int layerID){
        id = layerID;
    }

    public int getLayerID(){
        return id;
    }

    public String toString(){
        return "Layer " + id;
    }
}
