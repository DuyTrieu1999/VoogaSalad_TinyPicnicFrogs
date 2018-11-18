package simpleCombat;
import authoring.Actor;
import engine.backend.Turn;

import java.util.List;

public class CombatManager implements engine.backend.CombatManager {
    List<Actor> enemies;
    List<Actor> allies;
    List<Turn> turnList;
    Broadcaster broadcaster;

    CombatManager(List<Actor> enemyList, List<Actor> allyList){
        enemies = enemyList;
        allies = allyList;
        turnList.add(new PlayerTurn());
        turnList.add(new AITurn());
    }
    @Override
    public void runCombat() {
        for(Turn t: turnList){
            if(enemies.size() < 1 || allies.size() < 1){
                break;
            }
            t.execute();
        }
        if(enemies.size() < 1){
            broadcaster.broadcast(new WinMessage());
        }
        if(allies.size() < 1){
            broadcaster.broadcast(new LoseMessage());
        }
    }

}
