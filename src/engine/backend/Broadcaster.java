package engine.backend;

import java.util.List;

/**
 * Facilitates the broadcasting of messages to actors
 * @author Max Bartlett (mmb70)
 */

public class Broadcaster {
	List<Actor> actorList;

	/**
	 * Constructs an instance of Broadcaster
	 * @param l a list of actors
	 */
	public Broadcaster(List<Actor> l) {
		actorList = l;
	}

	/**
	 * Broadcasts a message to all actors in actorList
	 * @param m the message to broadcast
	 */
	public void broadcast(Message m) {
		for(Actor a : actorList) {
			a.receiveMessage(m);
		}
	}
}
