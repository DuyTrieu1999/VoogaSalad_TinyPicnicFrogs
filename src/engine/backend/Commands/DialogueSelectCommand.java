package engine.backend.Commands;

import engine.backend.Actor;

import java.util.List;

public class DialogueSelectCommand extends Command{

    String myOption;

    DialogueSelectCommand(String optionName){
        myOption = optionName;
    }

    @Override
    public void execute(List<Object> params) {
        myTarget.getDialogueTreeNode().getChild(myOption);
    }

    @Override
    public void bind(Object target) {
        if(target instanceof  DialogueInteraction){
            super.bind((DialogueInteraction) target);
        }
        else{
            throw new IllegalArgumentException("Not a dialogue interaction");
        }
    }
}
