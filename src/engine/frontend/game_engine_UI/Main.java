package engine.frontend.game_engine_UI;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        StateView stateView = new StateView();
        stage.setTitle("SLogo Team 08");
        stage.setScene(stateView.getScene());
        stage.show();
    }
}
