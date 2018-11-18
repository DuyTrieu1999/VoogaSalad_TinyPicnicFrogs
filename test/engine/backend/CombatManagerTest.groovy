package engine.backend

import engine.backend.Commands.Command
import engine.backend.Commands.ModifyHealthCommand
import engine.backend.Commands.TestCommand
import groovy.json.JsonParser
import groovy.mock.interceptor.StubFor
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import sun.security.util.IOUtils

@SuppressWarnings("all")

class CombatManagerTest  {

    static void main(String... args){
        CombatManager myCM
        def mockAlly = [getHealth: 10, getCommandList: [new TestCommand()]] as CombatInteraction
        def mockEnemy = [getHealth: 15, getCommandList: [new TestCommand()]] as CombatInteraction
        myCM = new CombatManager([mockAlly],[mockEnemy], new LowestHealthFirstInitiative())

        myCM.runCombat()
    }










}
