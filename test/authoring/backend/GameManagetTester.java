package authoring.backend;
import authoring.authoring_backend.ActorPrototypeManager;
import authoring.authoring_backend.GameManager;
import engine.backend.Actor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class GameManagetTester {
    @Test
    public void testMessageCreated() {
        GameManager manager = new GameManager();
        manager.createMessage("testMessage", "This is a test");
        assertEquals("This is a test", manager.getMessage("testMessage").getMessageString());
        assertEquals(null, manager.getMessage("test"));
    }

    @Test
    public void testMessageReading() {
        GameManager manager = new GameManager();
        setUp(manager);
        manager.loadMessage("victory","./resources/message-1.xml");
        manager.loadMessage("defeat","./resources/message-2.xml");
        assertEquals("Player won",manager.getMessage("victory").getMessageString());
        assertEquals("Game over",manager.getMessage("defeat").getMessageString());
    }
    @Test
    public void testReadActorNotNull(){
        GameManager manager = new GameManager();
        setUp(manager);
        manager.loadActors("./resources/actors.xml");
        assertNotEquals(null,manager.getActor("charizard"));
    }
    @Test
    public void testReadPrototypeNotNull(){
        GameManager manager = new GameManager();
        setUp(manager);
        manager.loadPrototype("boss","./resources/authoring/prototype-1.xml");
        assertNotEquals(null,manager.getPrototype("boss"));
    }

    private void setUp(GameManager manager){
        manager.createMessage("onVictory","Player won");
        manager.createMessage("onDefeat","Game over");
        manager.saveGame("./resources/","./resources/authoring/");
        JSONObject data = loadJSON();
        if(data!=null){
            manager.createActorPrototype(data);
            manager.createActor("charizard",0,0,0,0,0);
            manager.saveGame("./resources/","./resources/authoring/");
        }
    }
    private  JSONObject loadJSON(){
        JSONParser parser = new JSONParser();
        try{
            JSONObject obj=(JSONObject) parser.parse("{\n" +
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
                    "        \"targetType\":\"constant\",\n" +
                    "        \"animations\":[{\"key\":\"default\",\"path\":\"/resource/charizard5.png\"},{\"key\":\"special\",\"path\":\"/resource/charizard6.png\"}]\n" +
                    "      },{\n" +
                    "        \"name\":\"basic regen\",\n" +
                    "        \"targetStat\": \"HP\",\n" +
                    "        \"targetActorNumber\":1,\n" +
                    "        \"targetActorType\":\"friend\",\n" +
                    "        \"targetValue\":10,\n" +
                    "        \"targetType\":\"percent\",\n" +
                    "        \"animations\":[{\"key\":\"default\",\"path\":\"/resource/charizard5.png\"},{\"key\":\"special\",\"path\":\"/resource/charizard6.png\"}]\n" +
                    "      },{\n" +
                    "        \"name\":\"special attack\",\n" +
                    "        \"targetStat\": \"HP\",\n" +
                    "        \"targetActorNumber\":3,\n" +
                    "        \"targetActorType\":\"enemy\",\n" +
                    "        \"targetValue\":10,\n" +
                    "        \"targetType\":\"percent\",\n" +
                    "        \"animations\":[{\"key\":\"default\",\"path\":\"/resource/charizard5.png\"},{\"key\":\"special\",\"path\":\"/resource/charizard6.png\"}]\n" +
                    "      }\n" +
                    "    ]\n" +
                    "\n" +
                    "  }\n" +
                    "  ]\n" +
                    "}");
            return obj;
        }catch (ParseException e){
            e.printStackTrace();
        }


        return null;
    }
}