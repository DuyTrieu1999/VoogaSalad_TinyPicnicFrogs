package engine.backend;

import engine.backend.Commands.Command;
import engine.backend.Commands.DialogueSelectCommand;

import java.util.*;

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

	public List<Command> getResponsesCommands() {
		List<Command> commands = new ArrayList();
		for(String response : getResponses()) {
			commands.add(new DialogueSelectCommand(response));
		}
		return commands;
	}

	/**
	 * Gets nextDialogue from response
	 * @param response
	 * @return nextDialogue
	 */
	public DialogueTreeNode getChild(String response) {
		return myChildren.get(response);
	}

	/**
	 * @return myDialogue
	 */
	public String getDialogue() {
		return myDialogue;
	}
}

//DialogueTreeNode root = //...
//DialogueTreeNode node = root;

//String dialogue = node.getDialogue();
//while(!dialogue.equals("NULL")) {
//	display(dialogue);
//once user has scrolled through...
//	display(node.getResponses());
//once user has selected response...
//	String response = getUserInput();
//	node = node.getChild(response);
//	String dialogue = node.getDialogue();
//}