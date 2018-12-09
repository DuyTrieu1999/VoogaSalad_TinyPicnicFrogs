package engine.frontend.game_engine_UI.MenuView;

import engine.backend.Commands.Command;
import engine.controller.Controller;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.util.List;

public class DialogueMenu extends MenuView{

    public DialogueMenu (Controller controller, String text) {
        super(controller);
        ScrollPane scrollPane = new ScrollPane();
        Text messageText = new Text(text);
        scrollPane.setContent(messageText);
        scrollPane.setPrefViewportHeight(50);
        scrollPane.setPrefViewportWidth(1000);
        scrollPane.setLayoutY(670);
        pane.setBottom(scrollPane);
    }
}
