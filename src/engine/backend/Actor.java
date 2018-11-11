package engine.backend;

import engine.frontend.Animation;

public class Actor {
    Coordinate myCoordinate;
    int myBoxHeight;
    int myBoxWidth;
    ActiveState myActiveState;
    Animation myActiveAnimation;
    Interaction myInteraction;

    int mySpeed;

    //Overworld animations. TODO: put these in some kind of pretty structure
    Animation myIdleAnimation;
    Animation myLeftAnimation;
    Animation myUpAnimation;
    Animation myDownAnimation;
    Animation myRightAnimation;

    Actor() {
        myCoordinate = new Coordinate(0, 0, 0);
        myActiveState = ActiveState.ACTIVE;
        myBoxHeight = 0;
        myBoxWidth = 0;

        //TODO: this is also for testing purposes. Remove when unneeded
        myIdleAnimation = new Animation("idle");
        myLeftAnimation = new Animation("left");
        myRightAnimation = new Animation("right");
        myUpAnimation = new Animation("up");
        myDownAnimation = new Animation("down");
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
    public void moveUp(int amt) {

    }

    /**
     * Moves Actor down
     */
    public void moveDown(int amt) {

    }

    /**
     * Moves Actor left
     */
    public void moveLeft(int amt) {

    }

    /**
     * Moves Actor right
     */
    public void moveRight(int amt) {

    }

    /**
     * Sets the Actor to the idle position
     */
    public void idle(){

    }

    /**
     * Listens for messages and responds to ones that this actor cares about
     *
     * @param m The message sent to the Actor
     */
    public void receiveMessage(Message m){
        if(m.getMessageString() == "InactivateAll"){
            myActiveState = ActiveState.INACTIVE;
        }
        if(m.getMessageString() == "ActivateAll"){
            myActiveState = ActiveState.ACTIVE;
        }
        receiveCustomMessage(m);
    }


    /**
     * Handles messages that are not common between all Actors.
     * @param m the message
     */
    protected void receiveCustomMessage(Message m){

    }

    private void inactivate(){
        myActiveState = ActiveState.INACTIVE;
    }
    private void activate(){
        myActiveState = ActiveState.ACTIVE;
    }


}
