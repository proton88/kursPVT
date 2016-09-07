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
		
		// тут надо вызывать сервис и считывать юзера из Ѕƒ
		User user1=null;
		try{
			user1= service.logination(login, password);
		}catch (ServiceException e){
			throw new CommandException("Don't get user", e);
		} 
		
		// если юзера нет, значит неправильные логин, пассворд
		if (user1!=null){
			if (user1.getBlock()==1){
				try{
					request.setAttribute("error", "index.error_block");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}catch(ServletException|IOException e){
					throw new CommandException("Don't execute index.jsp",e);
				}
				return;
			}
			HttpSession session = request.getSession(true);
			//session.invalidate();//нужно чтоб при смене пользовател€ мен€лась сесси€
			//session = request.getSession(true);
			session.setAttribute("user", user1);
			RequestDispatcher dispather=request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
			try {
				dispather.forward(request, response);
			} catch (ServletException | IOException e) {
				throw new CommandException("Don't execute main.jsp",e);
			}		
		//session.invalidate();	
		}else{
			try{
				request.setAttribute("error", "index.error_not_user");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}catch(ServletException|IOException e){
				throw new CommandException("Don't execute index.jsp",e);
			}
		}
		//здесь надо помен€ть код, чтоб в куки сохран€лись им€ и пароль пользовател€, который авторизовалс€
		//но дл€ удобства € пока хочу, чтоб всегда был админ-админ
		/*Cookie[] cookies=request.getCookies();
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
		}*/
	}
}
