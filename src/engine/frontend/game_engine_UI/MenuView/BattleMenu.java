package engine.frontend.game_engine_UI.MenuView;

import engine.backend.ServiceLocator;
import engine.backend.gameevent.GameMenuEvent;
import engine.backend.gameevent.InputSource;
import engine.controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class BattleMenu extends MenuView {
    public BattleMenu (Controller controller) {
        super(controller);
    }
    @Override
    public void setSelectedCommand () {
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("clicked on " + listView.getSelectionModel().getSelectedItem());
                String command = listView.getSelectionModel().getSelectedItem();
                activeCommands.add(map.get(command));
                GameMenuEvent e = new GameMenuEvent(activeCommands.get(0), InputSource.PLAYER);
                ServiceLocator.getGameWorld().handleInput(e);

            }
        });
    }
}
