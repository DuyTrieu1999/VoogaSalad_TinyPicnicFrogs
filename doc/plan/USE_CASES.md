# VoogaSalad Use Cases


## Engine Backend

- PlayerActor collides with an Actor that represents a wall
    - GameWorld detects the collision
    - GameWorld checks Actor's Interaction which is a CollidesInteraction
    - GameWorld sets PlayerActor's location so that its bounding box no longer overlaps the wall

- PlayerActor enters the line of sight of an Actor that represents an enemy

    - PlayerActor collides with the enemy's bounding box, which is larger than the sprite to simulate "line of sight"
    - GameWorld detects the collision and sees that the enemy has a CombatInteraction
    - PlayerActor and enemyActor is passed to CombatManager
    - CombatManager creates Turn objects for each actor. It then arranges the Turn objects in a list
    - CombatManager loops through the turnList, and executes each Turn.

- PlayerActor wins a battle
    - Broadcaster.broadcast() is called
    - GameWorld detects the message and changes the GameMode from Combat to Overworld

- PlayerActor loses a battle
    - Broadcaster.broadcast() is called
    - Gameworld detects the message and changes the GameMode from Combat to Overworld
    - Game is reverted to point before the battle began

- PlayerActor uses an item in combat
    - useItemCommand is selected from the combat menu
    - CombatManager creates an object that displays the Inventory contents
    - Player selects an Item object from the menu
    - The item's bound CommandMove is executed by CombatManager

- PlayerActor walks over a grass Actor, and nothing happens
    - GameWorld detects the collision and triggers the grass's Interaction, which is a NullInteraction
    -  GameWorld does nothing
    
- PlayerActor picks up an item
    - GameWorld detects collection, and sees that the item has an ItemInteraction
    - GameWorld passes the ItemInteraction to the PlayerActor's Inventory

- PlayerActor obtains a key object, which opens a door
    - GameWorld detects collision, and sees that the key has an ItemInteraction
    - GameWorld passes the ItemInteraction to the PlayerActor's Inventory. 
    - The Inventory adds the item Actor to its list of items held
    - Inventory calls Broadcaster.broadcast() with the message specified by the key
    - The door Actor.recieveMessage() handles the message by calling setInactiveState()
    - setInactiveState() calls ActorManager.deactivateActor() on the door
    - ActorManager moves the door Actor animations out of the activeAnimations list

- PlayerActor walks over a grass Actor, and encounters an enemy
    - GameWorld detects the collision, triggers the grass' Interaction which is a Collides interaction
    - GameWorld sets PlayerActor's location so that its bounding box no longer overlaps the wall
    - PlayerActor and enemyActor is passed to CombatManager
    - CombatManager creates Turn objects for each actor. It then arranges the Turn objects in a list
    - CombatManager loops through the turnList, and executes each Turn.


- An Actor is deactivated
    - The Actor's activeState is set to State.INACTIVE
    - ActorManager.deactivateActor() is called
    - The actor's Animation objects are taken out of ActorManager's activeAnimations list
- The PlayerActor who was walking left switches direction to right, thus changing the animation
    - PlayerActor.move() sets PlayerActor's activeAnimation() to rightAnimation
    - PlayerActor.move() calls ActorManager.updateAnimation()
    - updateAnimation() removes the old animation from the activeAnimations list and replaces it with the new PlayerActor.getActiveAnimation()

## Engine Frontend
* Pass in an animation object
    - Backend send in an animation object for a specific actor
    - ViewController add the actor in the screen, and render the scene
    - ActorUI set the bound, location, and animation for the scene
* Player entering a battle scene
    - The StateView switch the ENUM state to BATTLE and load the BattleView
    - BattleView updates and renders the actors for battling scene
    - OverWorldView.animation.stop()
    - After the battle a Splash screen is shown and depending on the outcome of battle go back to OverWorldView
* Player open inventory box
    - buttonHandler from StateView will open the Menu popup. 
    - OverWorldView.animation.stop()
    - BattleView.animation.stop()
    - Item.remove() or Item.add()
* Pause menu
    - The handler key will update with what the users put in through ButtonHandler
    - OverWorldView.animation.stop()
    - BattleView.animation.stop()
* Add splash screen
    - Splash screen is open as indicated by messages from the back end
* Zoom in and out of cameraView
    - ButtonHandler updates the key users want to zoom in and out of camera
    - Camera.zoom(int k)
## Authoring Backend
* Game author decides that an enemy drops an item after it is defeated
    * A background item object is created but not visible or active
    * The game authoring environment adds to the list of messages of an actor
    * A message is created specifying that the item object should be toggled after the enemy dies
    * After the enemy is defeated, the game engine checks for messages and messages the item that the enemy is dead
    * The item object is activated and placed in the user's inventory

* Game author creates and places a new background object (eg. tree, grass)
    * A new actor prototype is created with the sprite, x, y, z locations
    * The author defines a list of messages that should be broadcast when the user interacts with this background object
    * The author creates a new collision interaction that defines whether or not the main character should be able to pass over this background object or not
    * The actor is added to the actor manager

* Game author creates an item that can be used to restore HP
    * The author chooses where to place the potion, how much HP it restores, and its x, y, and z locations
    * A new actor prototype is created and added to the manager
    * A new collectible interaction is created that defines the item as unused, and contains a command
    * This command defines the target stat (HP), amount of HP it restores

* Author Loads a Map and breaks it up into segments
    * A GUI will pop up when a new game is just being created. The author will specify the size of the map (will take in an integer) and there will be another field to specify an n to create an nxn grid. Save in MapManager. GameManager (which contains the MapManager) will act as a controller between the front end of the game authoring, so the fron end will have access to these values as well. 
* Author creates a new Pokemon Prototype: assuming all states and messages are already pre-defined
    * ActorPrototypeManager's addNewPrototype() method is fired with all the data entered by the user
    * New ActorPrototype is created and stored in the ActorPrototype Collection
        * During the ActorPrototype's creation a new instance of the FighterInterraction with user defined information is created
            * During Interraction's creations new Moves get created
* Author places Pokemon prototype on the screen
    * The idea is that in the overworld view, the specific prototype (such as a prototype named 'Hero') will pop up on the side as an option to select and place somewhere on the map. When the prototype is placed on the map, then the front end will handle this mouse event and trigger the event of making that AcotorPrototype into an Actor.
    
* Author creates a new Health Potion Prototype 

   * ActorPrototypeManager's addNewPrototype() method is fired with all the data entered by the user
    * New ActorPrototype is created and stored in the ActorPrototype Collection
        * During the ActorPrototype's creation a new instance of the CollectibleInterraction with user defined information is created
            * During Interraction's creations new Moves get created
            * 
* Author places health potion on the screen
     * The idea is that in the overworld view, the created HealthPotionPrototype (which was just created) will pop up on the side as an option to select and place somewhere on the map. When the prototype is placed on the map, then the front end will handle this mouse event and trigger the event of taking the HealthPotionPrototype and creating a new instantiation of it, to then create a HealthPotionActor, which is what is placed on the map. 
     
* Author deletes Actor from Map
    * Depending on the author's interaction with the GUI/the front end, there will be an event that will trigger an action in ActorManager. The ActorManager will then call deleteActor(Actor ID) in order to remove the specific Actor from the Collection worldActors. 
* Author defines a new Message
    * The Author will create a new Message object and fill out the constructor, which consists of the String the message should say. Then this Message will be added to the Collection globalMessages.
    
* Author Clicks File->Publish Game
    * GameManager runst through ActorManager's collection of Actors and calls serialize() on all of them
* Author deletes ActorPrototype
    * Depending on the author's interaction with the GUI/the front end, there will be an event that will trigger an action in ActorPrototypeManager. The ActorPrototypeManager will then call deleteActorPrototype(ActorPrototype ID) in order to remove the specific ActorPrototype from the Collection AuthorDefinedPrototype. Then, the overworld view will have the prototype disappear from the sidebar. It will no longe be something the author can click on to place on the screen.
* Author changes Pikachu on screen to a Charmander 
    * The corrsponding Actorprototype's generateNewInstance() is fired
    * The Actor's setPrototype() method is fired with the newly generated prototype as a parameter




