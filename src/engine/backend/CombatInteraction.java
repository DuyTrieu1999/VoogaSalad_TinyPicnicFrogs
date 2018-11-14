package engine.backend;

import engine.frontend.Animation;

public class CombatInteraction {
    Animation myCombatIdleAnimation;
    int myHealth;
    CombatInteraction(){
        //TODO: Test value
        myHealth = 10;
    }

    public void setHealth(int amt){
        myHealth = amt;
    }

    public int getHealth(){
        return myHealth;
    }
}
