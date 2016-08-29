package by.my.library.dao.impl.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ConnectionPool {
	private static final ConnectionPool instance = new ConnectionPool();
	private BlockingQueue<Connection> connectionQueue;//блокирующие очереди - коллекции; очередь свободных конекшн
	private BlockingQueue<Connection> givenAwayConQueue;//очередь занятых конекшн
	
	private String driverName;
	private String url;
	private String user;
	private String password;
	private int poolSize;
	
	private ConnectionPool(){
		
		DBResourceManager dbResourceManager = DBResourceManager.getInstance();
		driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);//читаем значения из пропертис
		url = dbResourceManager.getValue(DBParameter.DB_URL);
		user = dbResourceManager.getValue(DBParameter.DB_USER);
		password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);
		
		try{
			poolSize=Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOL_SIZE));
		}catch(NumberFormatException e){
			poolSize=5;
		}
	}
	
	public static ConnectionPool getInstance(){
		return instance;
	}
	
	public void initPoolData() throws ConnectionPoolException{//инициализирует соединение
		try{
			Class.forName(driverName);
			givenAwayConQueue = new ArrayBlockingQueue<Connection>(poolSize);//потоко безопасная коллекция занятых соед
			connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);//такая очередь не позволит одновременно 
			//2 потокам получить ссылку на одно соединение 
			for(int i=0; i<poolSize; i++){
				Connection con = DriverManager.getConnection(url, user, password);
				connectionQueue.add(con);
			}
		}catch(SQLException e){
			throw new ConnectionPoolException("SQLException in ConnectionPool",e);
		}catch(ClassNotFoundException e){
			throw new ConnectionPoolException("Can't find database driver class",e);
		}
	}
	
	public void dispose(){
		clearConnectionQueue();
	}
	
	private void clearConnectionQueue(){
			closeConnectionQueue(givenAwayConQueue);
			closeConnectionQueue(connectionQueue);
	}
	
	private void closeConnectionQueue(BlockingQueue<Connection> queue){
		for (Connection con : queue) {
			try {
				con.close();
			} catch (SQLException e) {
				// logging
			}
		}
	}
	
	public Connection takeConnection() throws ConnectionPoolException {
		Connection connection = null;
		try {
			connection = connectionQueue.take();
			givenAwayConQueue.add(connection);
		} catch (InterruptedException e) {
			 throw new ConnectionPoolException("Error connecting to the data source.", e);
		}
		return connection;
	}
	
	public void releaseConnection(Connection connection) throws ConnectionPoolException {
		if (connection == null) {
			throw new ConnectionPoolException("User, i haven't connections");
		}

		try {
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			throw new ConnectionPoolException(e);
		}

		givenAwayConQueue.remove(connection);
		connectionQueue.add(connection);

	}
}
