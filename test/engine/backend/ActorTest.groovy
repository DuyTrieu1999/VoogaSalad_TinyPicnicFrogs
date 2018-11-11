package engine.backend

public class ActorTest extends GroovyTestCase {
    void testMove() {
        def actor = new Actor()
        def oldX = actor.getCoordinate().getX()
        def oldY = actor.getCoordinate().getY()
        def oldZ = actor.getCoordinate().getZ()

        actor.moveUp(10)
        actor.moveDown(10)
        actor.moveLeft(10)
        actor.moveRight(10)

        assert actor.getCoordinate().getX() == oldX + 10
        assert actor.getCoordinate().getY() == oldY + 10
        assert actor.getCoordinate().getZ() == oldZ
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


}
