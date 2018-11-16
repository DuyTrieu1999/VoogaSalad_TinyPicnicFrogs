API Discussion
===
cl349
rr202

Part 1
---
- What about your API/design is intended to be flexible? 
    - Adding new combat interactions is designed to be the most flexible. Since they are simply lists of Command objects, they can act on basically any property
- How is your API/design encapsulating your implementation decisions?
    - The Command object hides all the method calls
- How is your part linked to other parts of the project?
    - Our part loads objects created by the authoring environment, and the Frontend portion renders all of the animations.
- What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
    - There might be issues if the components of the backend are not initialized in the correct order. To mitigate this, the ServiceProvider class provides default "null" services if they have not already been provided
- Why do you think your API/design is good (also define what your measure of good is)?
    - Our design is flexible and highly encapsulated with a lot of indirection. This means that small issues with implementation can be easily changed without changing larger pieces of the components.

Part 2
---
- Discuss the use cases/issues that you are responsible for: are they descriptive, appropriate, reasonably sized?
    - Yes I think they are
- Estimate how long you think each will take and why. 
    - Collisions: should be pretty fast to do; colission checking is not that hard to do
    - Broadcasting messages: Very easy to do; simply loop through actors and call recieveMessage()
    - Combat: Will take a long time to do; have to implement a lot of things and it takes a lot of collaborators
- What, if anything, makes estimating these tasks uncertain?
    - Classes with collaborators depend on other team mates
- What feature/design problem are you most excited to work on?
    - Combat
- What feature/design problem are you most worried about working on?
    - Combat
- What major feature do you plan to implement this weekend?
    - Loading objects from XML