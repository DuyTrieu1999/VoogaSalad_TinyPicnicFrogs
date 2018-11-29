package engine.backend;

import authoring.authoring_backend.ActorPrototype;


import java.util.HashMap;

import java.util.Map;

public class Actor {

    private Coordinate myCoordinate;
    private Map <String, Interaction> myInteractionMap;
    private Map<String, Integer>myStatsMap;
    private Map<String, AnimationObject> myAnimationMap;
    private String myName;
    private AnimationObject myActiveAnimation;

    private boolean isPlayerActor;

    private Bounds myBounds;


    public Actor(){}
    public Actor(ActorPrototype prototype, int x, int y, int z){
        myCoordinate= new Coordinate(x,y,z);
        myAnimationMap=parseAnimations(prototype.getAnimationMap());
        myInteractionMap=prototype.getInteractionMap();
        myStatsMap= prototype.getMyStats();
        myActiveAnimation=myAnimationMap.get("idle");
        isPlayerActor = prototype.getIsPlayer();
        myBounds=prototype.getBounds();
    }

    public Map <String,AnimationObject>parseAnimations(Map<String,String>imagePaths){
        Map<String,AnimationObject> animations = new HashMap<>();
        for(String s: imagePaths.keySet()){
            AnimationObject animation= new AnimationObject(s,imagePaths.get(s));
            animations.put(s,animation);
        }
        return animations;
    }

    public Interaction getInteraction(String key) {
        return myInteractionMap.get(key);
    }

    /**
     *
     * @return interraction object associated with the first key in the keyset
     */
    public Interaction getInteraction(){
        String key =(String)myInteractionMap.keySet().toArray()[0];
        return myInteractionMap.get(key);
    }

    public Bounds getBounds(){
        return myBounds;
    }

    public AnimationObject getActiveAnimation() {
        return myActiveAnimation;
    }

    public Coordinate getCoordinate() {
        return myCoordinate;
    }

    public boolean getIsPlayerActor() {return isPlayerActor;}

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

    }


    /**
     * Handles messages that are not common between all Actors.
     * @param m the message
     */
    protected void receiveCustomMessage(Message m){

    }


    /**
     * Used by authoring to serialize the actor
     */
    public void serialize(){

    }

}
