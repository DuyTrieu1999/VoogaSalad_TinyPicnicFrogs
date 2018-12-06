API Changes
===

## Authors
Christopher Lin (cl349)
Allen Qiu (asq3)
Duy Trieu (dvt5)

## Changes

- PlayerActor is deprecated
    - This is a major change, since a whole class has been deleted
    - This makes the API more friendly to the authoring end, since there is only one type of object (Actor) to worry about
- Command now takes a List of Objects as a parameter
    - This is a major change, because the Command structure is pretty fundamental to the backend
    - This isn't a super elegant solution, but it seems to be necessary

- WorldView is significantly changed
    - A major change in the game engine front end is the super class WorldView that allows us to better support the extended classes
    - WorldView only contains functions updateScene() and addActors(), which are essential for the front end. These changes keep the class simplier and easier to change

- Add in MenuView
    - a MenuView class is created to hold the Menu for player
    - This will be extended to other menu views inside battle view and world view

- ActorMenu has been eliminated
    - ActorManager now takes the place of ActorMenu
    - This is because ActorMenu was essentially useless except to generate a ScrollPane of actors for the GUI, which can be done more easily with the ActorManager
        - This is because the ActorManager keeps track of the actors so that when new ones are added, they can easily be added to the ScrollPane
        - This is difficult because most classes (including PrototypeForm) have access to ActorManager but not ActorMenu
    - This is a pretty major change but the functionality has been merged
    - This is for the better since ActorManager is now more versatile
    - It is unlikely that more changes to ActorManager will be made before project completion

- GameMap has been refactored into the Map, Grid, and Cell classes
    - This is a relatively major fix
    - This is because we realized we need more flexibility with the way the map is displayed
    - We also anticipate this may change in the future because we have not yet implemented the overall map
        - This may lead to more changes
        - The overall map is a miniature map that shows the entire map so that the large map can show a small segment in more detail
    - This is for better so that we can have more granular control over individual cells rather than concentrating all of them into one GameMap

- MapManager was added
    - This was added because we realized RPG games should be able to have more than one map
    - The MapManager contains all of the maps and their names, as well as the implementation needed to create a box that displays the maps
    - It also allows us to delete maps and connect maps via a portal
    - This is also relatively major
    - This is unlikely to change in the future
    - This is for the better because now we can create games with more than one map once the backend supports it

- ActiveMap was added
    - This is a service locator class
    - It was added originally so that we could communicate between the ActorMenu and the GameMap
        - ActorMenu has now been refactor and merged into ActorManager and the GameMap has been split into the Map, Cell, and Grid
        - It now communicates between the ActorManager and the Grid
    - This is a relatively minor change
    - It may be eliminated in the future depending on how interconnected ActorManager and Grid end up in the future