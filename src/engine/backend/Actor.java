package engine.backend;

import engine.frontend.Animation;

public class Actor {
    private Coordinate myCoordinate;
    private int myBoxHeight;
    private int myBoxWidth;
    private ActiveState myActiveState;
    private Animation myActiveAnimation;
    private Interaction myInteraction;

    private int mySpeed;

    //Overworld animations. TODO: put these in some kind of pretty structure
    private Animation myIdleAnimation;
    private Animation myLeftAnimation;
    private Animation myUpAnimation;
    private Animation myDownAnimation;
    private Animation myRightAnimation;

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
        myActiveAnimation = myIdleAnimation;
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
        myCoordinate.setY(myCoordinate.getY()-amt);
        myActiveAnimation = myUpAnimation;
    }

    /**
     * Moves Actor down
     */
    public void moveDown(int amt) {
        myCoordinate.setY(myCoordinate.getY()+amt);
        myActiveAnimation = myDownAnimation;
    }

    /**
     * Moves Actor left
     */
    public void moveLeft(int amt) {
        myCoordinate.setX(myCoordinate.getX()-amt);
        myActiveAnimation = myLeftAnimation;

    }

    /**
     * Moves Actor right
     */
    public void moveRight(int amt) {
        myCoordinate.setX(myCoordinate.getX()+  amt);
        myActiveAnimation = myRightAnimation;
    }

    /**
     * Sets the Actor to the idle position
     */
    public void idle(){
        myActiveAnimation = myIdleAnimation;

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
