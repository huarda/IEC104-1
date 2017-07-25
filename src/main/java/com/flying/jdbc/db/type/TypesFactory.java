package com.flying.jdbc.db.type;
/**
 * 
 * @author zdf
 * @version 1.0
 * 
 */
import com.flying.jdbc.util.DBType;

public class TypesFactory {
	
	public static OracleTypes oracleTypes = new OracleTypes();
	public static MysqlTypes mysqlTypes = new MysqlTypes();
	public static MssqlTypes mssqlTypes = new MssqlTypes();
	
	public static BaseTypes getTypes(int databaseType){
		switch (databaseType)
		{
			case DBType.ORACLE : return oracleTypes;
			case DBType.MYSQL  : return mysqlTypes;   
			case DBType.MSSQL  : return	mssqlTypes;
		}
		return(null);
	}
}
