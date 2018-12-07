package engine.backend;

import org.json.simple.JSONObject;

import java.util.Map;

/**
 * BackgroundInteraction
 * @author
 */
public class BackgroundInteraction extends Interaction {
	public static final String canPassThroughString = "canPassThrough";
	private boolean canPassThrough;

	/**
	 * Constructs a BackgroundInteraction with given data and messages
	 * @param data JSON data
	 * @param messages Map of Strings and their corresponding Messages
	 */
	public BackgroundInteraction(JSONObject data, Map<String, Message> messages) {
		super(data, messages);
		canPassThrough = (boolean) data.get(canPassThroughString);
	}

	/**
	 * @return canPassThrough
	 */
	protected boolean isCanPassThrough() {
		return canPassThrough;
	}
}
