package engine.backend;

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
        myInt = controlledAlly;

    }

    @Override
    public void getInput() {
        ServiceLocator.getCombatManager().inputRecieved();
    }


    @Override
    public void initializeTurn() {
        System.out.println("menu created");

    }


}
