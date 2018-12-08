package engine.backend;

/**
 * Represents a single turn in combat
 * @author Christopher Lin (cl349)
 */

public abstract class Turn {
    protected CombatInteraction myInt;

    public CombatInteraction getCombatInteraction(){
        return myInt;
    }

    public abstract void executeTurn();
}
