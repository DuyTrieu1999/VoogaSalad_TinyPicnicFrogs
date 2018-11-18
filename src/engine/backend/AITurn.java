package engine.backend;

public class AITurn extends Turn {

    CombatInteraction myInt;
    AITurn(CombatInteraction controlledEnemy){
        myInt = controlledEnemy;
    }

    @Override
    public void executeTurn() {
        var ai = ServiceLocator.getAI();
        ai.setOptions(myInt.getCommandList());
        ai.getOption().execute(null);
    }
}
