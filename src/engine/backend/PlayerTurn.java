package engine.backend;

import menu.CommandLineMenu;
import menu.Menu;

public class PlayerTurn extends Turn {

    CombatInteraction myInt;
    PlayerTurn(CombatInteraction controlledAlly){
        myInt = controlledAlly;
    }

    @Override
    public void executeTurn() {
        Menu myMenu = new CommandLineMenu(myInt.getCommandList());
        myMenu.getChoices().get(0).execute(null);

    }
}
