package simpleCombat;

public class LoseMessage implements engine.backend.Message{
    @Override
    public String getMessage() {
        return "LOSE";
    }
}
