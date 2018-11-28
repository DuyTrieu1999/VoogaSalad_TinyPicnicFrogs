package authoring;

import engine.backend.Animation;
import engine.backend.Message;

import java.util.Collection;
import java.util.Map;

public interface ActorPrototypeManager{

    void addNewActor(Prototype p, int ID, int x, int y, int z);

    Actor getActor(int ID);

    void deleteActor(int ID);

    Collection<Actor> getAllActors();


}