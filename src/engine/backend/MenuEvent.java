package engine.backend;

import javafx.event.Event;
import javafx.event.EventType;

public class MenuEvent extends Event {
    public MenuEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }
}
