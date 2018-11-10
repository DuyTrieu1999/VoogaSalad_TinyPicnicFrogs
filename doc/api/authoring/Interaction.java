package authoring;

import engine.backend.Animation;
import engine.backend.Message;

import java.util.Collection;

public interface Interaction {
    Collection <Animations> getInterractionAnimations();
    Message getMessage();
}