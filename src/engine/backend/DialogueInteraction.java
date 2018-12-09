package engine.backend;

public class DialogueInteraction extends Interaction {
	DialogueTreeNode myRoot;

	public DialogueInteraction(DialogueTreeNode root) {
		myRoot = root;
	}

	public DialogueTreeNode getRoot() {
		return myRoot;
	}
}
