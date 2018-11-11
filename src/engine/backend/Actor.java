package engine.backend;

import engine.frontend.Animation;

public class Actor {
    Coordinate myCoordinate;
    int myBoxHeight;
    int myBoxWidth;
    ActiveState myActiveState;
    Animation myActiveAnimation;
    Interaction myInteraction;

    Actor() {
        myCoordinate = new Coordinate(0, 0, 0);
        myActiveState = ActiveState.ACTIVE;
        myBoxHeight = 0;
        myBoxWidth = 0;
    }

    public Interaction getInteraction() {
        return myInteraction;
    }

    public int getBoxHeight() {
        return myBoxHeight;
    }

    public int getBoxWidth() {
        return myBoxWidth;
    }

    public Animation getActiveAnimation() {
        return myActiveAnimation;
    }

    public ActiveState getActiveState() {
        return myActiveState;
    }

    public Coordinate getCoordinate() {
        return myCoordinate;
    }


    /**
     * Moves the Actor up
     */
    public void moveUp() {

    }

    /**
     * Moves Actor down
     */
    public void moveDown() {

    }

    /**
     * Moves Actor left
     */
    public void moveLeft() {

    }

    /**
     * Moves Actor right
     */
    public void moveRight() {

    }

    /**
     * Listens for messages and responds to ones that this actor cares about
     *
     * @param m The message sent to the Actor
     */
    public void recieveMessage(Message m){

    }

    private void inactivate(){
        myActiveState = ActiveState.INACTIVE;
    }
    private void activate(){
        myActiveState = ActiveState.ACTIVE;
    }


}
