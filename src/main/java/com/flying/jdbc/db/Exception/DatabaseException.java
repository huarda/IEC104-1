package com.flying.jdbc.db.Exception;

import java.sql.SQLException;

/**
 * DatabaseException denotes a generic runtime data access (SQL) exception. By declaring the 
 * exception as a descendant of RuntimeException, the jdbc framework give the programmer the option
 * of whether or not to catch the exception.
 * @author Jeff Smith (jeff@SoftTechDesign.com, www.SoftTechDesign.com)
 * @author Paolo Orru (paolo.orru@gmail.com) modified to add PostgreSQL support
 */
public abstract class DatabaseException extends RuntimeException
{
	/** sql error code obtained from database after exception */
	protected int sqlErrorCode = 0;

	/** sql state obtained from database after exception */
	protected String SQLState = null;

	/** was db exception caused by a data integrity violation? */
	public abstract boolean isDataIntegrityViolation();

	/** was db exception caused by unique constraint violation (duplicate record) */
	public abstract boolean isUniqueConstraintViolation();

	/** was db exception caused by bad sql grammer (a typo)? */
	public abstract boolean isBadSQLGrammar();

	/** was db exception caused by referencing a non existent table or view? */
	public abstract boolean isNonExistentTableOrViewOrCol();

	/** was db exception caused by referencing a invalid bind parameter name? */
	public abstract boolean isInvalidBindVariableName();

	/** was db exception caused by database being unavailable? */
	public abstract boolean isDatabaseUnavailable();

	/** was db exception caused by a row lock error or some other sql querty timeout? */
	public abstract boolean isRowlockOrTimedOut();

	/** was db exception caused by a an unbound variable (parameter)? */
	public abstract boolean isVarParameterUnbound();


	/**
	 * constructor initializes the sqlErrorCode
	 * @param msg error message
	 * @param e corresponding SQLException 
	 */
	public DatabaseException(String msg, SQLException e)
	{
		super(msg);

		sqlErrorCode = e.getErrorCode();

		// added by Paolo Orru, to fix NullPointerException for PostgreSQL Database Exception
		SQLState = e.getSQLState (); 

		//could add code here to write the exception message to a log file or table
	}

	/**
	 * constructor initializes the sqlErrorCode to -1 and the SQLState to null
	 * @param s
	 */
	public DatabaseException(String msg)
	{
		super(msg);

		sqlErrorCode = -1;
		SQLState = null;
		//could add code here to write the exception message to a log file or table
	}

	/**
	 * Get the SQLErrorCode
	 * @return sqlErrorCode as an integer
	 */
	public int getSQLErrorCode()
	{
		return(sqlErrorCode);
	}

	/**
	 * get the SQLState
	 * @return SQLState as String
	 */
	public String getSQLState()
	{
		return(SQLState);
	}
}
