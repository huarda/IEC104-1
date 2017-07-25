package com.flying.jdbc.data;

/**
 * 
 * @author zdf
 * @version 1.0
 * 
 */
public class DataRow extends Object {
	
	//属性区
	public DataColumn[] Columns = new DataColumn[0];
	
	//构造方法
	public DataRow(DataColumn[] cols)
	{
		Columns = cols.clone();
	}
	public DataRow()
	{
		
	}
	//方法区
	public void AddColumn(DataColumn col)
	{
		DataColumn[] _cols = Columns.clone();
		Columns = null;
		Columns = new DataColumn[_cols.length+1];
		for(int i=0;i<_cols.length;i++)
		{
			Columns[i] = _cols[i];
		}
		Columns[Columns.length-1] = col;
		_cols = null;
	}
	
	public int size()
	{
		return Columns.length;
	}
	
}
