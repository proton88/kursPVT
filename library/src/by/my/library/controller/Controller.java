package by.my.library.controller;

import java.io.IOException;

import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.my.library.command.Command;
import by.my.library.command.exception.CommandException;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String COMMAND="command";
       
    private final CommandHelper commandHelper=new CommandHelper();
    
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletContext=getServletContext();//контекст приложения
		ServletConfig servletConfig=getServletConfig();//контекст сервлета
		Enumeration<?> initName=servletConfig.getInitParameterNames();
		while(initName.hasMoreElements()){
			String name=initName.nextElement().toString();
			String value=servletConfig.getInitParameter(name);
			System.out.println(name+" - "+value);
		}
		System.out.println("Author: "+servletContext.getInitParameter("author"));
		
		
		HttpSession session = null;
		session=request.getSession(false);
		
		String encodeURL=response.encodeURL(request.getContextPath())+"/Controller";
		request.setAttribute("encodeURL", encodeURL);
		
		if(session==null){
			
			System.out.println("Session wasn't construct");
		}else{
			System.out.println(session.getId());
		}
		
		String name=request.getParameter(COMMAND);
		Command command=commandHelper.getCommand(name);
		try {
			command.execute(request, response);
		} catch (CommandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
