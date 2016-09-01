package by.my.library.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.my.library.dao.AdminDAO;
import by.my.library.dao.DAOFactory;
import by.my.library.dao.UserDAO;
import by.my.library.dao.exception.DAOException;
import by.my.library.domain.Book;
import by.my.library.service.LibraryService;
import by.my.library.service.exception.ServiceException;
import by.my.library.service.exception.addbook.ServiceEmptyFieldsException;
import by.my.library.service.exception.addbook.ServiceIsbnException;
import by.my.library.service.exception.addbook.ServicePriceException;

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

	@Override
	public boolean addBook(String title, String author, String genre, String price, String isbn) throws 
	ServiceException, ServiceEmptyFieldsException, ServicePriceException, ServiceIsbnException {
		if(title==null || title.isEmpty() || author==null || author.isEmpty() || price==null || price.isEmpty() ||
				genre==null || genre.isEmpty() || isbn==null || isbn.isEmpty()){
			throw new ServiceEmptyFieldsException();
		}
		double priceDouble;
		long isbnLong;
		try{
			priceDouble=Double.parseDouble(price);
		}catch(NumberFormatException e){
			throw new ServicePriceException();
		}
		if (priceDouble<=0){
			throw new ServicePriceException();
		}
		try{
			isbnLong=Long.parseLong(isbn);
		}catch(NumberFormatException e){
			throw new ServiceIsbnException();
		}
		if (isbnLong<=0 || isbnLong>999999999){
			throw new ServiceIsbnException();
		}		
		Book book=new Book(1,title,author,genre,priceDouble,isbnLong);
		////////////////////////////////////////////////////
		DAOFactory factory = DAOFactory.getInstance();
		AdminDAO adminDAO=factory.getAdminDAO();
		///////////////////////////////////////////////////
		try {
			if(!adminDAO.addbook(book)){
				return false;
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return true;
	}

}
