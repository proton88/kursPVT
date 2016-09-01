package by.my.library.dao;

import java.util.List;

import by.my.library.dao.exception.DAOException;
import by.my.library.domain.Book;
import by.my.library.domain.User;

public interface UserDAO {
	List<Book> getCatalog() throws DAOException;
	Book findBookByTitle(String title) throws DAOException;
	User registration(String login, String password, String passwordRepeat, String name, String surname,
			String adress, String passportId) throws DAOException;
	List<Book> find(String textFind) throws DAOException;
}
