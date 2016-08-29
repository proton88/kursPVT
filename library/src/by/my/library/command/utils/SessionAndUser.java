package by.my.library.command.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.my.library.command.exception.CommandException;
import by.my.library.domain.User;

public class SessionAndUser {
	public static boolean isSessionAndUser(HttpServletRequest request, HttpServletResponse response) 
			throws CommandException{
		HttpSession session=request.getSession(false);
		//session=null;
		if (session == null){
			try{
				request.setAttribute("error", "Извините, ваша сессия недействительна, пожалуйста, авторизуйтесь");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}catch(ServletException|IOException e){
				throw new CommandException(e);
			}
			return false;
		}
		User user = (User)session.getAttribute("user");
		
		if(user==null){
			try{
				request.setAttribute("error", "Извините, такого пользователя нет, попробуйте авторизоваться еще раз!");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}catch(ServletException|IOException e){
				throw new CommandException(e);
			}
			return false;
		}
		return true;
		
	}

}
