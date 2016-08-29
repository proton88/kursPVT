package by.my.library.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.my.library.command.Command;
import by.my.library.command.exception.CommandException;
import by.my.library.domain.User;
import by.my.library.service.ClientService;
import by.my.library.service.ServiceFactory;
import by.my.library.service.exception.ServiceException;

public class Logination implements Command{
	private static final String PASSWORD="password";
	private static final String LOGIN="login";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException{
		
		String login=request.getParameter(LOGIN);
		String password=request.getParameter(PASSWORD);
		///////////////////////////////////////////////////////////////////////////////
		ServiceFactory factory = ServiceFactory.getInstance();
		ClientService service = factory.getClientService();
		///////////////////////////////////////////////////////////////////////////////
		
		// ��� ���� �������� ������ � ��������� ����� �� ��
		User user1=null;
		try{
			user1= service.logination(login, password);
		}catch (ServiceException e){
			throw new CommandException(e);
		} 
		// ���� ����� ���, ������ ������������ �����, ��������
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
				request.setAttribute("error", "��������, ������ ������������ ���, ���������� �������������� ��� ���!");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}catch(ServletException|IOException e){
				throw new CommandException(e);
			}
		}
		//����� ���� �������� ���, ���� � ���� ����������� ��� � ������ ������������, ������� �������������
		//�� ��� �������� � ���� ����, ���� ������ ��� �����-�����
		Cookie[] cookies=request.getCookies();
		boolean isLogin=false;
		if (cookies!=null){
			for(Cookie cook:cookies){
				if(cook.getName().equals("login")){
					isLogin=true;
				}
			}
		}
		if(!isLogin){
			Cookie c=new Cookie("login","admin");
			Cookie c2=new Cookie("password","admin");
			response.addCookie(c);
			response.addCookie(c2);
		}
	}
}
