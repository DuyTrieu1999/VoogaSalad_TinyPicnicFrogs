package engine.backend.Commands;

import engine.backend.CombatInteraction;

import java.util.List;

public class ModifyHealthCommand extends Command {

    @Override
    public void execute(List<Object> params) {
        if(params.size() > 1 || params.get(0).getClass() != Integer.class){
            throw new IllegalArgumentException("Requires single Integer as param");
        }
        if(myTarget.getClass() == CombatInteraction.class){
            int currentHealth = ((CombatInteraction) myTarget).getHealth();
            ((CombatInteraction) myTarget).setHealth(currentHealth-(int)params.get(0));
        }
    }
}