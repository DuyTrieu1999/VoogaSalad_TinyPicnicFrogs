package engine.backend;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Christopher Lin cl349
 */
public class CombatManager {
    List<CombatInteraction> myAllies;
    List<CombatInteraction> myEnemies;
    AI myAI;
    private List<Turn> turnList;

    /**
     *
     * @param allies List of CombatInteractions representing allies
     * @param enemies .. representing enemies
     * @param initiativeComparator A comparator used to sort the Turn list
     */
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

    /**
     * Runs the combat interaction until one side is completely dead
     */
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
            myEnemies.removeAll(deadList);
        }
    }

    public List<Integer> getAlliesHealth(){
        var healthList = new ArrayList<Integer>();
        for(CombatInteraction a : myAllies){
            healthList.add(a.getHealth());
        }
        return healthList;
    }

    public List<Integer> getEnemiesHealth(){
        var healthList = new ArrayList<Integer>();
        for(CombatInteraction a : myEnemies){
            healthList.add(a.getHealth());
        }
        return healthList;
    }

    public List<AnimationObject> getAlliesIdleAnimation(){
        var animationList = new ArrayList<AnimationObject>();
        for(CombatInteraction a : myAllies){
            animationList.add(a.getCombatIdleAnimation());
        }
        return animationList;
    }

    public List<AnimationObject> getEnemiesIdleAnimation(){
        var animationList = new ArrayList<AnimationObject>();
        for(CombatInteraction a : myEnemies){
            animationList.add(a.getCombatIdleAnimation());
        }
        return animationList;
    }
}
