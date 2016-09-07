package by.my.library.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.my.library.command.Command;
import by.my.library.command.exception.CommandException;

public class Localization implements Command{
	private static final String LOCALE="locale";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String locale=null;
		locale=request.getParameter(LOCALE);
		
		request.getSession().setAttribute("locale", locale);
		
		try {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			throw new CommandException("Don't execute index.jsp",e);
		}
	}

}
