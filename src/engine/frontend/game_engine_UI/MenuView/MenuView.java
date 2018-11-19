package engine.frontend.game_engine_UI.MenuView;

import engine.backend.Commands.Command;
import engine.frontend.game_engine_UI.WorldView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public abstract class MenuView {
    private Scene menuScene;
    private WorldView view;
    private BorderPane pane;
    List<Command> commandList;
    List<Command> activeCommands;
    List<LayoutBox> layoutBoxes;

    public MenuView () {
        setUp();
    }
    private void setUp() {
        pane = new BorderPane();
        commandList = new ArrayList<>();
        layoutBoxes = new ArrayList<>();
        addLayout();
        pane.getChildren().addAll(layoutBoxes);
    }
    private void addLayout() {
        for (Command command : commandList) {
            LayoutBox box = new LayoutBox(command.getName());
            layoutBoxes.add(box);
        }
    }
    public void addCommandUI (List<Command> commands) {
        commandList = commands;
    }
    public List<Command> returnAtiveCommands () {
        return activeCommands;
    }
}
