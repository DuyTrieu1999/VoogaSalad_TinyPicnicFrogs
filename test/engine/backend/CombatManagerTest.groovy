package engine.backend

import groovy.mock.interceptor.StubFor

class CombatManagerTest extends GroovyTestCase {
    void testRunCombat(){
        def allyCI = new CombatInteraction()
        def enemyCI = new CombatInteraction()


        def mockAI = [getMove: "blah"] as AI

        def combatMan = new CombatManager([allyCI],[enemyCI],mockAI)




    }

}
