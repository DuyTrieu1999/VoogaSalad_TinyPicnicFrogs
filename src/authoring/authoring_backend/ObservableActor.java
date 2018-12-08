package authoring.authoring_backend;


import javafx.scene.image.ImageView;

public class ObservableActor {
    public String myId;
    public int x,y,z;
    public ImageView myView;
    public ObservableActor (String id, int xc,int yc,int zc,ImageView view){
        x=xc;
        y=yc;
        z=zc;
        myId=id;
        myView=view;
    }
}
