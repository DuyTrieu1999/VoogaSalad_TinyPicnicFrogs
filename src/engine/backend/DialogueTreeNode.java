package engine.backend;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DialogueTreeNode {
	String myDialogue;
	DialogueTreeNode myParent;
	Map<String, DialogueTreeNode> myChildren;

	/**
	 * @param dialogue
	 */
	public DialogueTreeNode(DialogueTreeNode parent, String dialogue) {
		myParent = parent;
		myDialogue = dialogue;
		myChildren = new HashMap<>();
	}

	/**
	 * Puts a new child in myChildren
	 * @param childDialogue dialogue for child node
	 * @param child child node
	 */
	public void putChild(String childDialogue, DialogueTreeNode child) {
		myChildren.put(childDialogue, child);
	}

	/**
	 * @return myChildren
	 */
	public Map<String, DialogueTreeNode> getMyChildren() {
		return myChildren;
	}

	/**
	 * @return Gets responses
	 */
	public Set<String> getResponses() {
		return myChildren.keySet();
	}

	/**
	 * Gets nextDialogue from response
	 * @param response
	 * @return nextDialogue
	 */
	public String getNextDialogue(String response) {
		String nextDialogue = myChildren.get(response).getDialogue();
		if(myChildren.get(response).equals(null)) {
			return "NULL";
		}
		return nextDialogue;
	}

	/**
	 * @return myDialogue
	 */
	public String getDialogue() {
		return myDialogue;
	}
}
