package engine.frontend.game_engine_UI.MenuView;

import engine.backend.Commands.Command;
import engine.backend.ServiceLocator;
import engine.backend.gameevent.GameMenuEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuView extends HBox {
    private BorderPane pane;
    private List<Command> commandList;
    private List<Command> activeCommands;
    private ListView<String> listView;
    Map<String, Command> map = new HashMap<>();

    private boolean isClosed;
    private BorderPane view;

    public MenuView (List<Command> list, BorderPane view) {
        this.view = view;
        pane = new BorderPane();
        isClosed = false;
        commandList = list;
        activeCommands = new ArrayList<>();
        addListView();
    }
    private void addListView () {
        List<String> nameList = new ArrayList<>();
        for (Command command:commandList) {
            nameList.add(command.getName());
            map.put(command.getName(), command);
        }
        ObservableList<String> items = FXCollections.observableArrayList (nameList);
        listView = new ListView<>();
        listView.setPrefSize(100, 50);
        listView.setItems(items);
        pane.setCenter(listView);
        pane.setPadding(new Insets(10));
    }
    public void setSelectedCommand() {
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("clicked on " + listView.getSelectionModel().getSelectedItem());
                String command = listView.getSelectionModel().getSelectedItem();
                activeCommands.add(map.get(command));
                ServiceLocator.getGameWorld().handleInput(new GameMenuEvent());
            }
        });
    }
    public List<Command> getActiveCommands () {
        return activeCommands;
    }
    public BorderPane getPane () { return pane; }
}
