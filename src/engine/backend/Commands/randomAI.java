package engine.backend.Commands;

import engine.backend.AI;

import java.util.List;
import java.util.Random;

public class randomAI extends AI {
    @Override
    public Command getOption() {
        if(myOptions.size() == 0){
            return null;
        }
        Random rn = new Random();
        var optionIndex = rn.nextInt(myOptions.size());
        return myOptions.get(optionIndex);
    }
}
