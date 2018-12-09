package engine.backend;

import engine.backend.Commands.CombatMove;

import engine.backend.Commands.Command;
import engine.backend.gameevent.GameEvent;
import engine.backend.gameevent.GameMenuEvent;
import engine.backend.gameevent.InputSource;

import javax.swing.event.MenuEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.spi.LocaleServiceProvider;

/**
 * Manages the combat state (taking turns, etc).
 *
 * @author Christopher Lin cl349
 */
public class CombatManager {
    List<CombatInteraction> myAllies;
    List<CombatInteraction> myEnemies;
    AI myAI;
    private List<Turn> turnList;
    boolean turnLock;
    CombatMove nextMove;
    InputSource nextSource;


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
        turnLock = true;
        for(CombatInteraction a : myAllies){
            turnList.add(new PlayerTurn(a));
        }
        for(CombatInteraction e: enemies){
            turnList.add(new AITurn(e));
        }
        turnList.sort(initiativeComparator);
        nextMove = null;

    }

    public List<Command> getAllyCommandList() {
        return myAllies.get(0).getCommandList();
    }


    /**
     * Call this method every cycle during combat. This will advance the combat state when it is ready
     */
    public void combatTick(){
        if(!turnLock){
            //nextMove.execute(null);
            nextTurn();
        }
    }

    /**
     * This releases the lock and allows combat to move on
     */
    public void receiveInput(GameMenuEvent e){
        if(e.getSource() == nextSource){
            if(e.getSource() == InputSource.PLAYER){
                e.getOption().bind(myEnemies.get(0));
            }
            else if(e.getSource() == InputSource.AI){
                e.getOption().bind(myAllies.get(0));
            }
            e.getOption().execute(null);
            turnLock = false;
        }
    }


    /**
     * Runs the next turn of the combat
     */
    public void nextTurn(){
        System.out.println("next turnss");
        turnLock = true;
        //run the current turn and put it on the end of the queue
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
        if(myAllies.size() < 1){
            playerDefeat();
        }
        if(myEnemies.size()< 1){
            playerVictory();
        }

        nextSource = turnList.get(0).getExpectedSource();
        turnList.get(0).initializeTurn();
    }


    private void playerDefeat() {
        System.out.println("PLAYER LOST");
    }

    private void playerVictory(){
        System.out.println("PLAYER WON");
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
        //System.out.println(animationList.size());
        return animationList;
    }
}
