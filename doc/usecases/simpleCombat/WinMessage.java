package simpleCombat;

public class WinMessage implements engine.backend.Message {
    @Override
    public String getMessage() {
        return "WIN";
    }
}
