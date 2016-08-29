package by.my.library.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.my.library.dao.UserDAO;
import by.my.library.dao.exception.DAOException;
import by.my.library.domain.Book;
import by.my.library.domain.User;

public class SQLUserDAO implements UserDAO{

	@Override
	public List<Book> getCatalog() throws DAOException {
		List<Book> catalog=new ArrayList<Book>();
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/library", "root", "030588");
			
			String sql="select * from books";
			ps=con.prepareStatement(sql);
			
			rs=ps.executeQuery(sql);
			while(rs.next()){
				catalog.add(new Book(rs.getInt("id"),rs.getString("title"),rs.getString("author"),
						rs.getString("genre"), rs.getInt("price"),rs.getLong("isbn")));
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
		//catalog=new ArrayList<Book>(); - для получения ошибки пустого каталога
		return catalog;
	}

	@Override
	public Book findBookByTitle(String title) throws DAOException {
		Book book=null;
		List<Book> catalog=getCatalog();
		for(Book b:catalog){
			if(b.getTitle().toUpperCase().equals(title.toUpperCase())){
				book=b;
			}
		}
		//book=new Book(3,"Java","ds","f",41,7666);//проверочная книга, если БД не работает
		catalog=null;//освобождаю память
		return book;
	}

	@Override
	public User registration(String login, String password, String passwordRepeat, String name, String surname,
			String adress, String passportId) throws DAOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new DAOException(e);
		}
		String sql = "INSERT INTO users(login, password, role) VALUES(?,?,?)";//подготовленный запрос
		String sql2 = "INSERT INTO userdata(name, surname, adress, pasport_id) VALUES(?,?,?,?)";
		try(//c ресурсами, автоматически освобождает ресурсы.
	        Connection con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/library", "root", "030588");//устанавливаем соед
	        PreparedStatement st=con.prepareStatement(sql);
			PreparedStatement st2=con.prepareStatement(sql2);){//защищен от выполнения sql атак
	        st.setString(1, login);
	        st.setString(2, password);
	        st.setString(3,"user");
	        st.executeUpdate();
	        st2.setString(1, name);
	        st2.setString(2, surname);
	        st2.setString(3, adress);
	        st2.setString(4, passportId);
	        st2.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		User user= new User(login, password, "user", name, surname, adress, passportId);
		return user;
	}

}
