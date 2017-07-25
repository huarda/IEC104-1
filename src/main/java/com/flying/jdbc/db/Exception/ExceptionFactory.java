package com.flying.jdbc.db.Exception;

import com.flying.jdbc.util.DBType;

import java.sql.SQLException;

/**
 * Given a databaseType (e.g. DatabaseType.ORACLE), message, and SQLException, generate the 
 * appropriate DatabaseException subclass object 
 * @author Jeff Smith (jeff@SoftTechDesign.com, www.SoftTechDesign.com)
 * @author Paolo Orru (paolo.orru@gmail.com), added PostgreSQL support
 */
public class ExceptionFactory
{
	/**
	 * Generate the correct exception subclass (of DatabaseException). For example, generate an
	 * OracleException or a MySQLException.
	 * @param databaseType
	 * @param msg
	 * @param e
	 * @return DatabaseException (subclass)
	 */
	public static DatabaseException getException(int databaseType, String msg, SQLException e)
	{
		switch (databaseType)
		{
			case DBType.ORACLE : return(new OracleException(msg, e));
			case DBType.MYSQL  : return(new MysqlException(msg, e));
			case DBType.MSSQL  : return(new MssqlException(msg, e));
		}
		return(null);
	}

	/**
	 * Generate an object of the correct exception subclass (of DatabaseException). For example, 
	 * generate an OracleException or a MySQLException.
	 * @param databaseType
	 * @param msg
	 * @return DatabaseException (subclass)
	 */
	public static DatabaseException getException(int databaseType, String msg)
	{
		switch (databaseType)
		{
			case DBType.ORACLE : return(new OracleException(msg));
			case DBType.MYSQL  : return(new MysqlException(msg));
			case DBType.MSSQL  : return(new MssqlException(msg));
		}
		return(null);
	}
}
