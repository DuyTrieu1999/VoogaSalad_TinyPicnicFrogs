package engine.backend.Commands;

import java.util.List;

public abstract class Command {
    String myName;
    Object myTarget;

    public abstract void execute(List<Object> params);

    public void bind(Object target){
        myTarget = target;
    }

    public String getName(){
        return myName;
    }

    /**
     * for testing purposese
     */
    public void serialize(){System.out.println(getName());}
}
