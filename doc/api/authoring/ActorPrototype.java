public interface ActorPrototype{

    Collection <Animation> overworldAnimations
    Collection<State> myStastes
    Collection <Interraction> myInterractions
    enum DEFAULT_STATE
    Map <Message, State> messageStateMap

    ActorPrototype clone()
    Collection<Animation> getAnimations()
    Collection <State> getStates()
    State get defaultState()
    Map<Message,State> get messageStateMap()
}