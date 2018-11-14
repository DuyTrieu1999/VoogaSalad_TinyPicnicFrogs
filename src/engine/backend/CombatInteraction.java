package engine.backend;

import engine.backend.Commands.CombatMove;
import engine.backend.Commands.Command;
import engine.frontend.Animation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CombatInteraction extends Interaction{
    Animation myCombatIdleAnimation;
    List<Command>commandList;
    int myHealth;
    //See Interraction changes
    public CombatInteraction(JSONObject data, Map<String, Message> messages){
        super(data,messages);
        commandList= new ArrayList<>();
        parseMoves((JSONArray)data.get("moves"));

        //TODO: Test value
        myHealth = 10;
    }

    public void setHealth(int amt){
        myHealth = amt;
    }

    public int getHealth(){
        return myHealth;
    }

    private void parseMoves(JSONArray movesArr){
        for(int i=0;i<movesArr.size();i+=1){
            CombatMove commandMove = new CombatMove((JSONObject)movesArr.get(i));
            commandList.add(commandMove);
            System.out.println(commandMove.getName());
        }
    }
}
