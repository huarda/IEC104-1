package com.flying.jdbc.util;

import com.flying.jdbc.db.Exception.ExceptionFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * @author zdf
 * @version 1.0
 * 
 */
public class DBConnection {
	
	//属性区
	
	private String _driver = null;
	private String _url = null;
	private String _username = null;
	private String _password = null;
	
	//数据库类型
	private int dbType;
	
	private Connection connection = null;
	private boolean available = false;
	
	//方法区
	public DBConnection()
	{
		try{
			//加载properties信息
			InputStream in =  DBConnection.class.getClassLoader().getResourceAsStream("database.properties");
			Properties properties = new Properties();		
			try {
				properties.load(in);
				String driverName = properties.getProperty("driver");
				String UserName = properties.getProperty("username");
				String UserPwd = properties.getProperty("password");
				String url = properties.getProperty("url");
								
				//获取数据库类型
				this.dbType = DBType.getDBType(driverName);
				
				Class.forName(driverName);
				
				this.connection = DriverManager.getConnection(url,UserName,UserPwd);
	 		} catch (Exception e) {
	 			e.printStackTrace();
	 			throw new Exception("运行jdbcUtil出错！");
			}  
			
			
			
		}catch(SQLException sqle){
			
			throw ExceptionFactory.getException(dbType, sqle.toString() +
					"\n" + "SQL State: " +
					sqle.getSQLState(), sqle);
			
		}catch (ClassNotFoundException cnfe){
			
			throw ExceptionFactory.getException(dbType, cnfe.getMessage());
			
		}catch (Exception e){
			
			throw ExceptionFactory.getException(dbType, e.getMessage());
			
		}
	}
	/**
	 * DBConnection构造函数
	 * @param String connectionString :
	 * 	数据库连接参数，格式：url;user;passworld.
	 * 	例如：jdbc:oracle:thin:@localhost:1521:db;system;manager
	 * @throws Exception
	 */
	public DBConnection(String connectionString)
	{
		
		String[] _sa = connectionString.split(";");
		if(_sa.length !=3){
			//抛出异常，错误连接
			System.out.println("错误的链接参数！");
		}
		
		this._url = _sa[0];
		this._username = _sa[1];
		this._password = _sa[2];
		
		//启动驱动
		connect();
	}
	
	public DBConnection(String url,String username,String password)
	{
		this._url = url;
		this._username = username;
		this._password = password;
		
		//启动驱动
		connect();
	}
	
	//连接数据库
	private void connect()
	{
		//取得默认驱动
		this._driver = DBType.getDefaultDriver(this._url);
		//取得默认数据库类型
		this.dbType = DBType.getDBType(this._driver);
		try{
		if(_driver != null)
			Class.forName(_driver);

			this.connection = DriverManager.getConnection(_url, _username,_password);
		}catch(SQLException sqle){
			
			throw ExceptionFactory.getException(dbType, sqle.toString() +
					"\n" + "SQL State: " +
					sqle.getSQLState(), sqle);
			
		}catch (ClassNotFoundException cnfe){
			
			throw ExceptionFactory.getException(dbType, cnfe.getMessage());
			
		}catch (Exception e){
			
			throw ExceptionFactory.getException(dbType, e.getMessage());
			
		}
	}
	/**
	 * 关闭数据库连接
	 * @throws Exception
	 */
	public void Close()
	{
		try {
			if(!connection.isClosed()&&connection.getAutoCommit())
			{
				this.connection.close();
			}
		} catch (SQLException e) {
			throw ExceptionFactory.getException(dbType, e.getMessage());
		}
	}
	
	/**
	 * 从OracleConnection类创建事务处理的OracleTransaction类实例
	 * @return
	 * @throws Exception
	 */
	public DBTransaction BeginTransaction()
	{
		DBTransaction tran = null;
		try {
			tran = new DBTransaction(this);
		} catch (Exception e) {
			throw ExceptionFactory.getException(dbType, e.getMessage());
		}
		return tran;
	}
	
	/**
	 * 获取java.sql.Connection对象
	 * @return java.sql.Connection对象
	 */
	public Connection GetConnection()
	{
		return this.connection;
	}
	
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public boolean isClose() throws SQLException{
		return this.connection.isClosed();
	}

}