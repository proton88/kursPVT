package by.my.library.dao.impl.pool;

public class ConnectionPoolException extends Exception{
	public ConnectionPoolException(String message){
		super(message);
	}
	
	public ConnectionPoolException(String message, Exception e){
		super(message,e);
	} 
	
	public ConnectionPoolException(Exception e){
		super(e);
	}
}
