package com.flying.jdbc.data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * 
 * @author zdf
 * @version 1.0
 */
public class DataColumn extends Object {
	
	//属性区
	//列名
	public String colName = null;
	//列值
	public Object colValue = null;
	
	
	//构造方法
	public DataColumn()
	{
	}
	
	public DataColumn(String n,Object v)
	{
		colName = n;
		colValue = v;
	}
	//注意数值类型，Oralce中，数据只有Number一种，所以返回的数值都存入BigDecimal中。
	//而在Mssql,Mysql中，数值是以多种数据类型存储，所以返回的数据类型有Integer,Double,Float,Long。
	//返回整数值
	public int getInt(){
		//判断是否为null
		if(isNull()) return (0);
		
		if(colValue instanceof Integer){
			return(((Integer)colValue).intValue());
		}else if(colValue instanceof BigDecimal){
			return(((BigDecimal)colValue).intValue());
		}else{
			//类型错误
			return (0);
		}
	}
	
	//返回长整形
	public long getLong(){
		//判断是否为空
		if(isNull()) return (0);
		
		if (colValue instanceof Long)
			return(((Long)colValue).longValue());
		else if (colValue instanceof Integer)
			return(((Integer)colValue).longValue());
		else if (colValue instanceof BigDecimal)
			return(((BigDecimal)colValue).longValue());
		else 
			//错误类型
			return (0);
	}
	
	//返回浮点型
	public float getFloat(){
		//判断是否为空
		if(isNull()) return (0);
		
		if (colValue instanceof Float)
			return(((Float)colValue).floatValue());
		else if (colValue instanceof BigDecimal)
			return(((BigDecimal)colValue).floatValue());
		else 
			//错误类型
			return (0);
	}
	
	//返回双精度值
	public double getDouble(){
		//判断是否为空
		if(isNull()) return (0);
		
		if (colValue instanceof Double)
			return(((Double)colValue).doubleValue());
		else if (colValue instanceof BigDecimal)
			return(((BigDecimal)colValue).doubleValue());
		else 
			//错误类型
			return (0);
	}
	
	//返回布尔值
	public boolean getBoolean(){
		//判断是否为空
		if(isNull()) return false;
		
		if(colValue instanceof Boolean)
			return (((Boolean)colValue).booleanValue());
		else
			return(false);
	}
	//注意时间类型，在Mssql中，时间有Date，Datetime两种，Oralce中，时间有Date，DateTime，Timestamp三种，
	//在返回时间的时候，Mssql将时间当字符串输出，Oralce将时间以Timestamp类型输出。
	//返回日期型java.sql.Date
	public Date getDate(){
		//判断是否为空
		if(isNull()) return null;
		
		if (colValue instanceof Timestamp)
			return new Date(((Timestamp)colValue).getTime());
		else if(colValue instanceof Date)
			return((Date)colValue);
		else
			return null;
	}
	
	//java.sql.Timestamp
	public Timestamp getTimestamp(){
		//判断是否为空
		if(isNull()) return null;
		
		if (colValue instanceof Timestamp)
			return (Timestamp)colValue;
		else if(colValue instanceof Date)
			return new Timestamp(((Date)colValue).getTime());
		else
			return null;
	}
	
	//java.sql.Time
	public Time getTime(){
		//判断是否为空
		if(isNull()) return null;
		
		if(colValue instanceof Time)
			return((Time)colValue);
		else
			return null;
	}
	
	//返回字符串
	public String getString(){
		//判断是否为空
		if(isNull()) return "";
		
		if (colValue instanceof BigDecimal)
			return("" + getDouble());
		else if (colValue instanceof Integer)
			return("" + getInt());
		else if (colValue instanceof Long)
			return("" + getLong());
		else if (colValue instanceof Boolean)
			return("" + getBoolean());
		else if (colValue instanceof Date)
			return("" + getDate());
		else if (colValue instanceof Timestamp)
			return("" + getTimestamp());
		else if (colValue instanceof Time)
			return("" + getTime());
		else if (colValue instanceof Float)
			return("" + getFloat());
		else if (colValue instanceof Double)
			return("" + getDouble());
		else if (colValue instanceof String)
			return(colValue.toString());
		else
			return "";//不支持类型
	}
	
	//判断是否有值
	public boolean isNull(){
		return (colValue == null);
		
	}
	
}
