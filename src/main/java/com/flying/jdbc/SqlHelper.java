package com.flying.jdbc;

import com.flying.jdbc.data.*;
import com.flying.jdbc.util.DBConnection;
import com.flying.jdbc.util.DBConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author zdf
 * @version 1.0
 * 
 */
public class SqlHelper {
	public static DBConnectionPool connPool = null;
	/**
	 * 
	 * @param connection
	 * @param commandType
	 * @param commandText
	 * @return
	 */
	public static List<Map> executeQuery(DBConnection connection,
                                         CommandType commandType, String commandText) throws Exception{
		return executeQuery(connection,commandType,commandText,new Parameter[0]);
	}
	
	/**
	 * 
	 * @param connection
	 * @param commandType
	 * @param commandText
	 * @param parameters
	 * @return
	 */
	public static List<Map> executeQuery(DBConnection connection,
                                         CommandType commandType, String commandText, Parameter... parameters) throws Exception{
		
		List<Map> resultList = new ArrayList<Map>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			if (commandType.equals(CommandType.Text)) {
				if(commandText.contains("?")){
					stmt = connection.GetConnection().prepareStatement(commandText);
					Parameter[] paras = parameters;
					for(int i = 0;i<paras.length;i++){
						((PreparedStatement)stmt).setObject(i+1,paras[i].Value,paras[i].parameterType);
					}
					rs = ((PreparedStatement)stmt).executeQuery();
					
				}else{
					stmt = connection.GetConnection().createStatement();
					rs = stmt.executeQuery(commandText);
				}

				resultList.addAll(convertResultSetToList(rs));
				
			} else if (commandType.equals(CommandType.StoreProcedure)) {
	
				Parameter[] paras = parameters;
	
				String sql = "";
				for (int i = 0; i < paras.length; i++) {
					sql = sql + "?,";
				}
				if (sql.length() > 0) {
					sql = "(" + sql.substring(0, sql.length() - 1) + ")";
				}
	
				sql = "{ call " + commandText + sql + " }";
				stmt =connection.GetConnection().prepareCall(sql);
				for (int i = 0; i < paras.length; i++) {
					Parameter p = paras[i];
					if (p.parameterDirection == ParameterDirection.IN) {
						((CallableStatement)stmt).setObject(i + 1, p.Value, p.parameterType);
					} else if (p.parameterDirection == ParameterDirection.OUT) {
						((CallableStatement)stmt).registerOutParameter(i + 1, p.parameterType);
					}
				}
				
				if(((CallableStatement)stmt).execute()){
					resultList.addAll(convertResultSetToList(((CallableStatement)stmt).getResultSet()));
					while(((CallableStatement)stmt).getMoreResults()){
						resultList.addAll(convertResultSetToList(((CallableStatement)stmt).getResultSet()));
					}
				}
				//注意JDBC的读取方式，是一种指针的读取方式，当我们使用watch进行调试的时候，如果监视JDBC的get方法时候，常出现01002，读取异常的错误，
				//原因指针已经下移，重新读取，指针已经下移，而watch的get值还是刚才的那个值，发生读取顺序异常的错误。
				//支持游标
				for (int i = 0; i < paras.length; i++) {
					Parameter p = paras[i];
					if (p.parameterDirection == ParameterDirection.OUT) {
						p.Value = ((CallableStatement)stmt).getObject(i + 1);
						if (p.parameterType == -10) {//oracle.jdbc.OracleTypes.CURSOR:oracle游标的值
							rs = (ResultSet) p.Value;
							List<Map> listDt = convertResultSetToList(rs);
							p.Value = listDt;
							resultList.addAll(listDt);
						}
					}
				}

			} else {
				throw new Exception("commandType is invalid");
			}
		} catch (Exception e) {
			connection.Close();
			throw e;
		}
		//关闭
		if(rs != null)
			rs.close();
		stmt.close();
		
		return resultList;
	}
	/**
	 * 
	 * @param connection
	 * @param commandType
	 * @param commandText
	 * @return
	 * @throws Exception
	 */
	public static DataSet executeQueryForDataSet(DBConnection connection,
                                                 CommandType commandType, String commandText) throws Exception {
		return executeQueryForDataSet(connection, commandType, commandText,new Parameter[0]);
	}

	/**
	 * 
	 * @param connection
	 * @param commandType
	 * @param commandText
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public static DataSet executeQueryForDataSet(DBConnection connection,
                                                 CommandType commandType, String commandText, Parameter... parameters) throws Exception {

		DataSet ds = new DataSet();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			if (commandType.equals(CommandType.Text)) {
				if(commandText.contains("?")){
					stmt = connection.GetConnection().prepareStatement(commandText);
					Parameter[] paras = parameters;
					for(int i = 0;i<paras.length;i++){
						((PreparedStatement)stmt).setObject(i+1,paras[i].Value,paras[i].parameterType);
					}
					rs = ((PreparedStatement)stmt).executeQuery();
					
				}else{
					stmt = connection.GetConnection().createStatement();
					rs = stmt.executeQuery(commandText);
				}

				ds.AddTable(convertResultSetToDataTable(rs));
				
			} else if (commandType.equals(CommandType.StoreProcedure)) {
	
				Parameter[] paras = parameters;
	
				String sql = "";
				for (int i = 0; i < paras.length; i++) {
					sql = sql + "?,";
				}
				if (sql.length() > 0) {
					sql = "(" + sql.substring(0, sql.length() - 1) + ")";
				}
	
				sql = "{ call " + commandText + sql + " }";
				stmt =connection.GetConnection().prepareCall(sql);
				for (int i = 0; i < paras.length; i++) {
					Parameter p = paras[i];
					if (p.parameterDirection == ParameterDirection.IN) {
						((CallableStatement)stmt).setObject(i + 1, p.Value, p.parameterType);
					} else if (p.parameterDirection == ParameterDirection.OUT) {
						((CallableStatement)stmt).registerOutParameter(i + 1, p.parameterType);
					}
				}
				
				if(((CallableStatement)stmt).execute()){
					ds.AddTable(convertResultSetToDataTable(((CallableStatement)stmt).getResultSet()));
					while(((CallableStatement)stmt).getMoreResults()){
						ds.AddTable(convertResultSetToDataTable(((CallableStatement)stmt).getResultSet()));
					}
				}
				//注意JDBC的读取方式，是一种指针的读取方式，当我们使用watch进行调试的时候，如果监视JDBC的get方法时候，常出现01002，读取异常的错误，
				//原因指针已经下移，重新读取，指针已经下移，而watch的get值还是刚才的那个值，发生读取顺序异常的错误。
				//支持游标
				for (int i = 0; i < paras.length; i++) {
					Parameter p = paras[i];
					if (p.parameterDirection == ParameterDirection.OUT) {
						p.Value = ((CallableStatement)stmt).getObject(i + 1);
						if (p.parameterType == -10) {//oracle.jdbc.OracleTypes.CURSOR:oracle游标的值
							rs = (ResultSet) p.Value;
							DataTable dt = convertResultSetToDataTable(rs);
							DataSet _lds = new DataSet();
							_lds.AddTable(dt);
							p.Value = _lds;
							ds.AddTable(dt);
						}
					}
				}

			} else {
				throw new Exception("commandType is invalid");
			}
		} catch (Exception e) {
			connection.Close();
			throw e;
		}
		//关闭
		if(rs != null)
			rs.close();
		stmt.close();
		
		return ds;
	}
	
	/**
	 * 
	 * @param connection
	 * @param commandType
	 * @param commandText
	 * @throws Exception
	 */
	public static void executeNonQuery(DBConnection connection,
                                       CommandType commandType, String commandText) throws Exception {
		executeNonQuery(connection, commandType, commandText, new Parameter[0]);
	}

	/**
	 * 
	 * @param connection
	 * @param commandType
	 * @param commandText
	 * @param parameters
	 * @throws Exception
	 */
	public static void executeNonQuery(DBConnection connection,
                                       CommandType commandType, String commandText,
                                       Parameter... parameters) throws Exception {
		
		Statement stmt = null;
		
		try {
			if (commandType.equals(CommandType.Text)) {
				if(commandText.contains("?")){
					stmt = connection.GetConnection().prepareStatement(commandText);
					Parameter[] paras = parameters;
					for(int i = 0;i<paras.length;i++){
						((PreparedStatement)stmt).setObject(i+1, paras[i].Value, paras[i].parameterType);
					}
					((PreparedStatement)stmt).execute();
					((PreparedStatement)stmt).close();
				}else{
					stmt = connection.GetConnection().createStatement();
					stmt.execute(commandText);
					stmt.close();
				}
				
			} else if (commandType.equals(CommandType.StoreProcedure)) {
	
				Parameter[] paras = parameters;
	
				String sql = "";
				for (int i = 0; i < paras.length; i++) {
					sql = sql + "?,";
				}
				if (sql.length() > 0) {
					sql = "(" + sql.substring(0, sql.length() - 1) + ")";
				}
	
				sql = "{ call " + commandText + sql + " }";
				stmt = connection.GetConnection().prepareCall(sql);
				for (int i = 0; i < paras.length; i++) {
					Parameter p = paras[i];
					if (p.parameterDirection == ParameterDirection.IN) {
						((CallableStatement)stmt).setObject(i + 1, p.Value, p.parameterType);
					} else if (p.parameterDirection == ParameterDirection.OUT) {
						((CallableStatement)stmt).registerOutParameter(i + 1, p.parameterType);
					}
				}
				
				((CallableStatement)stmt).execute();
				//支持游标
				for (int i = 0; i < paras.length; i++) {
					Parameter p = paras[i];
					if (p.parameterDirection == ParameterDirection.OUT) {
						p.Value = ((CallableStatement)stmt).getObject(i + 1);
						if (p.parameterType == -10) {//oracle.jdbc.OracleTypes.CURSOR:oracle游标的值
							ResultSet rs = (ResultSet) p.Value;
							DataTable dt = convertResultSetToDataTable(rs);
							DataSet _lds = new DataSet();
							_lds.AddTable(dt);
							p.Value = _lds;
						}
					}
				}
			} else {
				throw new Exception("commandType is invalid");
			}
		} catch (Exception e) {
			connection.Close();
			throw e;
		}
		
		stmt.close();
	}

	/**
	 * 
	 * @param connection
	 * @param commandType
	 * @param commandText
	 * @return
	 * @throws Exception
	 */
	public static Object executeScale(DBConnection connection,
                                      CommandType commandType, String commandText)throws Exception{
		return executeScale(connection,commandType,commandText,new Parameter[0]);
	}
	
	/**
	 * 返回第一行，第一列
	 * @param connection
	 * @param commandType
	 * @param commandText
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public static Object executeScale(DBConnection connection,
                                      CommandType commandType, String commandText,
                                      Parameter...parameters)throws Exception {
		
		Object obj = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		if (commandType.equals(CommandType.Text)) {
			if(commandText.contains("?")){
				stmt = connection.GetConnection().prepareStatement(commandText);
				Parameter[] paras = parameters;
				for(int i = 0;i<paras.length;i++){
					((PreparedStatement)stmt).setObject(i+1, paras[i].Value, paras[i].parameterType);
				}
				
				rs = ((PreparedStatement)stmt).executeQuery();
			}else{
				stmt = connection.GetConnection().createStatement();
				rs = stmt.executeQuery(commandText);
			}
		} else if (commandType.equals(CommandType.StoreProcedure)) {
			//不支持存储过程
			return null;
		}
		obj = buildScalar(rs);
		//关闭
		rs.close();
		stmt.close();
		
		return obj;
	}
	/**
	 * 将ResultSet装换为DataTable的函数
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException 
	 * @throws Exception
	 */
	private static DataTable convertResultSetToDataTable(ResultSet rs) throws SQLException{
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();

		DataTable dt = new DataTable();
		while (rs.next()) {
			DataRow dr = new DataRow();
			for (int i = 1; i <= columnCount; i++) {
				String columnName = rsmd.getColumnName(i);
				Object columnValue = rs.getObject(i);
				
				//对于不同库的不同返回类型，可以在此处进行单独处理，使之统一，也可以存入DataColumn中，再根据需要处理。
				//注意时间类型，在Mssql中，时间有Date，Datetime两种，Oralce中，时间有Date，DateTime，Timestamp三种，
				//在返回时间的时候，Mssql将时间当字符串输出，Oralce将时间以Timestamp类型输出。
				
				DataColumn dc = new DataColumn(columnName,columnValue);
				dr.AddColumn(dc);
			}
			dt.AddRow(dr);
		}
		return dt;
	}
	/**
	 * 将ResultSet装换为List的函数
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private static List<Map> convertResultSetToList(ResultSet rs) throws SQLException{
		
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();

		List<Map> resultList = new ArrayList<Map>();
		while (rs.next()) {
			Map resultMap = new HashMap();
			for (int i = 1; i <= columnCount; i++) {
				String columnName = rsmd.getColumnName(i);
				Object columnValue = rs.getObject(i);
				
				//对于不同库的不同返回类型，可以在此处进行单独处理，使之统一，也可以存入DataColumn中，再根据需要处理。
				//注意时间类型，在Mssql中，时间有Date，Datetime两种，Oralce中，时间有Date，DateTime，Timestamp三种，
				//在返回时间的时候，Mssql将时间当字符串输出，Oralce将时间以Timestamp类型输出。
				
				resultMap.put(columnName, columnValue);
			}
			resultList.add(resultMap);
		}
		return resultList;
	}
	
	/**
	 * 返回结果的第一个行，第一列的值
	 * @param rs
	 * @return
	 * @throws SQLException 
	 */
	private static Object buildScalar(ResultSet rs) throws SQLException
    {
        if (rs == null)
        {
            return null;
        }
        Object obj = null;
        
        if (rs.next())
        {
            obj = rs.getObject(1);
        }
        
        return obj;
    }
}