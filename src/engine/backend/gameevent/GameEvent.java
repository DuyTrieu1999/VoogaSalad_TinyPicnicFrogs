package engine.backend.gameevent;

import javafx.event.Event;

public abstract class GameEvent {
    Event myEvent;
    InputSource mySource;

    GameEvent(InputSource src){
        mySource = src;
    }

    public InputSource getSource(){
        return mySource;
    }
    public Event getEvent(){
        return myEvent;
    }
}
