package menu;

import engine.backend.Commands.Command;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class CommandLineMenu extends Menu {

    public CommandLineMenu(List<Command> options){
        super(options);
    }

    @Override
    public List<Command> getChoices() {
        for(int i = 0; i < myOptions.size(); i ++){
            System.out.println(i + ". " + myOptions.get(i).getName());
        }
        Console console = System.console();
        System.out.print("Your choice: ");
        String choice = console.readLine();
        int choiceIndex = Integer.parseInt(choice);
        var choiceList = new ArrayList();
        choiceList.add(myOptions.get(choiceIndex));
        return choiceList;
    }
}
