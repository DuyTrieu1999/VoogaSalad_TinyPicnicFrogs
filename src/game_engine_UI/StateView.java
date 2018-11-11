package game_engine_UI;

import Controller.Controller;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

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
