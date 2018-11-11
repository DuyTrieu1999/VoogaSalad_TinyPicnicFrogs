package engine.frontend.game_engine_UI;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import engine.controller.Controller;

public class StateView {
    private Controller myController;
    private StackPane myPane;
    private static Pane myBackGround;

    public void setGameWorld() {
        myController = new Controller(this);
        myBackGround = new Pane();
        myPane = new StackPane();
        setUpScene();
    }
    private void setUpScene () {

    }
}
