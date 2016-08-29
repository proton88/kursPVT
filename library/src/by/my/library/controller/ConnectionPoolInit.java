package by.my.library.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.my.library.dao.impl.pool.ConnectionPool;
import by.my.library.dao.impl.pool.ConnectionPoolException;

public class ConnectionPoolInit implements ServletContextListener {
	private ConnectionPool pool;
    public ConnectionPoolInit() {
        // TODO Auto-generated constructor stub
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
         pool.dispose();
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
         pool=ConnectionPool.getInstance();
         try {
			pool.initPoolData();
		} catch (ConnectionPoolException e) {
			new RuntimeException(); 
		}
    }
	
}
