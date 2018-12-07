package engine.backend.AI;

import engine.backend.CombatInteraction;
import engine.backend.ServiceLocator;
import engine.backend.Turn;

/**
 * Runs the turn for an AI controlled opponent
 *
 * @Christopher Lin cl349
 */
public class AITurn extends Turn {

    public AITurn(CombatInteraction controlledEnemy){
        myInt = controlledEnemy;
    }

    @Override
    public void executeTurn() {
        var ai = ServiceLocator.getAI();
        ai.setOptions(myInt.getCommandList());
        ai.getOption().execute(null);
    }
}
