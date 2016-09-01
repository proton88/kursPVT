package by.my.library.service.impl;

import by.my.library.dao.AdminDAO;
import by.my.library.dao.CommonDAO;
import by.my.library.dao.DAOFactory;
import by.my.library.dao.UserDAO;
import by.my.library.dao.exception.DAOException;
import by.my.library.domain.User;
import by.my.library.service.ClientService;
import by.my.library.service.exception.ServiceException;
import by.my.library.service.exception.ServiceLoginPassException;
import by.my.library.service.exception.ServicePassportException;
import by.my.library.service.exception.ServicePasswordException;
import by.my.library.service.exception.ServiceRepeatUserException;
import by.my.library.service.utils.RegularChanges;

public class ClientServiceImpl implements ClientService{

	@Override
	public User logination(String login, String password) throws ServiceException {
		User user=null;
		//валидация
		if(login == null || login.isEmpty() || password == null || password.isEmpty()){
			return user;
		}
		////////////////////////////////////////////////////
		DAOFactory factory = DAOFactory.getInstance();
		CommonDAO commonDAO=factory.getCommonDAO();
		///////////////////////////////////////////////////
		try{
			user=commonDAO.logination(login, password);
		}catch(DAOException e){
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public User registration(String login, String password, String passwordRepeat, String name, String surname,
			String adress, String passportId) throws ServiceException, ServicePasswordException, 
	ServicePassportException, ServiceRepeatUserException, ServiceLoginPassException {
		User user=null;
		//валидация
		if(login == null || login.isEmpty() || password == null || password.isEmpty()){
			throw new ServiceLoginPassException();
		}
		if(!password.equals(passwordRepeat)){
			throw new ServicePasswordException();
		}
		//проверка паспорта через регулярные выражения
		if (!RegularChanges.passportChange(passportId)){
			throw new ServicePassportException();
		}
		
		////////////////////////////////////////////////////
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO=factory.getUserDAO();
		CommonDAO commonDAO=factory.getCommonDAO();
		///////////////////////////////////////////////////
		//если пользователь с таким именем и паролем проходит логинацию, значит он уже есть в БД
		//и значит пользователя с такими же логин-паролем нельзя зарегить
		////////////////////////////////////////////////////////////////////
		try {
			user=commonDAO.logination(login, password);
		} catch (DAOException e1) {
			throw new ServiceException(e1);
		}
		if(user!=null){
			throw new ServiceRepeatUserException();
		}
		user=null;
		//////////////////////////////////////////////////////////////////
		try{
			user=userDAO.registration(login, password, passwordRepeat, name, surname, adress, passportId);
		}catch(DAOException e){
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public String blockUser(String login) throws ServiceException {
		String responseUserBlock="";
		////////////////////////////////////////////////////
		DAOFactory factory = DAOFactory.getInstance();
		AdminDAO adminDAO=factory.getAdminDAO();
		///////////////////////////////////////////////////
		try{
			responseUserBlock=adminDAO.userBlock(login);
		}catch(DAOException e){
			throw new ServiceException("user block error in admin DAO", e);
		}
		return responseUserBlock;
	}

	@Override
	public String unBlockUser(String login) throws ServiceException {
		String responseUserUnBlock="";
		////////////////////////////////////////////////////
		DAOFactory factory = DAOFactory.getInstance();
		AdminDAO adminDAO=factory.getAdminDAO();
		///////////////////////////////////////////////////
		try{
			responseUserUnBlock=adminDAO.userUnBlock(login);
		}catch(DAOException e){
			throw new ServiceException("user unblock error in admin DAO", e);
		}
		return responseUserUnBlock;
	}

}
