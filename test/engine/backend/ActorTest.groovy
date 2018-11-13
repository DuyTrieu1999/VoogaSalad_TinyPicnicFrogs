package engine.backend

import groovy.mock.interceptor.MockFor

public class ActorTest extends GroovyTestCase {
    void testMove() {
        def actor = new Actor()
        def oldX = actor.getCoordinate().getX()
        def oldY = actor.getCoordinate().getY()
        def oldZ = actor.getCoordinate().getZ()

        actor.moveDown(10)
        actor.moveRight(10)
        assert actor.getCoordinate().getX() == oldX + 10
        assert actor.getCoordinate().getY() == oldY + 10
        assert actor.getCoordinate().getZ() == oldZ

        actor.moveUp(10)
        actor.moveLeft(10)

        assert actor.getCoordinate().getX() == oldX
        assert actor.getCoordinate().getY() == oldY
    }

    void testMessage(){
        def actor = new Actor()
        assert actor.getActiveState() == ActiveState.ACTIVE
        def message = new Message("InactivateAll")
        actor.receiveMessage(message)
        assert actor.getActiveState() == ActiveState.INACTIVE
    }

    void testMoveAnimations(){
        def actor = new Actor()
        assert actor.getActiveAnimation().getName() == "idle"
        actor.moveUp(10)
        assert actor.getActiveAnimation().getName() == "up"
        actor.moveDown(10)
        assert actor.getActiveAnimation().getName() == "down"
        actor.moveLeft(10)
        assert actor.getActiveAnimation().getName() == "left"
        actor.moveRight(10)
        assert actor.getActiveAnimation().getName() == "right"
        actor.idle()
        assert actor.getActiveAnimation().getName() == "idle"
    }

    void testMoveEdge(){
        def actor = new Actor()
        assert actor.getCoordinate().getX() == 0
        assert actor.getCoordinate().getY() == 0
        actor.moveUp(10)
        assert actor.getCoordinate().getX() == 0
        actor.moveRight(10)
        assert actor.getCoordinate().getY() == 0

        def mockGameWorld = new MockFor(GameWorld)
        mockGameWorld.demand.getMapHeight{10}
        mockGameWorld.demand.getMapWidth{10}
        mockGameWorld.use{
            actor.moveDown(20)
            assert(actor.getCoordinate().getY()) == 0
            actor.moveRight(20)
            assert(actor.getCoordinate().getX()) == 0
        }





    }


}
