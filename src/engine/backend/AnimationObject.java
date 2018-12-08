package engine.backend;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AnimationObject {
    private String animationName;
    private String animationPath;
    private ImageView animationView;
    private Coordinate coordinate;

    private int spriteRows;
    private int spiteCols;

    public AnimationObject (String name,String path) {
        this.animationName = name;
        animationPath=path;
        animationView= new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(path)));
    }

    public AnimationObject (String name,String path,int sriteR,int spriteC) {
        this.animationName = name;
        animationPath=path;
        animationView= new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(path)));
        spriteRows=sriteR;
        spiteCols=spriteC;
    }


    public AnimationObject (String name,String path,Coordinate coordinateP) {
        this.animationName = name;
        animationPath=path;
        animationView= new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(animationPath)));
        coordinate=coordinateP;
    }
    public ImageView getAnimationView () {
        return this.animationView;
    }
    public String getName(){return animationName+": "+animationPath;}
    public Coordinate getCoordinate(){return coordinate;}
    public void setImage(){
        animationView= new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(animationPath)));
    }
}
