package authoring.authoring_frontend;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main
 */
public class Main extends Application {

    public void start(Stage primaryStage) {
        AuthoringView environment = new AuthoringView();
        primaryStage.setTitle(environment.getProjectName());
        primaryStage.setScene(environment.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
