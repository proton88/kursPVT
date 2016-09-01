package by.my.library.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.my.library.dao.CommonDAO;
import by.my.library.dao.exception.DAOException;
import by.my.library.dao.impl.pool.ConnectionPool;
import by.my.library.dao.impl.pool.ConnectionPoolException;
import by.my.library.domain.User;

public class SQLCommonDAO implements CommonDAO{

	@Override
	public User logination(String login, String password) throws DAOException {
		User user=null;
		int userBlock;
		
		Connection con=null;//������ ��� ���� � ��
	    PreparedStatement st=null; //������, ������� ����� ��������� ������� � ��
	    ResultSet rs=null;//��� �������� � ��
	    try{
			con=ConnectionPool.getInstance().takeConnection();

	        String sql = "SELECT * FROM users join userdata on users.id=userdata.users_id";//�������������� ������
	        st=con.prepareStatement(sql);//������� �� ���������� sql ����


	        rs=st.executeQuery("SELECT * FROM users join userdata on users.id=userdata.users_id");//� ����� ������� �������
	        while (rs.next()){
	        	if (rs.getString("login").equals(login) && rs.getString("password").equals(password)){
	        		user=new User(login,password,rs.getString("role"), rs.getString("name"), 
	        				rs.getString("surname"), rs.getString("adress"),rs.getString("pasport_id"));
	        		user.setBlock(rs.getInt("block"));
	        		
	        	}
	        }
	    }catch (ConnectionPoolException e){throw new DAOException("Don't take connection pool", e);
	    }catch (SQLException e){throw new DAOException("Wrong sql", e);
	    }finally {
            if (st!=null){
                try {
					st.close();
				} catch (SQLException e) {
					throw new DAOException("Don't close prepared statement", e);
				}
            }
            try {
				ConnectionPool.getInstance().releaseConnection(con);
			} catch (ConnectionPoolException e) {
				throw new DAOException("Don't release connection pool",e);
			}
	    }
	    return user;

	}
	

}
