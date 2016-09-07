package by.my.library.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.my.library.dao.UserDAO;
import by.my.library.dao.exception.DAOException;
import by.my.library.dao.impl.pool.ConnectionPool;
import by.my.library.dao.impl.pool.ConnectionPoolException;
import by.my.library.domain.Book;
import by.my.library.domain.User;

public class SQLUserDAO implements UserDAO {

	@Override
	public List<Book> getCatalog() throws DAOException {
		List<Book> catalog = new ArrayList<Book>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = ConnectionPool.getInstance().takeConnection();

			String sql = "select * from books";
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery(sql);
			while (rs.next()) {
				catalog.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"),
						rs.getString("genre"), rs.getDouble("price"), rs.getLong("isbn")));
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error connection pool",e);
		} catch (SQLException e) {
			throw new DAOException("Wrong sql request",e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
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
		// catalog=new ArrayList<Book>(); - для получения ошибки пустого
		// каталога
		return catalog;
	}

	@Override
	public Book findBookByTitle(String title) throws DAOException {
		Book book = null;
		List<Book> catalog = getCatalog();
		for (Book b : catalog) {
			if (b.getTitle().toUpperCase().equals(title.toUpperCase())) {
				book = b;
			}
		}
		// book=new Book(3,"Java","ds","f",41,7666);//проверочная книга, если БД
		// не работает
		return book;
	}

	@Override
	public User registration(String login, String password, String passwordRepeat, String name, String surname,
			String adress, String passportId) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		PreparedStatement st2 = null;

		String sql = "INSERT INTO users(login, password, role) VALUES(?,?,?)";// подготовленный
																				// запрос
		String sql2 = "INSERT INTO userdata(name, surname, adress, pasport_id) VALUES(?,?,?,?)";
		try {
			con = ConnectionPool.getInstance().takeConnection();
			st = con.prepareStatement(sql);
			st2 = con.prepareStatement(sql2);// защищен от выполнения sql атак
			st.setString(1, login);
			st.setString(2, password);
			st.setString(3, "user");
			st.executeUpdate();
			st2.setString(1, name);
			st2.setString(2, surname);
			st2.setString(3, adress);
			st2.setString(4, passportId);
			st2.executeUpdate();
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException("Wrong sql request",e);
		} finally {
			if (st != null || st2 != null) {
				try {
					st.close();
					st2.close();
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
		User user = new User(login, password, "user", name, surname, adress, passportId);
		return user;
	}

	@Override
	public List<Book> find(String textFind) throws DAOException {
		List<Book> catalog = new ArrayList<Book>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = ConnectionPool.getInstance().takeConnection();

			String sql = "SELECT * FROM books WHERE MATCH (title, author, genre) AGAINST (?);";
			ps = con.prepareStatement(sql);
			ps.setString(1, textFind);

			rs = ps.executeQuery();
			while (rs.next()) {
				catalog.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"),
						rs.getString("genre"), rs.getDouble("price"), rs.getLong("isbn")));
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error connection pool",e);
		} catch (SQLException e) {
			throw new DAOException("Wrong sql request",e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
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
		return catalog;
	}

}
