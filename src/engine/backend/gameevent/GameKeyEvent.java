package engine.backend.gameevent;

import javafx.scene.input.KeyEvent;

public class GameKeyEvent extends GameEvent{
    public GameKeyEvent(KeyEvent e){
        myEvent = e;
    }
}
