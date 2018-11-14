package engine.backend;

import engine.frontend.Animation;
import org.json.simple.JSONObject;

import java.util.Map;

public class CombatInteraction extends Interaction{
    Animation myCombatIdleAnimation;
    int myHealth;
    //See Interraction changes
    public CombatInteraction(JSONObject data, Map<String, Message> messages){
        super(data,messages);


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
