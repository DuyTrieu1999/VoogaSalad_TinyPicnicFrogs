package engine.frontend.game_engine_UI.MenuView;

import engine.backend.Commands.Command;
import engine.controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DialogueMenu extends HBox {
    private BorderPane pane = new BorderPane();
    Map<String, Command> map = new HashMap<>();
    ListView<String> listView = new ListView<>();

    public DialogueMenu (Controller controller, String text) {
        ScrollPane scrollPane = new ScrollPane();
        Text messageText = new Text(text);
        scrollPane.setContent(messageText);
        scrollPane.setPrefViewportHeight(50);
        scrollPane.setPrefViewportWidth(1000);
        scrollPane.setLayoutY(670);
        pane.setLeft(scrollPane);
        this.getChildren().add(pane);
    }

    public void addListView (List<Command> commands) {
        List<String> nameList = new ArrayList<>();
        for (Command command:commands) {
            nameList.add(command.getName());
            map.put(command.getName(), command);
        }
        ObservableList<String> items = FXCollections.observableArrayList (nameList);
        listView.setPrefSize(100, 50);
        listView.setItems(items);
        pane.setRight(listView);
    }
}
