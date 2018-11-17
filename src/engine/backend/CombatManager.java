package engine.backend;

import java.util.List;

public class CombatManager {
    List<CombatInteraction> myAllies;
    List<CombatInteraction> myEnemies;
    AI myAI;

    CombatManager(List<CombatInteraction> allies, List<CombatInteraction> enemies, AI combatAI){
        myAllies = allies;
        myEnemies = enemies;
    }

    public void runCombat(){

    }
}
