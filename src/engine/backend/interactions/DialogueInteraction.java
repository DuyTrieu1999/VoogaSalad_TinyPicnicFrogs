package engine.backend.interactions;

import engine.backend.DialogueTreeNode;

public class DialogueInteraction extends Interaction {
	DialogueTreeNode myRoot;

	public DialogueInteraction(DialogueTreeNode root) {
		myRoot = root;
	}

	public DialogueTreeNode getRoot() {
		return myRoot;
	}
}
