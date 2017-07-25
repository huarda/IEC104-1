package com.flying.jdbc.util;
/**
 * 
 * @author zdf
 * @version 1.0
 * 
 */
public class DBType {
	
	public final static int UNKNOWN = 0;
	public final static int ORACLE = 1;
	public final static int MSSQL = 2;
	public final static int MYSQL = 3;
	
	public final static String ORACLE_NAME = "ORACLE";
	public final static String MSSQL_NAME = "SQLSERVER";
	public final static String MYSQL_NAME = "MYSQL";
	
	public final static String DEFAULT_ORACLE_DIRVER = "oracle.jdbc.driver.OracleDriver";
	public final static String DEFAULT_MSSQL_DIRVER = "net.sourceforge.jtds.jdbc.Driver";
	public final static String DEFAULT_MYSQL_DIRVER = "com.mysql.jdbc.Driver";
	
	public static int getDBType(DBConnection conn){
		String dbName = null;
		int dbType = 0;
		
		try{
			dbName = conn.GetConnection().getMetaData().getDatabaseProductName();
			if(dbName.toUpperCase().indexOf(ORACLE_NAME)> -1)
				dbType = ORACLE;
			else if(dbName.toUpperCase().indexOf(MSSQL_NAME)> -1)
				dbType = MSSQL;
			else if(dbName.toUpperCase().indexOf(MYSQL_NAME)> -1)
				dbType = MYSQL;
			else
				dbType = UNKNOWN;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dbType;
	}

	public static int getDBType(String driverName){
		int dbType = 0;
		
		try{
			if(driverName.toUpperCase().indexOf(ORACLE_NAME)> -1)
				dbType = ORACLE;
			else if(driverName.toUpperCase().indexOf(MSSQL_NAME)> -1)
				dbType = MSSQL;
			else if(driverName.toUpperCase().indexOf(MYSQL_NAME)> -1)
				dbType = MYSQL;
			else
				dbType = UNKNOWN;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dbType;
	}
	
	public static String getDefaultDriver(String url){
		String dbDriver = null;
		
		try{
			if(url.toUpperCase().indexOf(ORACLE_NAME)> -1)
				dbDriver = DEFAULT_ORACLE_DIRVER;
			else if(url.toUpperCase().indexOf("MSSQL_NAME")> -1)
				dbDriver = DEFAULT_MSSQL_DIRVER;
			else if(url.toUpperCase().indexOf(MYSQL_NAME)> -1)
				dbDriver = DEFAULT_MYSQL_DIRVER;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dbDriver;
	}
}