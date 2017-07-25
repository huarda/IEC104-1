package com.flying.jdbc.data;

/**
 * 
 * @author zdf
 * @version 1.0
 */
public class DataSet {
	
	//属性区
	public DataTable[] Tables = new DataTable[0];
	
	//构造方法
	public DataSet(DataTable[] tables)
	{
		this.Tables = tables.clone();
	}
	
	public DataSet()
	{
		
	}
	
	//方法区
	public void AddTable(DataTable table)
	{
		DataTable[] _tables = this.Tables.clone();
		this.Tables = null;
		this.Tables = new DataTable[_tables.length+1];
		for(int i=0;i<_tables.length;i++)
		{
			this.Tables[i] = _tables[i];
		}
		this.Tables[this.Tables.length-1] = table;
		_tables = null;
	}
	
	public int size()
	{
		return Tables.length;
	}
}
