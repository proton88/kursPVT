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
		
		// ��� ���� �������� ������ � ��������� ����� �� ��
		String error="";
		User user1=null;
		try{
			user1= service.registration(login, password, passwordRepeat, name, surname, adress, passportId);
		}catch (ServicePasswordException e){
			error="��������, �� ��������� ����������� ������";
		}catch (ServiceRepeatUserException e){
			error="��������, ����� ������������ ��� ����";
		}catch (ServicePassportException e){
			error="��������, �� ���������� ����� ��������";
		}catch (ServiceLoginPassException e){
			error="��������, �� �� ����� ����� ��� ������";
		}catch (ServiceException e){
			throw new CommandException(e);
		} 
		
		if (user1!=null){
			HttpSession session = request.getSession(true);
			//session.invalidate();//����� ���� ��� ����� ������������ �������� ������
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
					error="���������� ������������������ ��� ���!";
				}
				request.setAttribute("error", error);
				request.getRequestDispatcher("reg.jsp").forward(request, response);
			}catch(ServletException|IOException e){
				throw new CommandException(e);
			}
		}
		
	}

}
