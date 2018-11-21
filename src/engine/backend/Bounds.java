package engine.backend;

public class Bounds {
    int width;
    int height;

    public int getHeight() {
        return height;
    }

    public int getRelX() {
        return relX;
    }

    public int getRelY() {
        return relY;
    }

    //the top left of bounding box expressed relative to the top of the Actor's coordinates
    int relX;
    int relY;

    public int getWidth() {
        return width;
    }

    Bounds(int width, int height, int relX, int relY){
        this.width = width;
        this.height = height;
        this.relX = relX;
        this.relY = relY;
    }
}
