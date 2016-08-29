package by.my.library.service.impl;

import java.util.ArrayList;
import java.util.List;


import by.my.library.dao.DAOFactory;
import by.my.library.dao.UserDAO;
import by.my.library.dao.exception.DAOException;
import by.my.library.domain.Book;
import by.my.library.service.LibraryService;
import by.my.library.service.exception.ServiceException;

public class LibraryServiceImpl implements LibraryService{

	@Override
	public List<Book> showBooks() throws ServiceException {
		////////////////////////////////////////////////////
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO=factory.getUserDAO();
		///////////////////////////////////////////////////
		List<Book> catalog = new ArrayList<Book>();
		try {
			catalog=userDAO.getCatalog();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return catalog;
	}

	@Override
	public Book findBookByTitle(String title) throws ServiceException {
		Book book=null;
		//сначала проверим, что нам ввели
		if(title==null || title.isEmpty()){
			return book;
		}
		////////////////////////////////////////////////////
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO=factory.getUserDAO();
		///////////////////////////////////////////////////
		try{
			book=userDAO.findBookByTitle(title);
		}catch(DAOException e){
			throw new ServiceException(e);
		}
		
		return book;
	}

}
