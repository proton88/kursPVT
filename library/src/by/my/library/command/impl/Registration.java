package by.my.library.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.my.library.command.Command;
import by.my.library.command.exception.CommandException;
import by.my.library.domain.User;
import by.my.library.service.ClientService;
import by.my.library.service.ServiceFactory;
import by.my.library.service.exception.ServiceException;
import by.my.library.service.exception.ServiceLoginPassException;
import by.my.library.service.exception.ServicePassportException;
import by.my.library.service.exception.ServicePasswordException;
import by.my.library.service.exception.ServiceRepeatUserException;

public class Registration implements Command{
	private static final String PASSWORD="password";
	private static final String LOGIN="login";
	private static final String PASSWORDREPEAT="passwordRepeat";
	private static final String NAME="name";
	private static final String SURNAME="surname";
	private static final String ADRESS="adress";
	private static final String PASSPORTID="passportId";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String login=request.getParameter(LOGIN);
		String password=request.getParameter(PASSWORD);
		String passwordRepeat=request.getParameter(PASSWORDREPEAT);
		String name=request.getParameter(NAME);
		String surname=request.getParameter(SURNAME);
		String adress=request.getParameter(ADRESS);
		String passportId=request.getParameter(PASSPORTID);
		///////////////////////////////////////////////////////////////////////////////
		ServiceFactory factory = ServiceFactory.getInstance();
		ClientService service = factory.getClientService();
		///////////////////////////////////////////////////////////////////////////////
		
		// тут надо вызывать сервис и считывать юзера из БД
		String error="";
		User user1=null;
		try{
			user1= service.registration(login, password, passwordRepeat, name, surname, adress, passportId);
		}catch (ServicePasswordException e){
			error="Извините, не совпадает проверочный пароль";
		}catch (ServiceRepeatUserException e){
			error="Извините, такой пользователь уже есть";
		}catch (ServicePassportException e){
			error="Извините, не правильный номер паспорта";
		}catch (ServiceLoginPassException e){
			error="Извините, вы не ввели логин или пароль";
		}catch (ServiceException e){
			throw new CommandException(e);
		} 
		
		if (user1!=null){
			HttpSession session = request.getSession(true);
			//session.invalidate();//нужно чтоб при смене пользователя менялась сессия
			//session = request.getSession(true);
			session.setAttribute("user", user1);
			RequestDispatcher dispather=request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
			try {
				dispather.forward(request, response);
			} catch (ServletException | IOException e) {
				throw new CommandException(e);
			}		
		//session.invalidate();	
		}else{
			try{
				if (error.isEmpty()){
					error="Попробуйте зарегистрироваться еще раз!";
				}
				request.setAttribute("error", error);
				request.getRequestDispatcher("reg.jsp").forward(request, response);
			}catch(ServletException|IOException e){
				throw new CommandException(e);
			}
		}
		
	}

}
