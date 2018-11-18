package engine.backend.Commands;

import engine.backend.Actor;
import engine.backend.CombatInteraction;
import javafx.animation.Animation;
import org.json.simple.JSONObject;

import java.util.List;

public class CombatMove extends Command {
    private String stat;
    private int targetActorNum;
    private int targetValue;
    private enum targetActor {ENEMY, FRIEND};
    private enum targetType {CONSTANT,PERCENTAGE};
    private targetType myTargetType;
    private targetActor myTargetActor;
    private Animation myAnimation;



    public CombatMove(JSONObject params){
        super.myName=(String)params.get("name");
        stat=(String)params.get("targetStat");
        targetActorNum=Integer.parseInt(String.valueOf(params.get("targetActorNumber")));
        targetValue=Integer.parseInt(String.valueOf(params.get("targetValue")));
        myTargetType=parseTargetType((String)params.get("targetType"));
        myTargetActor=parseTargetActor((String)params.get("targetActorType"));


    }

    public Animation  getAnimation(){
        return myAnimation;
    }

    @Override
    public void execute(List<Object> params) {
    }
    private targetType parseTargetType(String value){
        if(value.equals("constant"))return targetType.CONSTANT;
        else if(value.equals("percent"))return targetType.PERCENTAGE;
        else throw new IllegalArgumentException("invalid target type");
    }

    private targetActor parseTargetActor(String value){
        if(value.equals("friend"))return targetActor.FRIEND;
        else if(value.equals("enemy"))return targetActor.ENEMY;
        else throw new IllegalArgumentException("invalid target type");
    }

    /**
     * for testing purposes
     */
    @Override
    public void serialize(){
        System.out.println(super.myName+": "+"stat:"+stat+", value: "+targetValue+","+", number:"+targetActorNum+ ", type: "+myTargetType.toString()+", actor: "+myTargetActor.toString());
    }
}
