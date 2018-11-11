
# VoogaSalad Plan
---

![](https://i.imgur.com/A5QZBfs.png)


## Names
Christopher Lin (cl349)
Duy Trieu (dvt5)
Brooke Keene (bzk2)
Janice Liu (jl691)
Max Bartlett (mmb70)
Allen Qiu (asq3)
Michael Glushakov (mg367)


### Introduction
> This section describes the problem your team is trying to solve by writing this program, the primary design goals of the project (i.e., where is it most flexible), and the primary architecture of the design (i.e., what is closed and what is open). Describe your chosen game genre and what qualities make it unique that your design will need to support. Discuss the design at a high-level (i.e., without referencing specific classes, data structures, or code).

Our team, Tiny Picnic Frogs, is creating a turn-based Role-Playing Game (RPG). Our main goal is to create a program that allows a user to define the characters, items, and interactions of a game and play their designed RPG. 

The RPG genre is unique because it contains several elements not seen in other game genres. One defining characteristic is the ability for the main character(s) to explore the world and interact with a variety of colorful characters. RPGs are heavily focused on story and dialogue, allowing each player to experience their own unique stories. 

Because of the endless possibilities for characters and journeys in an RPG, one of our primary goals is to create an authoring environment that is flexible enough to allow almost any type of actor or interaction to be defined by the game author. This requires our game authoring environment to be extremely flexible in the information it accepts and the way that information is communicated to the game engine.

The design at a high level includes a game authoring environment, a game engine, a game player, and game data. The game authoring environment allows users to create and modify actors and their interactions, saves these serialized game objects, and then loads them into the game engine. The game engine then takes these user defined characters and interactions, more strictly defines the game loop and logic behind the game, and runs it. The game player acts as the platform through which the user can actually play their game. It takes the logic from the game engine and display the RPGs animations. [Our game data is what allows these pieces to communicate smoothly with each other. It uses the XStream library to store the user-specified character and interaction details from the authoring environment as an XML file that is accessible to our game engine. It also transfers data in the form of [...] from our game engine to our game player. - -> is this right?]



### Overview
> This section serves as a map of your design for other programmers to gain a general understanding of how and why the program was divided up, and how the individual parts work together to provide the desired functionality. Describe specific modules you intend to create, their purpose with regards to the program's functionality, and how they collaborate with each other, focusing specifically on each one's API. Include a picture of how the modules are related (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a UML design program). Discuss specific classes, methods, and data structures, but not individual lines of code.

#### Engine Backend
![](https://i.imgur.com/4Dl8Ksj.png)

* Overworld movement
    * Movement in the overworld is the "default" state. 
    * GameWorld holds a list of actors in the scene, the map of the scene, and handles collision detection.
    * When GameWorld detects a collision with the Player, the Actor's Interaction is triggered
* Interactions
    * We currently plan to support 2 types of interaction; picking up items, and combat. We will add more interactions such as dialogue and cutscenes once these two are implemented
    * Picking up items
        * The Inventory class is responsible for managing the player's inventory. It holds a list of Actors that have ItemInteractions.
    * Combat
        * CombatManager handles combat interactions. It is passed a lists of CombatInteraction objects, each of which holds a list of objects. 
        * CombatManager creates a Turn object for each party involved in the battle. It orders these in a list and executes each one.
        * On a PlayerTurn, the player is prompted to choose one of their available Moves
        * On the AITurn, the Move that is chosen is algorithmically determined. At present, the algorithm is to choose randomly.
* Messaging 
    * The author may want to trigger state changes in Actors globally. For example, if the player defeats a boss, then all the minions become inactive. This is achieved via calls to Broadcaster, which sends Message objects to each actor. Actors have a recieveMessage() method which allows it to change states.
* User Input
    * UserInputHandler accepts key press events, and calls Command objects which are wrappers for method calls. Which Command objects are mapped to which keys is changed dynamically based on which GameMode is currently active.
* Menus
    * Menus are the main way in which the player interacts with the world. The Menu class is completely generic; it simply accepts a list of Commands, and the user can choose which Command to execute. 



#### Game Authoring Environment - Backend
![](https://i.imgur.com/ONmoyBZ.png)

* Actor Class
    * The main components the Actor will contain are location, its state, and will extend Interactions (an abstract class). For example, an Actor that is a background will extend CollideInteraction, which extends Interaction.
    
* Interaction
    * An abstract class. Interactions will have many Messages; these only get triggered by an Actor. Abstract methods include getMessage(). For example, an Actor named Fighter would have a CombatInteraction. The Game Engine portion will perform the logic, and the BroadCaster system will send the appropriate message back to the Fighter in order to change any Fighter (Actor) states 
    * Collision Interraction: extends Interraction. Has a boolean canPass. Used to differentuate passable and impassable background objects
    * CombatInterraction: contains the player fight stats, fight animations, and availible moves. Public methods: getMove(index) and getStats(key)
    * ItemInterraction: contains the collectible item's stats: such as if it's single use or permanent and the Moves of the Interraction. Here moves are the effects an item has on the character using it
* Move implements Command Interface. Contains information about the number of players and player stats it targets, as well as the double amount signifying it's effects. Effect types are either constant or percentage, but potentially could be customized with groovy. Public methods: getNumActors() returns how many actors it affects. bind(Actor[]actors) binds to the actors. execute() calculates the effect on each actor calls methods to change their stats.

* ActorPrototype
    *  For example, if we wanted an EnemyPrototype, all the values (such  as Animation, ID, etc.) will get filled out; a new prototype has been created. Whenever a new Enemy Actor is created (when the the game author drags and drops the prototype onto the map), the GameManager will ask the ActorPrototypeManager to create a new instance of EnemyPrototype(need to pass in an identifier for the correct prototype), and then this new instance of EnemyPrototype will be passed to ActorManager. When ActorManager calls addActor(), this prototype will be part of the list of parameters needed. 

* GameManager
    * The GameManager acts as a connection point for all the other managers. It 'reroutes' information and does not expose methods or parts of the uncode uneccesarily. For example, there would be more dependencies and public methods if ActorManager and ActorPrototypeManager directly asked and received information between. 
    GameManager contains the following:
    
    * ActorManager
        * Will have the ability to get specific Actors and create Actors
    * ActorPrototypeManager
        * Will have the ability to instantiate specific Prototypes, create new Prototypes, and delete Prototypes
    * MessageManager: stores and gives access to the messages created by the author
    * MapManager: keeps track of each square's global location. Will contain the logic to return the actor's global coordinates when actor is created.

* Message: contains a string message entered by the author

#### Game Authoring Environment - Frontend
![](https://i.imgur.com/XgRNf4E.png)

* AuthoringView
    * The AuthoringView acts our main class in the View part of our Model, View, Controller design. This class is responsible for setting up the main scene of our program and adding UI components to the root of our main scene. Most of the methods within this class will be private so that the GUI is closed to outside modifications. To create the GUI elements, the class will create a new instances of various objects. 
    AuthoringView contains the following:
    * ActorMenu
        * the ActorMenu class defines the GUI element that displays the various ActorPrototype objects that the game author has created. From the menu itself, the user will be able to select a specific actor and then place it on the GameMap. 
        ActorMenu contains the following:
            * ActorChoice will define the object that contains a small view of the actor as well as the name associated with the actor.
    * TopMenu
        * the TopMenu class defines the GUI element that displays the main selection menus for our authoring environment. At the moment we envision that we will simply have a dropdown menu that allows a user to choose to create a new prototype or manage their current prototypes.
        TopMenu contains the following:
            * MenuButton will define the object that will define a button for top menu bar. 
            * PopupWindow will be called every time that the game author chooses to create a new prototype. 
    * GameMap
        * the GameMap class defines the GUI element that allows a user to specify a portion of the entire map and then determine the starting position of an actor by placing it on a zoomed in portion of the map. The GameMap class will consist of two UI components that can be considered separately.
        GameMap contains the following:
            * MapSquare will define the object that is the zoomed in area of a specified portion of the entire map
            * GridMap will define the entire map split into the user specified number of cells


### User Interface
> This section describes how the user will interact with your program (keep it simple to start). Describe the overall appearance of program's user interface components and how users interact with these components (especially those specific to your program, i.e., means of input other than menus or toolbars). Include a wireframe of your GUI(s) as well as describing how a game is represented to the designer and what support is provided to make it easy to create a game. Finally, describe any erroneous situations that are reported to the user (i.e., bad input data, empty data, etc.).

The User Interface for our project consists of two main parts: an authoring environment for users to make their games, and a game view to test and play the games. 





### Design Details 
> This section describes each module introduced in the Overview in detail (as well as any other sub-modules that may be needed but are not significant to include in a high-level description of the program). Describe how each module handles specific features given in the assignment specification, what resources it might use, how it collaborates with other modules, and how each could be extended to include additional requirements (from the assignment specification or discussed by your team). Look for opportunities to share APIs between your sub-teams (e.g., authoring and player front ends or authoring and engine back ends) that may themselves be separate modules (like Java and JavaFX are composed of several modules). Note, each sub-team should have its own API for others in the overall team or for new team members to write extensions. Finally, justify the decision to create each module with respect to the design's key goals, principles, and abstractions.

#### Engine Backend
* Overworld Movement
    * The overworld is the "Default State"
    * GameManager requires a background map and Actor objects to be loaded. These will be created by the Authoring application
    * ActorManager
        * Manages list of active Actors
        * provides list of active Animation objects to the frontend
    * UserInputHandler
        * Binds key inputs to Command objects
        * Which Commands are bound to which keys can be specified by the user. They also change when the GameMode changes
    * GameMode
        * Enum that stores the game mode (Combat, Overworld, Pause)
    * Command
        * Encapsulates calls to methods on backend objects
        * the bind() call specifies the specific object instance
* Item interactions
    * Each Actor that is an item contains an ItemInteraction 
    * ItemInteractions contain CombatMovesCommands, which are a subset of Commands that can be called during CombatInteractions. This is extremely flexible and any property can potentially be modified
    * When a player collides with an item, the ItemInteraction is passed to the Player's Inventory
    * The Inventory adds it to its list of items
* Combat interactions
    * A list of CombatInteraction objects for allies and enemies are passed into CombatManager
    * The CombatManager creates a list of Turn objects. These can be ordered in any arbitrary order, so different "initiative" systems can be easily implemented. 
    * On a PlayerTurn, the engine waits for an input from the user, which is provided through a Menu which selects from a list of CombatMoveCommands provided by the CombatInteraction objects
    * On an AITurn, the engine gets a CombatMoveCommand through some algorithm. Different algorithms can be implemented by switching out the CombatAI class
    * Whenever the HP of an Actor reaches 0, it is removed the list
    * When the ally or enemy list reaches 0 Actors, the Combat ends and a OnDefeatMessage or an OnVictoryMessage
* Messaging
    * Global messages are broadcast by the Broadcaster class. 
    * The Broadcaster class loops through all Actors and calls sendMessage() on each one
    * Each Actor is responsible for checking if the sent message is the one that it listens for
* Menus
    * Menus take a list of Commands and displays them. The user can select one to execute
    * The Menu class exposes methods to control cursor movement. These can be called through Command calls
    * This is one of the most extensible components. Any set of Commands can be passed to it, so it can control any element of the engine
    * This represents our "Utility" component, since any game could reasonably use our menuing system.
    

#### Game Authoring Backend

A major component of the Game Authoring Backend is the GameManager. It acts as the controller between the backend of game authoring and its front end. 

The GameManager holds the MapManager, ActorManager, ActorPrototypeManager, and the MessageManager. There will only be one instance of each Manager.

There will be the possibility for multiple ActorPrototypes and Actors. By having their respective managers, keeping track of and controlling these objects will be much easier. 

All the other classes, such as Message, Interactions, Move are all used in the GameManager hierarchy.

GameManager acts as the controller between front end and backend. The GameManager extends the interface IGameManager (named GameManager in APIs folder of the repo), where there is a method called parseData(). This will pass back the author's specifications. For example, if the author filled out an ActorPrototype, then GameManager would then take this information from the frontend and then decide which Manager to pass it to. 

The MapManager needs to be returned to the front end continuously. Each time the map gets updated, the backend needs to be update the information and pass it back to the front end. The GameManager has a getMapManager() method in order to do this.

The data that the author has implemented will then be serialized using XStream. There will be an additional class called Serializer, which will cover this functionality. The data must be saved so that the game engine can then load it. 

When the author presses save game, then there will be a signal sent to the GameManager in order to serialize the data.


#### Game Authoring Environment Frontend
* AuthoringView
    * As mentioned above, the AuthoringView class will encapsulate the creation and placement of all of our GUI elements in the authoring environment for our RPG. We will use a BorderPane to organize all of the elements, which will be created through the makeBorderPane() method. We will then specify the contents of the various areas of the BorderPane by instantiating instances of the various objects defined through the classes ActorMenu, TopMenu, and GameMap by using the other make...() methods. Additionally, our AuthoringView class will contain an instance of the GameManager object, which is what will allow our frontend components to be communicated to the backend.
* ActorMenu
    * The ActorMenu will be an object created as an extension of the existing VBox class in JavaFX. It will encapsulate a List of ActorChoice objects that will each be added to the ActorMenu object with the addNewChoice() method.
    * ActorChoice
        * The ActorChoice object, which extends an HBox, will include the image and name associated with the actor. When the user selects an ActorChoice by clicking it, it will call the setOnClick() method, which will hopefully 
* TopMenu
    * 
    * MenuButton 
    * PopupWindow
* GameMap
    * 
    * MapSquare
    * GridMap

place game elements (i.e., starting positions for a level)
determine the order of advancement (i.e., what level or stage follows the current one)
setup graphical elements (i.e., the level background or images used for each of the monsters)
assign reactions to collisions (i.e., what happens when a monster collides with the player)
assign reactions to interaction (i.e., what happens when a key is pressed or the mouse is moved)
set instructions, splash screen, player setup, level bonuses, etc.
tweak settings (i.e., monster hit-points or projectile speed or animation timing)
load previously created games to be edited again or nodded

#### Design for game engine front end
The highest design for game engine front end is the StateView class, which will determine what game mode (OverWorld, Battle) the game is in. The StateView class collects data about these modes through the Controller class, which will connect the back end and the front end. The Controller class passes informations about Actors, Animations mode, and GameMode to the StateView. 

From this, the StateView can set the state of the game to OverWorld or Battle. The OverWorldView then controls which 
part of the map is rendered in the view, and the ViewController will add and update the Actor and Animation through the animation cycle. Included in the ViewController are the ActorUI and Camera, which is to seperate the views inside. 

The BattleView will update the battle sequence through each frames according to the inputs from players.

We also design a Menu class that will hold the inventories and a SplashScreen class that will control which screen will be displayed. The Menu class is displayed when the player presses a certain key button indicated from the authorization process. The SplashScreen is called through communication via the backend about information regarding winning and certain states of the game.


### Example games
> Describe three example games from your genre in detail that differ significantly. Clearly identify how the functional differences in these games is supported by your design and enabled by your authoring environment. Use these examples to help make concrete the abstractions in your design.

#### Pokemon

One example in the genre is Pokemon. This is the game that our game is most closely modeled after. You have one main game character that can move around maps and and interacts with other characters, items, and background objects. There is a party of different pokemon, as well as an inventory of items.

Our authoring environment would allow the user to designate a main character and define the result of its interactions when it encounters other objects and characters. The user would also be able to select the items that appear on screen and their properties. Regarding both items and characters, the user would be able to place them on their map. Finally, our authoring environment would let the user define a background for the overworld view by creating background object actors.

#### Earthbound

EarthBound is another example of a turn-based role playing game. It differs most from Pokemon due to the way it handles overworld enemies.

Rather than initiating battles through random encounters, EarthBound shows overworld enemies that must be collided with in order to begin a battle. Additionally, there are rules that govern the turn order based on this collision. For example, if the player collides with an enemy from behind (sneaks up on the enemy) then the player attacks first, and vice versa. 

Depending on their levels, enemies will either walk toward the player if their levels are higher than the player's or avoid the player if their levels are lower.

Our authoring environment will allow the user to specify turn ordering, as well as overworld behavior of AI movement.

#### Fire Emblem

The Fire Emblem series is a variation of our turn-based role playing game, although it has some elements that we will not be implementing. Specifically, Fire Emblem 7 (marketed as just Fire Emblem in the US) and Fire Emblem 8 (marketed as Fire Emblem: Sacred Stones in the US) have many elements that can be created inside of our game authoring environment. For example, for every battle/map, there is a party of characters that the user controls. This differs slightly in Fire Emblem since every character can be moved per turn, whereas our game authoring environment will support a single active character at a time, but does not use turns for movement. Fire Emblem also contains square/grid maps, items that can be obtained and used for power ups, and a sophisticated battle system.

Our battle system will be more like the battle system in Fire Emblem rather than Pokemon, since the user will be presented a list of weapons to use, rather than "moves". The weapons will be based off of the item (which has a multiplier that can be set) and a stat of an actor. Our dialogue system will also be more similar to Fire Emblem in which there can be pre-scipted dialogue for the main character (whereas the main character does not speak in Pokemon).

### Design Considerations 
> This section describes any issues which need to be addressed or resolved before attempting to devise a complete design solution. Include any design decisions that each sub-team discussed at length (include pros and cons from all sides of the discussion) as well as any ambiguities, assumptions, or dependencies regarding the program that impact the overall design.

One design decision that was made was the choice of using a messaging system rather than a flag system so that responses to events can be made. Ulimately, we would like some sort of trigger system for one event (action) to trigger another (action). This would be useful for a variety of scenarios. For example, after a boss is beat, to remove a wall or another actor. Initially, we decided we wanted to use a list of flags associated with each actor, and these flags could be decided by the user. For example for an enemy actor, the actor could have flags describing whether or not it has been defeated, it has low HP, etc. Then, after each action, when the map is rerendered, the engine would check a list of flags against a list of events triggered by other events and run those events if needed.

However, after we discussed it more in depth, we decided a messaging system was more useful for this purpose. In this implementation, one action can message another action to tell it to begin. We chose to do this mostly so that each actor can hold it's own state, and so the game does not need to unnecessarily check every flag against every state after every action, which could be slow when we get into the hundreds of objects on a map.

Another design decision was made regarding the different types of actors. Initially, we had an actor interface and decided there would be several types of sub-actors: background objects, fighters, and collectibles. Background objects would do almost nothing except decorate the scene. Collectibles can be picked up, put in the inventory, and used for a certain purpose/stat. Fighters would encompass any other character that can be interacted with. After looking at this design hierarchy, we decided to make the parent actor class contain a list of interactions. The interactions are defined to be either a fight interaction or a collectible interaction. This choice was made so each actor object could contain its own interactions, which makes more sense from an author authoring environment perspective. 


