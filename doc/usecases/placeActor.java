
public class placeActor {


    /**
     * Goal: we are trying to create a hero Actor that is placed at on the map
     * 1. Need to create the prototype
     */


    ActorPrototype hero =
            ActorPrototypeManager.addNewActor(0, myAnimationObject, List < States > myStates, List < Interaction > myInteractions,
                    Map < Message, State > myMessages);

    /**
     * 2. Now the prototype has been created; it will pop up on the GUI as an asset that the author could select, and then click
     * somewhere on the map to place. When the ActorPrototype hero is placed on the map, the following will happen in the
     * backend: (assume myGameManager has already been instantiated)
     */


    ActorManager myActorManager = myGameManager.getActorManager();
    ActorPrototypeManager myAPManager = myGameManager.getActorPrototypeManager();

    ActorPrototype myHeroPrototype = myAPManager.instantiatePrototype(0); //note the ID
    Actor myHero = myActorManager.addNewActor(myHeroProtoType, 5, 40, 40, 0);

/**
 *
 What has been placed on the map at (40, 40, 0) is now an Actor, myHero, which has an ID of 5
 3. So then if the author realized that they placed the wrong actor down on the map, they could delete it.
 The GameManager will notify the ActorManager of the new data (such as the ID) passed from the front end. In this
 example, we are deleting the myHero character that we just placed. The front end will provide an option to delete, so
 in the backend:
 *
 */
        myActorManager.deleteActor(5);
    //the deleteActor() method will also handle deleting that Actor from the worldActors List in the
    //ActorManager class


}