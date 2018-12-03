package engine.backend.Commands;

import engine.frontend.game_engine_UI.OverWorld.OverWorldView;

import java.util.List;

public class OpenMenuCommand extends Command {
    @Override
    public void execute(List<Object> params) {
        ((OverWorldView) myTarget).openMenu();
    }
}
