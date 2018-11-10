package authoring;

public interface GameManager {
    MapManager getMapManager();
    ActorManager getActorManager();
    MessageManger getMessageManager();
    ActorProtoTypeManager getActorPrototypeManager();
}