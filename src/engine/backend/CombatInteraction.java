package engine.backend;

import engine.backend.Commands.CombatMove;
import engine.backend.Commands.Command;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * CombatInteraction
 *
 * @author
 */
public class CombatInteraction extends Interaction {
	public static final String MOVES_STRING = "moves";
	public static final String DEFAULT_STRING = "default";
	AnimationObject myCombatIdleAnimation;
	List<Command> commandList;
	int myHealth;

	/**
	 * Default constructor
	 */
	public CombatInteraction() {
		super();
	}

	/**
	 * Constructs a CombatInteraction
	 * @param data JSONObject data
	 * @param messages Map of Strings and their corresponding Messages
	 */
	public CombatInteraction(JSONObject data, Map<String, Message> messages) {
		super(data, messages);
		commandList = new ArrayList<>();
		parseMoves((JSONArray) data.get(MOVES_STRING));
		myCombatIdleAnimation = animationMap.get(DEFAULT_STRING);
		//TODO: Test value
		myHealth = 10;
	}

	/**
	 * @return myCombatIdleAnimation
	 */
	public AnimationObject getCombatIdleAnimation() {
		return myCombatIdleAnimation;
	}

	/**
	 * @return commandList
	 */
	public List<Command> getCommandList() {
		return commandList;
	}

	/**
	 * @return myHealth
	 */
	public int getHealth() {
		return myHealth;
	}

	/**
	 * Sets myHealth
	 * @param val myHealth val
	 */
	public void setHealth(int val) {
		myHealth = val;
	}

	/**
	 * Adds all commandMoves to commandList
	 * @param movesArr array of moves to add to commandList
	 */
	private void parseMoves(JSONArray movesArr) {
		for (int i = 0; i < movesArr.size(); i += 1) {
			CombatMove commandMove = new CombatMove((JSONObject) movesArr.get(i));
			commandList.add(commandMove);
		}
	}

	/**
	 * Serializes the commands
	 */
	@Override
	public void serialize() {
		super.serialize();
		for (Command c : commandList) {
			c.serialize();
		}
	}

	/**
	 * Sets the images for the combat moves
	 */
	@Override
	public void setImages() {
		super.setImages();
		for (Command c : commandList) {
			if (c.getClass().isInstance(CombatMove.class)) {
				CombatMove combatMove = (CombatMove) c;
				combatMove.setImages();
			}
		}
		myCombatIdleAnimation = super.animationMap.get("idle");
	}
}
