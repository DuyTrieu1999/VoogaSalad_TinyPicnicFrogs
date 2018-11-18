package engine.backend;

public abstract class Turn {
    protected CombatInteraction myInt;

    public CombatInteraction getCombatInteraction(){
        return myInt;
    }

    public abstract void executeTurn();
}
