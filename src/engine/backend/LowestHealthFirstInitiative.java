package engine.backend;

import java.util.Comparator;

public class LowestHealthFirstInitiative implements Comparator<CombatInteraction> {
    @Override
    public int compare(CombatInteraction c1, CombatInteraction c2) {
        return c2.getHealth()-c1.getHealth();
    }
}
