package engine.backend;
import authoring.Actor;
import java.util.List;

public interface Command {
    public void execute();
    public void bind(List<Actor> actors);
    public String getName();
}
