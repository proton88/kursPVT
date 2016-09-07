package listeners;



import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class ProjectSessionListener implements HttpSessionListener {

    public ProjectSessionListener() {
        // TODO Auto-generated constructor stub
    }

    public void sessionCreated(HttpSessionEvent arg0)  { 
    	HttpSession session = arg0.getSession(); 
    	String id = session.getId();
    	System.out.println("������ "+id+" �������");
    	System.out.println("����� ��������: "+new Date(session.getCreationTime()));
    }

    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	HttpSession session = arg0.getSession();
    	String id = session.getId();
    	//session.setAttribute("message", "����� ������ �������. ");
    	System.out.println("������ "+id+" ���������");
    	System.out.println("����� �����������: "+new Date());
    }
	
}
