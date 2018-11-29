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
        animationView= new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(path)));
    }

    public AnimationObject (String name,String path,Coordinate coordinateP) {
        this.animationName = name;
        animationPath=path;
//        System.out.println("TESTP"+path);
        animationView= new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(path)));
//        System.out.println("TEST: "+animationView.getImage().getUrl());
        coordinate=coordinateP;
    }
    public ImageView getAnimationView () {
        return this.animationView;
    }
    public String getName(){return animationName+": "+animationPath;}
    public Coordinate getCoordinate(){return coordinate;}
    protected void setImage(){
        //System.out.println(this.getClass().getResource("1.png").getPath());
        animationView= new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(animationPath)));
    }
}
