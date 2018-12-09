package engine.backend;

import engine.backend.gameevent.InputSource;
import engine.frontend.game_engine_UI.MenuView.MenuView;
import menu.CommandLineMenu;
import menu.Menu;

import static java.lang.Thread.sleep;

/**
 * Executes a player controlled turn
 * @author Christopher Lin cl349
 */
public class PlayerTurn extends Turn {

    PlayerTurn(CombatInteraction controlledAlly){
        mySource = InputSource.PLAYER;
        myInt = controlledAlly;
    }



    @Override
    public void initializeTurn() {
        System.out.println("Player Turn");
        Menu myMenu = new CommandLineMenu(myInt.getCommandList());
        myMenu.getChoices().get(0).execute(null);
    }
}
