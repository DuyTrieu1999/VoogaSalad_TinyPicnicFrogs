package authoring;

import engine.backend.Animation;
import engine.backend.Message;

import java.util.Collection;
import java.util.Map;

public interface ActorPrototypeManager{

    ActorPrototype instantiatePrototype(int apID);

    void addNewPrototype(int apID, Animation a, State, Interactions);

    void deletePrototype(int apID);

}