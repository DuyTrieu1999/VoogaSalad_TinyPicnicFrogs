package engine.backend;

public interface Turn {
    public CombatMoveCommand getCommand();
    public void execute();
}
