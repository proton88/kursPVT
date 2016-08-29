package by.my.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.my.library.command.Command;
import by.my.library.command.exception.CommandException;
import by.my.library.command.utils.SessionAndUser;
import by.my.library.service.ClientService;
import by.my.library.service.LibraryService;
import by.my.library.service.ServiceFactory;

public class AddBook implements Command{
	private static final String TITLE="title";
	private static final String AUTHOR="author";
	private static final String GENRE="genre";
	private static final String PRICE="price";
	private static final String ISBN="isbn";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		if (!SessionAndUser.isSessionAndUser(request, response)){
			return;
		}
		String title=request.getParameter(TITLE);
		String author=request.getParameter(AUTHOR);
		String genre=request.getParameter(GENRE);
		int price=Integer.parseInt(request.getParameter(PRICE));
		long isbn = Long.parseLong(request.getParameter(ISBN));
		///////////////////////////////////////////////////////////////////////////////
		ServiceFactory factory = ServiceFactory.getInstance();
		LibraryService service = factory.getLibraryService();
		///////////////////////////////////////////////////////////////////////////////
		//service.addBook
	}

}
