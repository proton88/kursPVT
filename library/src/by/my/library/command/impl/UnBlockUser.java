package by.my.library.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.my.library.command.Command;
import by.my.library.command.exception.CommandException;
import by.my.library.command.utils.SessionAndUser;
import by.my.library.service.ClientService;
import by.my.library.service.ServiceFactory;
import by.my.library.service.exception.ServiceException;

public class UnBlockUser implements Command{
	private static final String LOGIN="user_unblock";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		if (!SessionAndUser.isSessionAndUser(request, response)){
			return;
		}
		String login=request.getParameter(LOGIN);
		
		///////////////////////////////////////////////////////////////////////////////
		ServiceFactory factory = ServiceFactory.getInstance();
		ClientService service = factory.getClientService();
		///////////////////////////////////////////////////////////////////////////////
		String responseUnBlock;
		try {
			responseUnBlock = service.unBlockUser(login);
		} catch (ServiceException e1) {
			throw new CommandException("Error in service unBlockUser",e1);
		}
		if(responseUnBlock.equals("ok")){
			responseUnBlock="main.response_Unblock";
		}
		request.setAttribute("responseUnBlock", responseUnBlock);
		try {
			request.getRequestDispatcher("WEB-INF/jsp/main.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			throw new CommandException(e);
		}
	}

}
