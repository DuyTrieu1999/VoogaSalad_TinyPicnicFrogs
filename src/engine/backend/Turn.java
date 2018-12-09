package engine.backend;

import engine.backend.gameevent.InputSource;
import engine.backend.interactions.CombatInteraction;

/**
 * Represents a single turn in combat
 * @author Christopher Lin (cl349)
 */

public abstract class Turn {
    protected InputSource mySource;
    protected CombatInteraction myInt;

    public CombatInteraction getCombatInteraction(){

        return myInt;
    }

    /**
     * Gets the input from the user or AI. This must release the lock in
     * combatManager in order for the combat to advance
     */
    public abstract void initializeTurn();

    public InputSource getExpectedSource(){
        return mySource;
    }
}
