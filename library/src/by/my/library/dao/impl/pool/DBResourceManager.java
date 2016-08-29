package by.my.library.dao.impl.pool;

import java.util.ResourceBundle;

public class DBResourceManager {//прочитает все значения из проперти файла
	private static final DBResourceManager instance = new DBResourceManager();
	
	private ResourceBundle bundle;
	
	private DBResourceManager(){
		bundle=ResourceBundle.getBundle("resources.db");
	}
	
	public String getValue(String key){
		return bundle.getString(key);
	}
	
	public static DBResourceManager getInstance(){
		return instance;
	}

} 
