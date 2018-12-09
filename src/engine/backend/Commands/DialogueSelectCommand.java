package engine.backend.Commands;

import engine.backend.Actor;
import engine.backend.interactions.DialogueInteraction;


import java.util.List;

public class DialogueSelectCommand extends Command{

    String myOption;

    public DialogueSelectCommand(String optionName){
        myOption = optionName;
    }

    @Override
    public void execute(List<Object> params) {
        ((DialogueInteraction) myTarget).getRoot().getChild(myOption);
    }

    @Override
    public void bind(Object target) {
        if(target instanceof DialogueInteraction){
            super.bind((DialogueInteraction) target);
        }
        else{
            throw new IllegalArgumentException("Not a dialogue interaction");
        }
    }
}
