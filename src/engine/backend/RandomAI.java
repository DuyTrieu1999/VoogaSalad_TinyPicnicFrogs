package engine.backend;

import engine.backend.AI;
import engine.backend.Commands.Command;

import java.util.List;
import java.util.Random;

public class RandomAI extends AI {
    @Override
    public Command getOption() {
        if(myOptions.size() == 0){
            System.out.println("Yeettttt");
            return null;
        }

        Random rn = new Random();
        var optionIndex = rn.nextInt(myOptions.size());
        System.out.println(myOptions.get(optionIndex));
        return myOptions.get(optionIndex);
    }
}
