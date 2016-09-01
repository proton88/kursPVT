package by.my.library.dao;

import by.my.library.dao.exception.DAOException;
import by.my.library.domain.Book;

public interface AdminDAO {
	boolean addbook(Book book) throws DAOException;
	String userBlock(String login) throws DAOException;
	String userUnBlock(String login) throws DAOException;

}
