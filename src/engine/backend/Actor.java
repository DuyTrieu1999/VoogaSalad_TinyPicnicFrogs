package engine.backend;

import authoring.authoring_backend.ActorPrototype;
import authoring.authoring_backend.ObservableActor;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines behavior for Actors
 *
 * @author Max Bartlett (mmb70)
 */

public class Actor {
	private Coordinate myCoordinate;
	private Map<String, Interaction> myInteractionMap;
	private Map<String, Integer> myStatsMap;
	private Map<String, AnimationObject> myAnimationMap;
	private String myName;
	private AnimationObject myActiveAnimation;
	private boolean isPlayerActor;
	private Bounds myBounds;

	/**
	 * Default constructor
	 */
	public Actor() {
	}

	/**
	 * @param prototype ActorPrototype
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param z z coordinate
	 */
	public Actor(ActorPrototype prototype, int x, int y, int z) {
		myCoordinate = new Coordinate(x, y, z);
		myAnimationMap = parseAnimations(prototype.getAnimationMap(),prototype.getSpriteDimensionsMap());
		myInteractionMap = prototype.getInteractionMap();
		myStatsMap = prototype.getMyStats();
		myActiveAnimation = myAnimationMap.get("idle");
		myName = prototype.getName() + x + "-" + y + "-" + z;
		isPlayerActor = prototype.getIsPlayer();
		myBounds = prototype.getBounds();
	}

	/**
	 * @param imagePaths imagePaths for each animation object
	 * @return map of strings and their associated AnimationObjects
	 */
	public Map<String, AnimationObject> parseAnimations(Map<String, String> imagePaths,Map<String,int[]>spriteMap) {
		Map<String, AnimationObject> animations = new HashMap<>();
		for (String s : imagePaths.keySet()) {
			AnimationObject animation = new AnimationObject(s, imagePaths.get(s),spriteMap.get(s)[0],spriteMap.get(s)[1], myCoordinate);
			animations.put(s, animation);
		}
		return animations;
	}

	/**
	 * @return interaction object associated with the first key in the keyset
	 */
	public Interaction getInteraction() {
		String key = (String) myInteractionMap.keySet().toArray()[0];
		return myInteractionMap.get(key);
	}

	/**
	 * @return myBounds
	 */
	public Bounds getBounds() {
		return myBounds;
	}

	/**
	 * @return myActiveAnimation
	 */
	public AnimationObject getActiveAnimation() {
		return myActiveAnimation;
	}

	/**
	 * @return myCoordinate
	 */
	public Coordinate getCoordinate() {
		return myCoordinate;
	}

	/**
	 * @return isPlayerActor
	 */
	public boolean getIsPlayerActor() {
		return isPlayerActor;
	}

	/**
	 * @return ObservableActor with appropriate parameters
	 */
	public ObservableActor getObservableActor() {
		return new ObservableActor(myName, myCoordinate.getX(), myCoordinate.getY(), myCoordinate.getZ(), myActiveAnimation.getAnimationView());
	}

	/**
	 * Moves the Actor in the given direction by the given amount
	 *
	 * @param amt Amount to move the actor
	 * @param dir Direction to move the actor
	 */
	private void move(int amt, String dir) {
		if (dir.equals("top") || dir.equals("left")) {
			amt *= -1;
		}
		if (dir.equals("top") || dir.equals("bottom")) {
			myCoordinate.setY(myCoordinate.getY() + amt);
		} else if (dir.equals("left") || dir.equals("right")) {
			myCoordinate.setX(myCoordinate.getX() + amt);
		}
		myActiveAnimation = myAnimationMap.get(dir);
	}

	/**
	 * Moves the Actor up
	 *
	 * @param amt
	 */
	public void moveUp(int amt) {
		move(amt, "top");
	}

	/**
	 * Moves Actor down
	 *
	 * @param amt
	 */
	public void moveDown(int amt) {
		move(amt, "bottom");
	}

	/**
	 * Moves Actor left
	 *
	 * @param amt
	 */
	public void moveLeft(int amt) {
		move(amt, "left");
	}

	/**
	 * Moves Actor right
	 */
	public void moveRight(int amt) {
		move(amt, "right");
	}

	/**
	 * Sets the Actor to the idle position
	 */
	public void idle() {
		myActiveAnimation = myAnimationMap.get("idle");
	}

	/**
	 * Listens for messages and responds to ones that this actor cares about
	 *
	 * @param m The message sent to the Actor
	 */
	public void receiveMessage(Message m) {

	}

	/**
	 * Handles messages that are not common between all Actors.
	 *
	 * @param m the message
	 */
	protected void receiveCustomMessage(Message m) {

	}

	/**
	 * Used by authoring to serialize the actor
	 */
	public void serialize() {
		//System.out.println(getActiveAnimation().getName());
	}

	/**
	 * Sets the appropriate image for the actor
	 */
	public void setImages() {
		for(AnimationObject a:myAnimationMap.values()){a.setImage();}
		for(Interaction i : myInteractionMap.values()){
			i.setImages();
		}
	}
}
