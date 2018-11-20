package authoring.authoring_backend;

import engine.backend.Message;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

public class PlayerPrototype extends ActorPrototype {
    public PlayerPrototype(JSONObject data, List<Map<String, Message>> prototypeMessages){
        super(data, prototypeMessages);
    }

}
