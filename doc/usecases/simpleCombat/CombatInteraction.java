package simpleCombat;

import authoring.Animations;
import engine.backend.Message;

import java.util.Collection;

public class CombatInteraction implements authoring.Interaction {
    @Override
    public Collection<Animations> getInterractionAnimations() {
        return null;
    }
    public Message defeatMessage(){
        return new LoseMessage();
    }

    public Message winMessage(){
        return new WinMessage();
    }
    @Override
    public Message getMessage() {
        return null;
    }
}
