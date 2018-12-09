package engine.backend;

import engine.backend.gameevent.GameMenuEvent;
import engine.backend.gameevent.InputSource;
import engine.backend.interactions.CombatInteraction;

/**
 * Runs the turn for an AI controlled opponent
 *
 * @Christopher Lin cl349
 */
public class AITurn extends Turn {

    AITurn(CombatInteraction controlledEnemy){
        mySource = InputSource.AI;
        myInt = controlledEnemy;
    }

    @Override
    public void initializeTurn() {
        System.out.println("AI Turn");
        ServiceLocator.getAI().setOptions(myInt.getCommandList());
        var nextMove = ServiceLocator.getAI().getOption();
        ServiceLocator.getCombatManager().receiveInput(new GameMenuEvent(nextMove, InputSource.AI));
    }
}
