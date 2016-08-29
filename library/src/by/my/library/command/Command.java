package by.my.library.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.my.library.command.exception.CommandException;

public interface Command {
	void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
