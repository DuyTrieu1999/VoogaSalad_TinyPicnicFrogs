package engine.backend;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CombatManager {
    List<CombatInteraction> myAllies;
    List<CombatInteraction> myEnemies;
    AI myAI;
    private List<Turn> turnList;

    CombatManager(List<CombatInteraction> allies, List<CombatInteraction> enemies, Comparator<Turn> initiativeComparator){
        myAllies = allies;
        myEnemies = enemies;
        turnList = new ArrayList<>();
        for(CombatInteraction a : myAllies){
            turnList.add(new PlayerTurn(a));
        }
        for(CombatInteraction e: enemies){
            turnList.add(new AITurn(e));
        }
        turnList.sort(initiativeComparator);
    }

    public void runCombat(){
        while(myAllies.size() > 0 || myEnemies.size() > 0){
            //run the current turn and put it on the end of the queue
            var currentTurn = turnList.get(0);
            currentTurn.executeTurn();
            turnList.add(turnList.remove(0));
            //remove dead
            List<CombatInteraction> deadList = new ArrayList<>();
            for(CombatInteraction a : myAllies){
                if(a.getHealth() <= 0){
                    deadList.add(a);
                }
            }
            myAllies.removeAll(deadList);
            deadList.clear();
            for(CombatInteraction e : myEnemies){
                if(e.getHealth() <= 0){
                    deadList.add(e);
                }
            }
            myEnemies.removeAll(myEnemies);
        }
    }
}
