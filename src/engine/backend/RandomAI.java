package engine.backend;

import engine.backend.Commands.Command;

import java.util.Random;

public class RandomAI extends AI {
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
