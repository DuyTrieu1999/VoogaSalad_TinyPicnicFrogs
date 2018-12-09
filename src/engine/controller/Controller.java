package engine.controller;

import engine.backend.*;
import engine.backend.Commands.Command;
import engine.frontend.game_engine_UI.OverWorld.OverWorldView;
import engine.frontend.game_engine_UI.StateView;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class Controller {
    private StateView myView;

    public Controller (StateView view) {
        ServiceLocator.provideController(this);
        this.myView = view;
    }
    private Supplier<OverWorldView> viewSupplier = () -> myView.getMyView();
    public OverWorldView getOverWorldView () { return viewSupplier.get(); }
    /**
     * supplies the list of AnimationObjects to the front end to animate
     */
    private Supplier<Collection<AnimationObject>> animationObjectSupplier = () -> ServiceLocator.getActorManager().getAnimationObjects();
    public Collection<AnimationObject> getAnimation () { return animationObjectSupplier.get(); }
    /**
     * supplies the the player to the front end
     */
    private Supplier<Actor> playerActorSupplier = () -> ServiceLocator.getActorManager().getPlayerActor();
    public Actor getPlayer () { return playerActorSupplier.get(); }

    /**
     * receive the list of Commands
     */
    private Supplier<List<Command>> allCommandSupplier = () -> ServiceLocator.getCombatManager().getAllyCommandList();
    public List<Command> getAllCommand () { return allCommandSupplier.get(); }

    /**
     * supplies the active GameWorld
     */
    private Supplier<GameWorld> gameWorldSupplier = () -> ServiceLocator.getGameWorld();
    public GameWorld getGameWorld () { return gameWorldSupplier.get(); }
    /**
     * supplies the list of battle animations for the player
     */
    private Supplier<List<AnimationObject>> battlePlayerAnimationSupplier = () -> ServiceLocator.getCombatManager().getAlliesIdleAnimation();
    public List<AnimationObject> getBattlePlayerAnimation () { return battlePlayerAnimationSupplier.get(); }

    /**
     * supplies the list of battle animations for the enemy
     */
    private Supplier<List<AnimationObject>> battleEnemyAnimationSupplier = () -> ServiceLocator.getCombatManager().getEnemiesIdleAnimation();
    public List<AnimationObject> getBattleEnemyAnimation () { return battleEnemyAnimationSupplier.get(); }

    /**
     * supplies the list of health for the player during battle
     */
    private Supplier<List<Integer>> alliesBattleHealthSupplier = () -> ServiceLocator.getCombatManager().getAlliesHealth();
    public List<Integer> getalliesHealth () { return alliesBattleHealthSupplier.get(); }

    /**
     * supplies the list of health for the enemy during battle
     */
    private Supplier<List<Integer>> enemyBattleHealthSupplier = () -> ServiceLocator.getCombatManager().getEnemiesHealth();
    public List<Integer> getEnemiesHealth () { return alliesBattleHealthSupplier.get(); }

    private Supplier<CombatManager> combatManagerSupplier = () -> ServiceLocator.getCombatManager();
    public CombatManager getCombatManager () { return combatManagerSupplier.get(); }

    /**
     * set the WorldView in the front end
     */
    public void setWorldView() { myView.setOverWorldView(); }
    /**
     * set the BattleView in the front end
     */
    public void setBattleView() { myView.setBattleView(); }

    public void addDialogue(String m) { myView.getMyView().addDialogue(m); }
}
