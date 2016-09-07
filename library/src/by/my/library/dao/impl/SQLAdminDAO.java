package by.my.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.my.library.dao.AdminDAO;
import by.my.library.dao.exception.DAOException;
import by.my.library.dao.impl.pool.ConnectionPool;
import by.my.library.dao.impl.pool.ConnectionPoolException;
import by.my.library.domain.Book;

public class SQLAdminDAO implements AdminDAO{

	@Override
	public boolean addbook(Book book) throws DAOException {
		Connection con=null;
		PreparedStatement st=null;
		
		try{
			con=ConnectionPool.getInstance().takeConnection();
			String sql = "INSERT INTO books(title, author, genre, price, isbn) VALUES(?,?,?,?,?)";
			st=con.prepareStatement(sql);
	        st.setString(1, book.getTitle());
	        st.setString(2, book.getAuthor());
	        st.setString(3, book.getGenre());
	        st.setDouble(4, book.getPrice());
	        st.setInt(5, (int)book.getIsbn());
	        st.executeUpdate();
		}catch (ConnectionPoolException e){throw new DAOException("Error connection pool",e);
	    }catch (SQLException e){throw new DAOException("Wrong sql request",e);
	    }finally {
	            if (st!=null){
	                try {
						st.close();
					} catch (SQLException e) {
						throw new DAOException("Don't close prepare statement",e);
					}
	            }
	            try {
					ConnectionPool.getInstance().releaseConnection(con);
				} catch (ConnectionPoolException e) {
					throw new DAOException("Connection pool don't release connection",e);
				}
	    }
		return true;
	}

	@Override
	public String userBlock(String login) throws DAOException {
		Connection con=null;
		PreparedStatement st=null;
		
		try{
			con=ConnectionPool.getInstance().takeConnection();
			
			String sql = "update users set block=1 where login=?";
			
			st=con.prepareStatement(sql);
	        st.setString(1, login);
	        st.executeUpdate();
		}catch (ConnectionPoolException e){throw new DAOException("Error connection pool",e);
	    }catch (SQLException e){throw new DAOException("Wrong sql request",e);
	    }finally {
	            if (st!=null){
	                try {
						st.close();
					} catch (SQLException e) {
						throw new DAOException("Don't close prepare statement",e);
					}
	            }
	            try {
					ConnectionPool.getInstance().releaseConnection(con);
				} catch (ConnectionPoolException e) {
					throw new DAOException("Connection pool don't release connection",e);
				}
	    }
		return "ok";
	}

	@Override
	public String userUnBlock(String login) throws DAOException {
		Connection con=null;
		PreparedStatement st=null;
		
		try{
			con=ConnectionPool.getInstance().takeConnection();
			
			String sql = "update users set block=null where login=?";
			st=con.prepareStatement(sql);
	        st.setString(1, login);
	        st.executeUpdate();
		}catch (ConnectionPoolException e){throw new DAOException("Error connection pool",e);
	    }catch (SQLException e){throw new DAOException("Wrong sql request",e);
	    }finally {
	            if (st!=null){
	                try {
						st.close();
					} catch (SQLException e) {
						throw new DAOException("Don't close prepare statement",e);
					}
	            }
	            try {
					ConnectionPool.getInstance().releaseConnection(con);
				} catch (ConnectionPoolException e) {
					throw new DAOException("Connection pool don't release connection",e);
				}
	    }
		return "ok";
	}

}
