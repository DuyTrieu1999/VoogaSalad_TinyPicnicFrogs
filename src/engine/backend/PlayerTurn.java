package engine.backend;

import menu.CommandLineMenu;
import menu.Menu;

import static java.lang.Thread.sleep;

/**
 * Executes a player controlled turn
 * @author Christopher Lin cl349
 */
public class PlayerTurn extends Turn {

    PlayerTurn(CombatInteraction controlledAlly){
        myInt = controlledAlly;
    }

    @Override
    public void executeTurn() {
        var myController = ServiceLocator.getController();
        myController.getActiveCommands();

        Menu myMenu = new CommandLineMenu(myInt.getCommandList());
        myMenu.getChoices().get(0).execute(null);
    }
}
