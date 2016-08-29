package by.my.library.service;

import by.my.library.service.impl.ClientServiceImpl;
import by.my.library.service.impl.LibraryServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory factory=new ServiceFactory();
	
	private final ClientService clientService = new ClientServiceImpl();
	private final LibraryService libraryService = new LibraryServiceImpl();
	
	private ServiceFactory(){}
	
	public static ServiceFactory getInstance() {return factory;}

	public ClientService getClientService() {
		return clientService;
	}

	public LibraryService getLibraryService() {
		return libraryService;
	}
	
	

}
