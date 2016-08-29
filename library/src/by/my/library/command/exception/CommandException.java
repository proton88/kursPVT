package by.my.library.command.exception;

public class CommandException extends Exception{
	
	public CommandException(String message){
		super(message);
	}
	
	public CommandException(String message, Exception e){
		super(message,e);
	} 
	
	public CommandException(Exception e){
		super(e);
	}

}
