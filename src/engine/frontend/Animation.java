package engine.frontend;

public class Animation {

    /**
     * This is for testing purposes. It might be useful for other purposes but it's probably fine to take out
     */
    String myName;
    public String getName(){
        return myName;
    }

    public Animation(String name){
        myName = name;
    }
    public Animation(String name, String path){
        myName = name;
    }
}
