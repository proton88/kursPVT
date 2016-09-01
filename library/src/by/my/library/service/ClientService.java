package by.my.library.service;

import by.my.library.domain.User;
import by.my.library.service.exception.ServiceException;
import by.my.library.service.exception.ServiceLoginPassException;
import by.my.library.service.exception.ServicePassportException;
import by.my.library.service.exception.ServicePasswordException;
import by.my.library.service.exception.ServiceRepeatUserException;

public interface ClientService {
	User logination(String login, String password) throws ServiceException;
	User registration(String login, String password, String passwordRepeat, String name, String surname, 
			String adress, String passportId) throws ServiceException, ServicePasswordException, 
	ServicePassportException, ServiceRepeatUserException, ServiceLoginPassException;
	String blockUser(String login) throws ServiceException;
	String unBlockUser(String login) throws ServiceException;
}
