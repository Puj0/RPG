package commands;

import commands.concrete_commands.Command;


public class CommandDispatcher {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
        execute();
    }

    public void execute(){
        command.execute();
    }
}
