package engine.backend

class GameWorldTest extends GroovyTestCase {
    GameWorld myGameWorld
    void setUp(){
        myGameWorld = new GameWorld(100, 100)

    }

    void testDetectCollisions() {
        def PAcoord = new Coordinate(0,0,0)
        def PABound = new Bounds(10,10,0,0)
        def testPA = [getInteraction: new CombatInteraction(), getCoordinate: PAcoord, getBounds: PABound] as Actor

        def actorCoord = new Coordinate(2, 2, 0)
        def actorBounds = new Bounds(10,10,0,0)
        def testActor = [getInteraction: new CombatInteraction(), getCoordinate: actorCoord, getBounds: actorBounds] as Actor
        def testAM = new ActorManager([testActor])
        testAM.setPlayerActor(testPA)
        testAM.activate(testActor)
        ServiceLocator.provideActorManager(testAM)
        myGameWorld.detectCollisions()
        assert(myGameWorld.getGameState() == GameState.Combat)


    }
}
