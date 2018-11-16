package engine.backend;

import javafx.animation.Animation;

public class Actor {
    private Coordinate myCoordinate;
    private int myBoxHeight;
    private int myBoxWidth;
    private ActiveState myActiveState;
    private AnimationObject myActiveAnimation;
    private Interaction myInteraction;

    private int mySpeed;

    //Overworld animations. TODO: put these in some kind of pretty structure
    private AnimationObject myIdleAnimation;
    private AnimationObject myLeftAnimation;
    private AnimationObject myUpAnimation;
    private AnimationObject myDownAnimation;
    private AnimationObject myRightAnimation;
// Eventually needs to be public and take ActorPrototype and X,Y,Z as a parameter
    public Actor() {
        myCoordinate = new Coordinate(0, 0, 0);
        myActiveState = ActiveState.ACTIVE;
        myBoxHeight = 0;
        myBoxWidth = 0;

        //TODO: this is also for testing purposes. Remove when unneeded
        myIdleAnimation = new AnimationObject("idle");
        myLeftAnimation = new AnimationObject("left");
        myRightAnimation = new AnimationObject("right");
        myUpAnimation = new AnimationObject("up");
        myDownAnimation = new AnimationObject("down");
        myActiveAnimation = myIdleAnimation;
    }
    public Actor(ActorPrototype prototype, int x, int y, int z){}

    public Interaction getInteraction() {
        return myInteraction;
    }

    public int getBoxHeight() {
        return myBoxHeight;
    }

    public int getBoxWidth() {
        return myBoxWidth;
    }

    public AnimationObject getActiveAnimation() {
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

    /**
     * Used by authoring to serialize the actor
     */
    public void serialize(){}

}
