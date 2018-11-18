package engine.backend.Commands;

import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import engine.backend.Actor;
import engine.backend.AnimationObject;
import engine.backend.CombatInteraction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombatMove extends Command {
    private String stat;
    private int targetActorNum;
    private int targetValue;
    private enum targetActor {ENEMY, FRIEND};
    private enum targetType {CONSTANT,PERCENTAGE};
    private targetType myTargetType;
    private targetActor myTargetActor;
    private Map<String, AnimationObject> myAnimationMap;

    public CombatMove(JSONObject params){
        super((String)params.get("name"));
        stat=(String)params.get("targetStat");
        targetActorNum=Integer.parseInt(String.valueOf(params.get("targetActorNumber")));
        targetValue=Integer.parseInt(String.valueOf(params.get("targetValue")));
        myTargetType=parseTargetType((String)params.get("targetType"));
        myTargetActor=parseTargetActor((String)params.get("targetActorType"));
        myAnimationMap=parseAnimationMap((JSONArray)params.get("animations"));


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

    public Map<String,AnimationObject>parseAnimationMap(JSONArray arr){
        Map<String,AnimationObject>animationMap=new HashMap<>();
        for(int i=0;i<arr.size();i+=1){
            JSONObject animation=(JSONObject)arr.get(i);
            animationMap.put((String)animation.get("key"),new AnimationObject((String)animation.get("key"),(String)animation.get("path")));
        }
        return animationMap;
    }

    /**
     * for testing purposes
     */
    @Override
    public void serialize(){
        System.out.println(super.myName+": "+"stat:"+stat+", value: "+targetValue+","+", number:"+targetActorNum+ ", type: "+myTargetType.toString()+", actor: "+myTargetActor.toString());
    }
}
