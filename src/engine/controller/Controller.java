package engine.controller;

import engine.backend.AnimationObject;
import engine.backend.Commands.Command;
import engine.backend.PlayerActor;
import engine.backend.ServiceLocator;
import engine.frontend.game_engine_UI.StateView;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Controller {
    private StateView myView;

    public Controller (StateView view) {
        this.myView = view;
    }

    private Supplier<Collection<AnimationObject>> animationObjectSupplier = () -> ServiceLocator.getActorManager().getAnimationObjects();
    public Collection<AnimationObject> getAnimation () { return animationObjectSupplier.get(); }

    private Supplier<PlayerActor> playerActorSupplier = () -> ServiceLocator.getActorManager().getPlayerActor();
    public PlayerActor getPlayer () { return playerActorSupplier.get(); }

    private Supplier<List<Command>> activeCommandSupplier = () -> myView.getActiveCommand();
    public List<Command> getActiveCommands () { return activeCommandSupplier.get(); }

    private Consumer<List<Command>> allCommandConsumer = e -> myView.setAllCommand(e);
    public void setAllCommand(List<Command> commands) { allCommandConsumer.accept(commands); }
}
