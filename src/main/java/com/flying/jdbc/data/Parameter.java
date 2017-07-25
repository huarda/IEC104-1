package com.flying.jdbc.data;

/**
 * 
 * @author zdf
 * @version 1.0
 */
public class Parameter {
	
	//属性区
	//参数名称，（标示，用法待扩展）
	public String parameterName = "";
	//参数值，传递、接受
	public Object Value = null;
	//输入、输出标志
	public ParameterDirection parameterDirection = null;
	//参数类型
	public int parameterType = 0;
	
	//构造方法
	public Parameter(
			String paraName,
			int paraType,
			Object paraValue,
			ParameterDirection paraDirection
			)
	{
		this.parameterName = paraName;
		this.parameterType = paraType;
		this.Value = paraValue;
		this.parameterDirection = paraDirection;
	}
	
	public Parameter(
			String paraName,
			int paraType,
			Object paraValue
			)
	{
		this.parameterName = paraName;
		this.parameterType = paraType;
		this.Value = paraValue;
		this.parameterDirection = ParameterDirection.IN;
	}
}
