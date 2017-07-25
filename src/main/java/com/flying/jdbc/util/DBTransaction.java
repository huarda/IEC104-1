package com.flying.jdbc.util;

/**
 * 
 * @author zdf
 * @version 1.0
 */
public class DBTransaction {

	public DBTransaction(DBConnection connection) throws Exception
	{	
		this.Connection = connection;
		this.Connection.GetConnection().setAutoCommit(false);
	}
	
	/**
	 * 事务提交
	 * @throws Exception
	 */
	public void Commit() throws Exception
	{
		try
		{
			this.Connection.GetConnection().commit();
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			this.Connection.GetConnection().setAutoCommit(true);
			this.Connection.Close();
		}
	}
	
	/**
	 * 事务回滚
	 * @throws Exception
	 */
	public void Rollback() throws Exception
	{
		try
		{
			this.Connection.GetConnection().rollback();
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			this.Connection.GetConnection().setAutoCommit(true);
			this.Connection.Close();
		}
	}
	
	public DBConnection Connection = null;

}