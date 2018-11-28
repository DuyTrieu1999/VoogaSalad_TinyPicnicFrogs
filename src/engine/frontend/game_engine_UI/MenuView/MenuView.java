package engine.frontend.game_engine_UI.MenuView;

import engine.backend.Commands.Command;
import engine.frontend.game_engine_UI.WorldView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class MenuView {
    private Scene menuScene;
    private WorldView view;
    private BorderPane pane;
    List<Command> commandList;
    List<Command> activeCommands;
    List<LayoutBox> layoutBoxes;
    HashMap<LayoutBox, Command> map = new HashMap<>();

    public MenuView () {
        setUp();
        menuScene = new Scene(pane);
    }
    private void setUp() {
        pane = new BorderPane();
        commandList = new ArrayList<>();
        layoutBoxes = new ArrayList<>();
        addLayout();
        addSelectedButton();
        pane.getChildren().addAll(layoutBoxes);
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
    public Scene getMenuScene () { return menuScene; }
}
