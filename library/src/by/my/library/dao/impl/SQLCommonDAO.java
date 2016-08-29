package by.my.library.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.my.library.dao.CommonDAO;
import by.my.library.dao.exception.DAOException;
import by.my.library.domain.User;

public class SQLCommonDAO implements CommonDAO{

	@Override
	public User logination(String login, String password) throws DAOException {
		User user=null;
		
		Connection con=null;//������ ��� ���� � ��
	    PreparedStatement st=null; //������, ������� ����� ��������� ������� � ��
	    ResultSet rs=null;//��� �������� � ��
	    try{
	        //Class.forName("org.gjt.mm.mysql.Driver");//������ ����� ������� � ���
	        Class.forName("com.mysql.jdbc.Driver");//������ ����� ������� � ���

	        con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/library", "root", "030588");//������������� ����

	        String sql = "SELECT * FROM users join userdata on users.id=userdata.users_id";//�������������� ������
	        st=con.prepareStatement(sql);//������� �� ���������� sql ����


	        rs=st.executeQuery("SELECT * FROM users join userdata on users.id=userdata.users_id");//� ����� ������� �������
	        while (rs.next()){
	        	if (rs.getString("login").equals(login) && rs.getString("password").equals(password)){
	        		user=new User(login,password,rs.getString("role"), rs.getString("name"), 
	        				rs.getString("surname"), rs.getString("adress"),rs.getString("pasport_id"));
	        	}
	        }
	    }catch (ClassNotFoundException e){throw new DAOException(e);
	    }catch (SQLException e){throw new DAOException(e);
	    }finally {
	        try{
	            if (con!=null){
	                con.close();
	            }
	        }catch (SQLException e){throw new DAOException(e);}
	    }
	    return user;

	}
	

}
