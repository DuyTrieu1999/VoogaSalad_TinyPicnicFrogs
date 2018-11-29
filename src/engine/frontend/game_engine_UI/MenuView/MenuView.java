package engine.frontend.game_engine_UI.MenuView;

import engine.backend.Commands.Command;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuView extends HBox {
    private BorderPane pane;
    private List<Command> commandList;
    private List<Command> activeCommands;
    private List<LayoutBox> layoutBoxes;
    private HashMap<LayoutBox, Command> map = new HashMap<>();

    public MenuView () {
        setUp();
    }
    private void setUp() {
        pane = new BorderPane();
        commandList = new ArrayList<>();
        layoutBoxes = new ArrayList<>();
        activeCommands = new ArrayList<>();
        addLayout();
        addSelectedButton();
        pane.getChildren().addAll(layoutBoxes);
        this.getChildren().add(pane);
    }
    private void addLayout() {
        for (Command command : commandList) {
            LayoutBox box = new LayoutBox(command.getName());
            layoutBoxes.add(box);
            map.put(box, command);
        }
    }
    private void addSelectedButton () {
        for (LayoutBox box: layoutBoxes) {
            if (box.activeProperty().get()) {
                activeCommands.add(map.get(box));
            }
        }
    }
    public void addCommandUI (List<Command> commands) {
        commandList = commands;
    }
    public List<Command> returnActiveCommands () {
        return activeCommands;
    }
}
