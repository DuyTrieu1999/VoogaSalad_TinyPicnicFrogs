package engine.controller;

import engine.backend.*;
import engine.backend.Commands.Command;
import engine.frontend.game_engine_UI.StateView;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Controller {
    private StateView myView;

    public Controller (StateView view) {
        ServiceLocator.provideController(this);
        this.myView = view;
    }

    private Supplier<Collection<AnimationObject>> animationObjectSupplier = () -> ServiceLocator.getActorManager().getAnimationObjects();
    public Collection<AnimationObject> getAnimation () {
//        System.out.println("SIZE"+animationObjectSupplier.get().size());
        return animationObjectSupplier.get(); }

    private Supplier<Actor> playerActorSupplier = () -> ServiceLocator.getActorManager().getPlayerActor();
    public Actor getPlayer () { return playerActorSupplier.get(); }

    private Supplier<List<Command>> activeCommandSupplier = () -> myView.getActiveCommand();
    public List<Command> getActiveCommands () { return activeCommandSupplier.get(); }

    private Consumer<List<Command>> allCommandConsumer = e -> myView.setAllCommand(e);
    public void setAllCommand(List<Command> commands) { allCommandConsumer.accept(commands); }

    private Supplier<GameState> gameStateSupplier = () -> ServiceLocator.getGameWorld().getGameState();
    public GameState getGameState () { return  gameStateSupplier.get(); }

    private Supplier<GameWorld> gameWorldSupplier = () -> ServiceLocator.getGameWorld();
    public GameWorld getGameWorld () { return gameWorldSupplier.get(); }

    private Supplier<List<AnimationObject>> battlePlayerAnimationSupplier = () -> ServiceLocator.getCombatManager().getAlliesIdleAnimation();
    public List<AnimationObject> getBattlePlayerAnimation () { return battlePlayerAnimationSupplier.get(); }

    private Supplier<List<AnimationObject>> battleEnemyAnimationSupplier = () -> ServiceLocator.getCombatManager().getEnemiesIdleAnimation();
    public List<AnimationObject> getBattleEnemyAnimation () { return battleEnemyAnimationSupplier.get(); }

    private Supplier<List<Integer>> alliesBattleHealthSupplier = () -> ServiceLocator.getCombatManager().getAlliesHealth();
    public List<Integer> getalliesHealth () { return alliesBattleHealthSupplier.get(); }

    private Supplier<List<Integer>> enemyBattleHealthSupplier = () -> ServiceLocator.getCombatManager().getEnemiesHealth();
    public List<Integer> getEnemiesHealth () { return alliesBattleHealthSupplier.get(); }

    private Supplier<CombatManager> combatManagerSupplier = () -> ServiceLocator.getCombatManager();
    public CombatManager getCombatManager () { return combatManagerSupplier.get(); }

    public void setWorldView() { myView.setOverWorldView(); }
    public void setBattleView() { myView.setBattleView(); }
}
