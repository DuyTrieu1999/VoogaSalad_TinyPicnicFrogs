package engine.backend.gameevent;

import javafx.scene.input.KeyEvent;

public class GameKeyEvent extends GameEvent{
    GameKeyEvent(KeyEvent e){
        myEvent = e;
    }
}
