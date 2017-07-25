package com.flying.jdbc.util;

import com.flying.jdbc.db.Exception.ExceptionFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author zdf
 * @version 1.0
 * 
 */
public class DBConnectionPool {
	
	//属性区
	private String _driver = null;
	private String _url = null;
	private String _username = null;
	private String _password = null;
	
	//数据库类型
	private int dbType;
	
	/** Connection pool */
	private List<DBConnection> conPool = null;
	//初始容量
	private int capacity = 20;
	//最大容量
	private final int max = 150;
	//扩展限度
	private final int limit = 10;
	
	//构造方法区
	public DBConnectionPool(int capacity)
	{
		//数据库连接池的容量
		if(capacity >0 && capacity <= this.max){
			this.capacity = capacity;
		}else if(capacity > this.max){
			this.capacity = this.max;
		}else{
			this.capacity = 20;
		}
		//初始化
		conPool = new ArrayList<DBConnection>();
		
		//获取数据库类型
		this.dbType = DBType.getDBType(new DBConnection());
		//向连接池中添加连接
		for(int i = 0; i<this.capacity;i++){
			DBConnection conn = new DBConnection();
			conn.setAvailable(true);
			conPool.add(conn);
		}
	}
	/**
	 * DBConnection构造函数
	 * @param String connectionString :
	 * 	数据库连接参数，格式：url;user;passworld.
	 * 	例如：jdbc:oracle:thin:@localhost:1521:db;system;manager
	 * @throws Exception
	 */
	public DBConnectionPool(int capacity,String connectionString)
	{
		//数据库连接池的容量
		if(capacity >0){
			this.capacity = capacity;
		}else{
			this.capacity = 20;
		}
		//初始化
		conPool = new ArrayList<DBConnection>();
		
		//数据库连接参数
		String[] _sa = connectionString.split(";");
		if(_sa.length !=3){
			//抛出异常，错误连接
			System.out.println("错误的链接参数！");
		}
		
		this._url = _sa[0];
		this._username = _sa[1];
		this._password = _sa[2];
		//获取数据库类型
		this.dbType = DBType.getDBType(this._url);
		
		//向连接池中添加连接
		addConnectionsToPool();
	}
	
	public DBConnectionPool(int capacity,String url,String username,String password)
	{
		//数据库连接池的容量
		if(capacity >0){
			this.capacity = capacity;
		}else{
			this.capacity = 20;
		}
		//初始化
		conPool = new ArrayList<DBConnection>();
		
		//数据库连接参数
		this._url = url;
		this._username = username;
		this._password = password;
		
		//获取数据库类型
		this.dbType = DBType.getDBType(this._url);
		
		//向连接池中添加连接
		addConnectionsToPool();
	}
	
	private void addConnectionsToPool(){
		//向连接池中添加连接
		for(int i = 0; i<this.capacity;i++){
			DBConnection conn = new DBConnection(_url,_username,_password);
			conn.setAvailable(true);
			conPool.add(conn);
		}
	}
	
	//方法区
	public int getNumConnInPool(){
		return conPool.size();
	}
	
	public synchronized DBConnection getConnection(){
		//如果有已经关闭的Conneciton，先从连接池中移除
		removeAnyClosedConnections();
		
		/**
		 * 循环遍历，取出一个可用的连接
		 * 将连接的available属性改成false，不可用
		 * 返回连接
		 */
		for(int i = 0 ;i< getNumConnInPool(); i++){
			if(conPool.get(i).isAvailable()){
				conPool.get(i).setAvailable(false);
				return conPool.get(i);
			}
		}
		
		//如果一旦发现没有可用的连接，则向连接池中添加一个新连接
		if(addConnectionsToPool(1)){
			conPool.get(conPool.size()-1).setAvailable(false);
			return(conPool.get(conPool.size()-1));
		}else{
			return null;
		}
		
	}
	//去掉那些已经关闭的连接
	private void removeAnyClosedConnections(){
		try{
			
			boolean done = false;
			
			while(done == false){
				
				done = true;
				
				for(int i = 0 ;i < getNumConnInPool();i++){
					if(conPool.get(i).isClose()){
						conPool.remove(i);
						done = false;
						break;
					}
				}
				
			}
		}catch(SQLException e){
			throw ExceptionFactory.getException(dbType, e.getMessage());
		}
	}
	
	//扩展连接池
	private boolean addConnectionsToPool(int num){
		//超过容量，返回false
		if((getNumConnInPool()+num) > (this.max+this.limit))
			return false;
		//添加新连接到连接池
		for(int i = 0 ;i<num;i++){
			if(this._url == null){
				DBConnection conn = new DBConnection();
				conn.setAvailable(true);
				conPool.add(conn);
			}else{
				DBConnection conn = new DBConnection(_url,_username,_password);
				conn.setAvailable(true);
				conPool.add(conn);
			}
		}
		return true;
	}
	
	//释放连接
	public void releaseConnection(DBConnection conn){
		
		for(int i = 0 ; i < getNumConnInPool() ; i++){
			if(conPool.get(i).equals(conn)){
				conPool.get(i).setAvailable(true);
			}
		}
	}
	
	//关闭所有连接
	public synchronized void closeAllConnections(){
		for(int i = 0;i<getNumConnInPool();i++){
			closeConnection(conPool.get(i));
		}
	}
	
	//关闭连接
	public synchronized void closeConnection(DBConnection conn){
		if(conn != null){
			conn.Close();
		}
	}
}