package authoring;

public interface GameManager {
    MapManager getMapManager();
    ActorManager getActorManager();
    MessageManger getMessageManager();
    ActorProtoTypeManager getActorPrototypeManager();

    void parseData(Type t, String data);
    //where Type would specify 'the author filled out an ActorPrototype form'
    //need to specify how the data will be passed in from the front end

    

}