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
    private List<LayoutBox> layoutBoxes = new ArrayList<>();;
    private HashMap<LayoutBox, Command> map = new HashMap<>();

    public MenuView (List<Command> list) {
        commandList = list;
        setUp();
    }
    private void setUp() {
        pane = new BorderPane();
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
    public List<Command> returnActiveCommands () {
        return activeCommands;
    }
}
