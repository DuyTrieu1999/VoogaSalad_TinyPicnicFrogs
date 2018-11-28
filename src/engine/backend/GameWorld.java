package engine.backend;

import engine.backend.Commands.*;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Christopher Lin cl349
 */
public class GameWorld {

    private static int myMapHeight;
    private static int myMapWidth;
    private String myName;
    private GameState myGameState;

    private Map<KeyCode, Command> keyMap;

    GameWorld(int mapHeight, int mapWidth){
        myMapHeight = mapHeight;
        myMapWidth = mapWidth;
        myName = "Game";
        myGameState = GameState.Overworld;
        keyMap = new HashMap<>();
        var myPlayer = ServiceLocator.getActorManager().getPlayerActor();

        //Default keybinds. TODO: let these be player controlled
        var PlayerMoveUp = new MoveUpCommand();
        PlayerMoveUp.bind(myPlayer);
        var PlayerMoveDown = new MoveDownCommand();
        PlayerMoveDown.bind(myPlayer);
        var PlayerMoveLeft = new MoveLeftCommand();
        PlayerMoveLeft.bind(myPlayer);
        var PlayerMoveRight = new MoveRightCommand();
        PlayerMoveRight.bind(myPlayer);
        keyMap.put(KeyCode.W, PlayerMoveUp);
        keyMap.put(KeyCode.A, PlayerMoveLeft);
        keyMap.put(KeyCode.S, PlayerMoveDown);
        keyMap.put(KeyCode.D, PlayerMoveRight);
    }

    public static int getMapHeight(){
        return myMapHeight;
    }
    public static int getMapWidth(){
        return myMapWidth;
    }
    public String getMyName() { return myName; }


    public void detectCollisions(){
        var actorList = ServiceLocator.getActorManager().getActiveActors();
        var collisionList = new ArrayList<Actor>();
        var playerActor = ServiceLocator.getActorManager().getPlayerActor();
        for(Actor a : actorList){

            if(overlaps(playerActor, a)){
                collisionList.add(a);
            }
        }
        for(Actor c : collisionList){
            launchInteraction(c.getInteraction());
        }
    }

    private void launchInteraction(Interaction interaction){
        if(interaction instanceof  CombatInteraction){
            myGameState = GameState.Combat;
            launchCombatInteraction((CombatInteraction) ServiceLocator.getActorManager().getPlayerActor().getInteraction(), (CombatInteraction) interaction);
            myGameState = GameState.Overworld;
        }
    }

    private void launchCombatInteraction(CombatInteraction playerInteraction, CombatInteraction enemyInterction){
        var alliesList = new ArrayList<CombatInteraction>();
        alliesList.add(playerInteraction);
        var enemyList = new ArrayList<CombatInteraction>();
        enemyList.add(enemyInterction);
        var combatMan = new CombatManager(alliesList, enemyList, new LowestHealthFirstInitiative());
        combatMan.runCombat();

    }

    public void handleInput(KeyCode c){
        final int DEFAULT_MOVE_AMOUNT = 10;
        var defaultParams = new ArrayList<>();
        defaultParams.add(DEFAULT_MOVE_AMOUNT);
        if(keyMap.containsKey(c)){
            keyMap.get(c).execute(defaultParams);
        }
    }



    public void activateOverWorld(){
        myGameState = GameState.Overworld;
    }

    public GameState getGameState(){
        return myGameState;
    }

    private boolean overlaps(Actor a1, Actor a2){
        var a1Coordinate = a1.getCoordinate();
        var a1Bounds = a1.getBounds();
        int a1MaxX = a1Coordinate.getX()+a1Bounds.getRelX()+a1Bounds.getWidth();
        int a1MinX = a1Coordinate.getX()+a1Bounds.getRelX();
        int a1MaxY = a1Coordinate.getY()+a1Bounds.getRelY()+a1Bounds.getHeight();
        int a1MinY = a1Coordinate.getY()+a1Bounds.getRelY();


        var a2Coordinate = a2.getCoordinate();
        var a2Bounds = a2.getBounds();
        int a2MaxX = a2Coordinate.getX()+a2Bounds.getRelX()+a2Bounds.getWidth();
        int a2MinX = a2Coordinate.getX()+a2Bounds.getRelX();
        int a2MaxY = a2Coordinate.getY()+a2Bounds.getRelY()+a2Bounds.getHeight();
        int a2MinY = a2Coordinate.getY()+a2Bounds.getRelY();

        boolean xIntersects = (a1MaxX > a2MinX && a1MaxX < a2MaxX) || (a2MaxX > a1MinX && a2MaxX < a1MaxX);
        boolean yIntersects = (a1MaxY > a2MinY && a1MaxY < a2MaxY) || (a2MaxY > a1MinY && a2MaxY < a1MaxY);
        System.out.println(xIntersects);
        System.out.println(yIntersects);
        return xIntersects && yIntersects;
    }




}
