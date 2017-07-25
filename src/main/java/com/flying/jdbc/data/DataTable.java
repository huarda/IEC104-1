package com.flying.jdbc.data;

/**
 * 
 * @author zdf
 * @version 1.0
 */
public class DataTable {
	
	//属性区
	public DataRow[] Rows = new DataRow[0];
	
	//构造方法
	public DataTable(DataRow[] rows)
	{
		Rows = rows.clone();
	}
	
	public DataTable()
	{
		
	}
	
	//方法区
	public void AddRow(DataRow row)
	{
		DataRow[] _rows = Rows.clone();
		this.Rows = null;
		this.Rows = new DataRow[_rows.length+1];
		for(int i=0;i<_rows.length;i++)
		{
			this.Rows[i] = _rows[i];
		}
		this.Rows[this.Rows.length-1] = row;
		_rows = null;
	}
	
	public int size()
	{
		return Rows.length;
	}
}
