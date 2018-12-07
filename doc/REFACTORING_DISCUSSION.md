Refactoring Discussion
===

## Authors
Allen Qiu (asq3)
Max Bartlett (mmb70)
Michael Glushakov (mg367)

## Refactoring Decisions

### TopMenu.java
- Paddings and insets were converted to well-named constants to avoid magic numbers
- We moved the file menu subitems closer to the place where they are actually used
- Removed useless/wildcard imports

### MessageForm.java
- Window sizes were converted to well-named constants to avoid magic numbers
- Removed useless imports
- Made public classes that didn't need to be public either package-private or private

### Grid.java
- Magic numbers for window sizes were converted to well-named constants
- Removed useless imports
- Returned List objects instead of ArrayList since we didn't require specific ArrayList implementations

### ActorManager.java
- Combined two catch statements into one
- Set pane widths and heights to well-named constants to avoid magic numbers
- Removed useless imports
- Returned List objects instead of ArrayList since we didn't require specific ArrayList implementations

### AuthoringView.java
- Commented out empty methods that have not been implemented yet
- Replaced magic numbers with well-named constants for grid size defaults
- Removed useless imports

### MapMenu.java
- Replaced paddings and other magic numbers with well-named constants
- Removed unused variables
- Made public classes that didn't need to be public either package-private or private

### PopupFactory.java
- Made constants final variables
- Made public classes that didn't need to be public either package-private or private
- Changed public variables to private if needed

### PrototypeForm.java
- Made constants final variables
- Made public classes that didn't need to be public either package-private or private
- Changed public variables to private if needed

### Actor.java
- Fixed code smell relating to reusing variables

### SceneManager.java
- Fixed e.printStacktrace() by using cusom exceptions
- Launch an alert with exception message

### SaveForm.java
- Made constants private and final