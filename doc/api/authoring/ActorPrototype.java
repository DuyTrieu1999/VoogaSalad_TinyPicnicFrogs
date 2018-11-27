package authoring;

import engine.backend.Animation;
import engine.backend.Message;

import java.util.Collection;
import java.util.Map;

public interface ActorPrototype {
    ActorPrototype clone();
    Collection<Animation> getAnimations();
    Collection <State> getStates();
    State getDefaultState();
    Map<Message,State> getMessageStateMap();
}