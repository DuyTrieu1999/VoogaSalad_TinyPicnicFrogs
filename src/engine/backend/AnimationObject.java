package engine.backend;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AnimationObject {
    private String animationName;
    private String animationPath;
    private ImageView animationView;
    private Coordinate coordinate;

    public AnimationObject (String name,String path) {
        this.animationName = name;
        animationPath=path;
    System.out.println(path);
   // Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("demo/player_fight.png"));
    //System.out.println(this.getClass().getClassLoader().getResource("demo/player_fight.png").getPath());

        animationView= new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("demo/player_fight.png")));
    }
    public ImageView getAnimationView () {
        return this.animationView;
    }
    public String getName(){return animationName+": "+animationPath;}
    public Coordinate getCoordinate(){return coordinate;}
}
