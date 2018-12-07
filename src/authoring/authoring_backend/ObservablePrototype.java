package authoring.authoring_backend;


import javafx.scene.image.ImageView;
/**
 * @author Michael Glushakov
 * Purpose: Passed to authoring front-end to keep original ActorPrototypes closed to modification
 * Dependencies: None
 * Usages: when an ActorPrototype is created it creates an observable prototype and puts it in a list. Front-end can then request access to the list
 */
public class ObservablePrototype {
    public String myId;
    public ImageView myView;
    public ObservablePrototype(String id, ImageView view){myId=id;myView=view;}
}
