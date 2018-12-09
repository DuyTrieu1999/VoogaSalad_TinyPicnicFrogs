package engine.frontend.game_engine_UI.AnimationProcesser;

public class Sprite {
    int x,y;
    double width,height;
    public Sprite (int x, int y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public int getX () { return x; }
    public int getY () { return y; }
    public double getWidth () { return width; }
    public double getHeight () { return height; }
}
