package by.my.library.dao;

import by.my.library.dao.exception.DAOException;
import by.my.library.domain.User;

public interface CommonDAO {
	User logination(String login, String password) throws DAOException;

}
