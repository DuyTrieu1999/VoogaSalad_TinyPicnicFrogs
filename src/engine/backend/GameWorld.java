package engine.backend;


import engine.backend.Commands.*;
import engine.backend.gameevent.GameEvent;
import engine.backend.gameevent.GameKeyEvent;
import engine.backend.gameevent.GameMenuEvent;
import javafx.concurrent.Service;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;


/**
 * Holds information and methods that are used for the entire game
 *
 * @author Christopher Lin cl349
 */
public class GameWorld {

    private static int myMapHeight;
    private static int myMapWidth;
    private GameState myGameState;

    private Map<KeyCode, Command> keyMap;

    public GameWorld(int mapHeight, int mapWidth){
        myMapHeight = mapHeight;
        myMapWidth = mapWidth;
        myGameState = GameState.Overworld;
        keyMap = new HashMap<>();
        var myPlayer = ServiceLocator.getActorManager().getPlayerActor();
//        var overWorldView = ServiceLocator.getController().getOverWorldView();

        //Default keybinds. TODO: let these be player controlled
        var PlayerMoveUp = new MoveUpCommand();
        PlayerMoveUp.bind(myPlayer);
        var PlayerMoveDown = new MoveDownCommand();
        PlayerMoveDown.bind(myPlayer);
        var PlayerMoveLeft = new MoveLeftCommand();
        PlayerMoveLeft.bind(myPlayer);
        var PlayerMoveRight = new MoveRightCommand();
        PlayerMoveRight.bind(myPlayer);
//        var OpenMenu = new OpenMenuCommand();
//        OpenMenu.bind(overWorldView);
//        var CloseMenu = new CloseMenuCommand();
//        CloseMenu.bind(overWorldView);

        keyMap.put(KeyCode.W, PlayerMoveUp);
        keyMap.put(KeyCode.A, PlayerMoveLeft);
        keyMap.put(KeyCode.S, PlayerMoveDown);
        keyMap.put(KeyCode.D, PlayerMoveRight);
//        keyMap.put(KeyCode.P, OpenMenu);
//        keyMap.put(KeyCode.ESCAPE, CloseMenu);
    }

    public int getMapHeight(){
        return myMapHeight;
    }
    public int getMapWidth(){
        return myMapWidth;
    }

    public void onTick(){
        if(myGameState == GameState.Overworld){
            detectCollisions();
        }
        if(myGameState == GameState.Combat){
            ServiceLocator.getCombatManager().combatTick();
        }
    }

    /**
     * Called by frontend on update cycle to detect collisions and trigger the
     * correct interactions
     */
    public void detectCollisions(){
        var actorList = ServiceLocator.getActorManager().getActiveActors();
        var collisionList = new ArrayList<Actor>();
        var playerActor = ServiceLocator.getActorManager().getPlayerActor();
        for(Actor a : actorList){
            if(overlaps(a, playerActor)){
                //TODO: get rid of magic values
                collisionList.add(a);
                if(playerActor.getHeading() == Heading.LEFT){
                    playerActor.getCoordinate().setX(playerActor.getCoordinate().getX()+10);
                }
                else if(playerActor.getHeading() == Heading.RIGHT){
                    playerActor.getCoordinate().setX(playerActor.getCoordinate().getX()-10);
                }
                else if(playerActor.getHeading() == Heading.UP){
                    playerActor.getCoordinate().setY(playerActor.getCoordinate().getY()+10);
                }
                else if(playerActor.getHeading() == Heading.DOWN){
                    playerActor.getCoordinate().setY(playerActor.getCoordinate().getY()-10);
                }
            }
        }
        for(Actor c : collisionList){
            launchInteraction(c.getInteraction());
            //Delete actors you collide with
            //TODO: fix this shit
            //ServiceLocator.getActorManager().inactivate(c);
        }
    }

    /**
     * Launches interaction attached to the non-player actor
     * @param interaction interaction of non-player actor
     */
    private void launchInteraction(Interaction interaction){
        if(interaction instanceof CombatInteraction){
            launchCombatInteraction((CombatInteraction) ServiceLocator.getActorManager().getPlayerActor().getInteraction(), (CombatInteraction) interaction);
        }
    }

    private void launchCombatInteraction(CombatInteraction playerInteraction, CombatInteraction enemyInteraction){
        myGameState = GameState.Combat;
        var alliesList = new ArrayList<CombatInteraction>();
        alliesList.add(playerInteraction);
        var enemyList = new ArrayList<CombatInteraction>();
        enemyList.add(enemyInteraction);
        var combatMan = new CombatManager(alliesList, enemyList, new LowestHealthFirstInitiative());
        ServiceLocator.provideCombatManager(combatMan);
        ServiceLocator.getController().setBattleView();
        combatMan.nextTurn();
        //combatMan.nextTurn();
    }

    /**
     * Called by the front end when any event pertaining to the backend is triggered
     * @param e The event
     */
    public void handleInput(GameEvent e){
        if(e instanceof GameKeyEvent){
            handleKeyEvent((GameKeyEvent) e);
        }
        if(e instanceof GameMenuEvent){
            handleMenuEvent((GameMenuEvent) e);
        }
    }

    private void handleMenuEvent(GameMenuEvent e){
        if(myGameState == GameState.Combat){
            System.out.println("menu event triggered");
            var activeCommand = ServiceLocator.getController().getActiveCommands();
            if(activeCommand != null){
                ServiceLocator.getCombatManager().receiveInput(e);
            }
        }

    }

    private void handleKeyEvent(GameKeyEvent e){
        final int DEFAULT_MOVE_AMOUNT = 20;
        var defaultParams = new ArrayList<>();
        defaultParams.add(DEFAULT_MOVE_AMOUNT);
        if(keyMap.containsKey(((KeyEvent) e.getEvent()).getCode())){
            keyMap.get(((KeyEvent) e.getEvent()).getCode()) .execute(defaultParams);
        }
    }


    /**
     * Switches the game state to overworld mode
     */
    public void activateOverWorld(){
        myGameState = GameState.Overworld;
        ServiceLocator.getController().setWorldView();
    }

    /**
     * Gets the current gamestate
     * @return GameState attached to this GameWorld
     */
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
        // check for hit on the upper edge

        return(xIntersects && yIntersects);
    }




}
