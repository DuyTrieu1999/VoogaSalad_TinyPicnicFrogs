package engine.backend;

import javafx.animation.Animation;

import java.util.HashMap;
import java.util.Map;

public class Actor {
    private Coordinate myCoordinate;
    private int myBoxHeight;
    private int myBoxWidth;
    private ActiveState myActiveState;
<<<<<<< HEAD
    private Animation myActiveAnimation;
    private Map <String, Interaction> myInteractionMap;
    private Map<String, Integer>myStatsMap;
=======
    private AnimationObject myActiveAnimation;
    private Interaction myInteraction;
>>>>>>> 61c34d51d9ecce6c898aeec9079ba44398b9606f

    private int mySpeed;

    private Map<String, Animation> myAnimationMap;
    //Overworld animations. TODO: put these in some kind of pretty structure
<<<<<<< HEAD


=======
    private AnimationObject myIdleAnimation;
    private AnimationObject myLeftAnimation;
    private AnimationObject myUpAnimation;
    private AnimationObject myDownAnimation;
    private AnimationObject myRightAnimation;
>>>>>>> 61c34d51d9ecce6c898aeec9079ba44398b9606f
// Eventually needs to be public and take ActorPrototype and X,Y,Z as a parameter
    public Actor() {
        myCoordinate = new Coordinate(0, 0, 0);
        myActiveState = ActiveState.ACTIVE;
        myBoxHeight = 0;
        myBoxWidth = 0;
        myAnimationMap=new HashMap<>();
    }
    public Actor(ActorPrototype prototype, int x, int y, int z){
        myCoordinate= new Coordinate(x,y,z);
        myAnimationMap=parseAnimations(prototype.getAnimationMap());
        myInteractionMap=prototype.getInteractionMap();
        myStatsMap= prototype.getMyStats();
        myActiveAnimation=myAnimationMap.get("idle");

<<<<<<< HEAD
=======
        //TODO: this is also for testing purposes. Remove when unneeded
        myIdleAnimation = new AnimationObject("idle");
        myLeftAnimation = new AnimationObject("left");
        myRightAnimation = new AnimationObject("right");
        myUpAnimation = new AnimationObject("up");
        myDownAnimation = new AnimationObject("down");
        myActiveAnimation = myIdleAnimation;
>>>>>>> 61c34d51d9ecce6c898aeec9079ba44398b9606f
    }
    public Map <String,Animation>parseAnimations(Map<String,String>imagePaths){
        Map<String,Animation> animations = new HashMap<>();
        for(String s: imagePaths.keySet()){
            Animation animation= new Animation(s,imagePaths.get(s));
            animations.put(s,animation);
        }
        return animations;
    }

    public Interaction getInteraction(String key) {
        return myInteractionMap.get(key);
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
        myActiveAnimation = myAnimationMap.get("up");
    }

    /**
     * Moves Actor down
     */
    public void moveDown(int amt) {
        myCoordinate.setY(myCoordinate.getY()+amt);
        myActiveAnimation = myAnimationMap.get("down");
    }

    /**
     * Moves Actor left
     */
    public void moveLeft(int amt) {
        myCoordinate.setX(myCoordinate.getX()-amt);
        myActiveAnimation = myAnimationMap.get("left");

    }

    /**
     * Moves Actor right
     */
    public void moveRight(int amt) {
        myCoordinate.setX(myCoordinate.getX()+  amt);
        myActiveAnimation = myAnimationMap.get("right");
    }

    /**
     * Sets the Actor to the idle position
     */
    public void idle(){
        myActiveAnimation = myAnimationMap.get("idle");

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
