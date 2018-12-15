package by.maria.controller.command;

import by.maria.controller.util.CommandLoader;

import java.util.Map;

public class CommandFactory {

    private static CommandFactory ourInstance = new CommandFactory();
    private Map<String, Command> commandMap;

    public static CommandFactory getInstance() {
        return ourInstance;
    }

    public Command getCommand(String commandType){
        return commandMap.get(commandType);
    }

    private CommandFactory() {
       commandMap= CommandLoader.getInstance().getCommands();
    }
}
