package engine.backend;

public abstract class Command {
    String myName;
    Object myTarget;

    public void execute(){

    }

    public void bind(Object target){
        myTarget = target;
    }

    public String getName(){
        return myName;
    }
}
