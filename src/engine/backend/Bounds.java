package engine.backend;

public class Bounds {
   private int width;
   private int height;

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
   private int relX;
    private int relY;

    public int getWidth() {
        return width;
    }

    public Bounds(int width, int height, int relX, int relY){
        this.width = width;
        this.height = height;
        this.relX = relX;
        this.relY = relY;
    }
}
