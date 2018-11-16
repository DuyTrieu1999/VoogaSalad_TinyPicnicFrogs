package authoring.backend;
import authoring.authoring_backend.ActorPrototypeManager;
import authoring.authoring_backend.GameManager;
import engine.backend.Actor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameManagetTester {
    @Test
    public void testMessageCreated() {
        GameManager manager = new GameManager();
        manager.createMessage("testMessage", "This is a test");
        assertEquals("This is a test", manager.getMessage("testMessage").getMessageString());
        assertEquals(null, manager.getMessage("test"));
    }
    @Disabled
    @Test
    public void testActorPrototypeCreated(){
        GameManager manager=new GameManager();
        String jsobBody = "{\n" +
                "  \"name\":\"charizard\",\n" +
                "  \"animations\":[{\"key\":\"default\",\"path\":\"/resource/charizard1.png\"},{\"key\":\"special\",\"path\":\"/resource/charizard2.png\"}],\n" +
                "  \"stats\":[{\"key\":\"health\",\"value\":50},{\"key\":\"damage\",\"value\":5}\n" +
                "  ],\n" +
                "  \"Interactions\": [{\n" +
                "    \"name\":\"fight1\",\n" +
                "    \"type\":\"fight\",\n" +
                "    \"animations\":[{\"key\":\"default\",\"path\":\"/resource/charizard3.png\"},{\"key\":\"special\",\"path\":\"/resource/charizard4.png\"}],\n" +
                "    \"Messages\":[\n" +
                "      {\"key\":\"prototypeVictory\",\"messageKey\":\"onVictory\"},\n" +
                "      {\"key\":\"prototypeDefeat\",\"messageKey\":\"onDefeat\"}\n" +
                "    ],\n" +
                "\n" +
                "    \"moves\":[\n" +
                "      {\n" +
                "        \"name\":\"basic attack\",\n" +
                "        \"targetStat\": \"HP\",\n" +
                "        \"targetActorNumber\":1,\n" +
                "        \"targetActorType\":\"enemy\",\n" +
                "        \"targetValue\":10,\n" +
                "        \"targetType\":\"constant\"\n" +
                "      },{\n" +
                "        \"name\":\"basic regen\",\n" +
                "        \"targetStat\": \"HP\",\n" +
                "        \"targetActorNumber\":1,\n" +
                "        \"targetActorType\":\"friend\",\n" +
                "        \"targetValue\":10,\n" +
                "        \"targetType\":\"percent\"\n" +
                "      },{\n" +
                "        \"name\":\"special attack\",\n" +
                "        \"targetStat\": \"HP\",\n" +
                "        \"targetActorNumber\":3,\n" +
                "        \"targetActorType\":\"enemy\",\n" +
                "        \"targetValue\":10,\n" +
                "        \"targetType\":\"percent\"\n" +
                "      }\n" +
                "    ]\n" +
                "\n" +
                "  }\n" +
                "  ]\n" +
                "}";
        JSONParser parser= new JSONParser();
        try{JSONObject object= (JSONObject)parser.parse(jsobBody);
        manager.createActorPrototype(object);
        manager.createActor("charizard",0,0,0);
        }
        catch (ParseException e){e.printStackTrace();}
    }
}