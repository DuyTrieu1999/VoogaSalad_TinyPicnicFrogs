package simpleCombat;

import authoring.Actor;


import java.util.List;

public class simpleCombat {

    CombatManager myCombatManager;
    List<Actor> myAllies;
    List<Actor> myEnemies;


    simpleCombat(List<Actor> allyActors, List<Actor> enemyActors){
        myCombatManager = new CombatManager(allyActors, enemyActors);
    }
    public void runCombat(){
        myCombatManager.runCombat();
    }
}
