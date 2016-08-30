package by.my.library.dao;

import by.my.library.dao.impl.SQLAdminDAO;
import by.my.library.dao.impl.SQLCommonDAO;
import by.my.library.dao.impl.SQLUserDAO;

public class DAOFactory {
	private static final DAOFactory daoFactory = new DAOFactory();
	
	private final CommonDAO commonDAO = new SQLCommonDAO();
	private final UserDAO userDAO = new SQLUserDAO();
	private final AdminDAO adminDAO = new SQLAdminDAO();
	
	private DAOFactory(){}

	public static DAOFactory getInstance() {
		return daoFactory;
	}

	public CommonDAO getCommonDAO() {
		return commonDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public AdminDAO getAdminDAO() {
		return adminDAO;
	}
	

}
