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

    /**
     * Gets the input from the user or AI. This must release the lock in
     * combatManager in order for the combat to advance
     */
    public abstract void getInput();
    public abstract void initializeTurn();
}
