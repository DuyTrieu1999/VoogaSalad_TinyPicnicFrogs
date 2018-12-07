package engine.backend.gameevent;

import javafx.event.Event;

public abstract class GameEvent {
    Event myEvent;
    public Event getEvent(){
        return myEvent;
    }
}
