package engine.frontend.game_engine_UI.MenuView;

import engine.backend.Commands.Command;
import engine.backend.ServiceLocator;
import engine.backend.gameevent.GameMenuEvent;
import engine.backend.gameevent.InputSource;
import engine.controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MenuView extends HBox {
    protected BorderPane pane;
    protected List<Command> commandList;
    protected List<Command> activeCommands;
    protected ListView<String> listView;
    Map<String, Command> map = new HashMap<>();

    public MenuView (Controller controller) {
        pane = new BorderPane();
        commandList = controller.getAllCommand();
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
        this.getChildren().add(pane);
    }
    public void setSelectedCommand() {

    }
    public List<Command> getActiveCommands () {
        return activeCommands;
    }
}
