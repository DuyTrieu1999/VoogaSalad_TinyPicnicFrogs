package engine.backend;

import engine.backend.Commands.CombatMove;
import engine.backend.Commands.Command;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CombatInteraction extends Interaction{
    AnimationObject myCombatIdleAnimation;
    List<Command>commandList;
    int myHealth;


    public CombatInteraction(){
        super();
    }

    public AnimationObject getCombatIdleAnimation(){
        return myCombatIdleAnimation;
    }

    public List<Command> getCommandList(){
        return commandList;
    }

    //See Interraction changes
    public CombatInteraction(JSONObject data, Map<String, Message> messages){
        super(data,messages);
        commandList= new ArrayList<>();
        parseMoves((JSONArray)data.get("moves"));
        myCombatIdleAnimation=animationMap.get("default");
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
        }
    }
    @Override
    public void serialize(){
        super.serialize();
        for(Command c:commandList){c.serialize();}
    }
    @Override
    public void setImages(int width, int height){
        super.setImages(width,height);
        for(Command c:commandList){
            if(c.getClass().isInstance(CombatMove.class)){
                CombatMove combatMove=(CombatMove)c;
                combatMove.setImages(width,height);
            }}
        myCombatIdleAnimation=super.animationMap.get("idle");
        System.out.println(myCombatIdleAnimation.getName());
    }
}
