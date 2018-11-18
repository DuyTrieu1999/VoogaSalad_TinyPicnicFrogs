package engine.backend;

import java.util.Comparator;

public class LowestHealthFirstInitiative implements Comparator<Turn> {
    @Override
    public int compare(Turn t1, Turn t2) {
        return t2.getCombatInteraction().getHealth()-t1.getCombatInteraction().getHealth();
    }
}
