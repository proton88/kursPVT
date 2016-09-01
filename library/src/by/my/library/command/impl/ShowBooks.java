package by.my.library.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.my.library.command.Command;
import by.my.library.command.exception.CommandException;
import by.my.library.command.utils.SessionAndUser;
import by.my.library.domain.Book;
import by.my.library.domain.User;
import by.my.library.service.ClientService;
import by.my.library.service.LibraryService;
import by.my.library.service.ServiceFactory;
import by.my.library.service.exception.ServiceException;

public class ShowBooks implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException{
		if (!SessionAndUser.isSessionAndUser(request, response)){
			return;
		}
		// запрашивать у сервисов функцию вернуть книги из библиотеки
		///////////////////////////////////////////////////////////////////////////////
		ServiceFactory factory = ServiceFactory.getInstance();
		LibraryService service = factory.getLibraryService();
		///////////////////////////////////////////////////////////////////////////////
		List<Book> catalog=new ArrayList<Book>();
		try {
			catalog = service.showBooks();
		} catch (ServiceException e1) {
			throw new CommandException("Don't execute show books", e1);
		}
	
		if(catalog.isEmpty()){
			request.setAttribute("errorpage", "Извините, книг нет.");
			
			RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/errorpage.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				throw new CommandException(e);
			}
			return;
		}
		request.setAttribute("catalog", catalog);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/show_books.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			throw new CommandException(e);
		}
		
		
	}

}
