package by.my.library.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.my.library.command.Command;
import by.my.library.command.exception.CommandException;
import by.my.library.command.utils.SessionAndUser;
import by.my.library.service.LibraryService;
import by.my.library.service.ServiceFactory;
import by.my.library.service.exception.ServiceException;
import by.my.library.service.exception.addbook.ServiceEmptyFieldsException;
import by.my.library.service.exception.addbook.ServiceIsbnException;
import by.my.library.service.exception.addbook.ServicePriceException;

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
		String price=request.getParameter(PRICE);
		String isbn =request.getParameter(ISBN);
		///////////////////////////////////////////////////////////////////////////////
		ServiceFactory factory = ServiceFactory.getInstance();
		LibraryService service = factory.getLibraryService();
		///////////////////////////////////////////////////////////////////////////////
		String message="";
		try {
			if(service.addBook(title, author, genre, price, isbn)){
				message="Книга успешно добавлена";
			}else{
				message="Что-то не так, книга не добавлена";
			}
		}catch (ServiceEmptyFieldsException e){
			message="Заполните, пожалуйста все поля";
		}catch (ServicePriceException e){
			message="Неправильная цена";
		}catch (ServiceIsbnException e){
			message="Неправильный isbn, должно быть 9 цифр";
		}catch (ServiceException e) {
			message="Что-то не так, книга не добавлена";
			throw new CommandException(e);
		}
		request.setAttribute("message", message);
		try {
			request.getRequestDispatcher("WEB-INF/jsp/main.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
