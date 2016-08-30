package by.my.library.service;

import java.util.List;

import by.my.library.domain.Book;
import by.my.library.service.exception.ServiceException;

public interface LibraryService {
	List<Book> showBooks() throws ServiceException;
	Book findBookByTitle(String title) throws ServiceException;
	boolean addBook(String title, String author, String genre, String price, String isbn) throws ServiceException;
}
