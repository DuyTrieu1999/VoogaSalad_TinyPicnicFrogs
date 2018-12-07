package engine.backend;

/**
 * Runs the turn for an AI controlled opponent
 *
 * @Christopher Lin cl349
 */
public class AITurn extends Turn {

    AITurn(CombatInteraction controlledEnemy){
        myInt = controlledEnemy;
    }

    @Override
    public void getInput() {

    }

    @Override
    public void initializeTurn() {

    }

}
