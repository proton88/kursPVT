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
import by.my.library.domain.Book;
import by.my.library.domain.User;
import by.my.library.service.LibraryService;
import by.my.library.service.ServiceFactory;
import by.my.library.service.exception.ServiceException;

public class FindByTitle implements Command{
	private static final String TITLE="bookTitle";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		HttpSession session=request.getSession(false);
		//session=null;
		if (session == null){
			try{
				request.setAttribute("error", "Извините, ваша сессия недействительна, пожалуйста, авторизуйтесь");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}catch(ServletException|IOException e){
				throw new CommandException(e);
			}
			return;
		}
		User user = (User)session.getAttribute("user");
		
		if(user==null){
			try{
				request.setAttribute("error", "Извините, такого пользователя нет, попробуйте авторизоваться еще раз!");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}catch(ServletException|IOException e){
				throw new CommandException(e);
			}
			return;
		}
		String title=request.getParameter(TITLE);
		
		///////////////////////////////////////////////////////////////////////////////
		ServiceFactory factory = ServiceFactory.getInstance();
		LibraryService service = factory.getLibraryService();
		///////////////////////////////////////////////////////////////////////////////
		Book book;
		try {
			book = service.findBookByTitle(title);
		} catch (ServiceException e1) {
			throw new CommandException(e1);
		}
		
		if (book==null){
			request.setAttribute("errorpage", "Извините, такой книги нет.");
			
			RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/errorpage.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				throw new CommandException(e);
			}
			return;
		}
		List<Book> catalog =new ArrayList<>();//мне это надо, чтоб не создавать новую jsp страницу, поскольку 
		//текущая страница принимает список
		catalog.add(book);
		request.setAttribute("catalog", catalog);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/show_books.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			throw new CommandException(e);
		}
	}

}
