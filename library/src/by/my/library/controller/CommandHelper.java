package by.my.library.controller;

import java.util.HashMap;
import java.util.Map;

import by.my.library.command.Command;
import by.my.library.command.impl.AddBook;
import by.my.library.command.impl.BlockUser;
import by.my.library.command.impl.FindByTitle;
import by.my.library.command.impl.FullTextFind;
import by.my.library.command.impl.Localization;
import by.my.library.command.impl.Logination;
import by.my.library.command.impl.Registration;
import by.my.library.command.impl.ShowBooks;
import by.my.library.command.impl.UnBlockUser;

public class CommandHelper {
	private Map<CommandName, Command> commands = new HashMap<>(); 
	
	CommandHelper(){
		commands.put(CommandName.LOGINATION, new Logination());
		commands.put(CommandName.SHOW_BOOKS, new ShowBooks());
		commands.put(CommandName.FINDBYTITLE, new FindByTitle());
		commands.put(CommandName.REGISTRATION, new Registration());
		commands.put(CommandName.ADD_BOOK, new AddBook());
		commands.put(CommandName.LOCALE, new Localization());
		commands.put(CommandName.BLOCK_USER, new BlockUser());
		commands.put(CommandName.UNBLOCK_USER, new UnBlockUser());
		commands.put(CommandName.FIND, new FullTextFind());
	}
	
	Command getCommand(String name){
		CommandName commandName=CommandName.valueOf(name.toUpperCase());
		Command command = commands.get(commandName);
		return command;
	}

}
