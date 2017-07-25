package com.flying.jdbc.db.Exception;

import java.sql.SQLException;

public class MssqlException extends DatabaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MssqlException(String msg) {
		super(msg);
	}
	
	public MssqlException(String msg,SQLException e){
		super(msg,e);
	}
	
	@Override
	public boolean isBadSQLGrammar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDataIntegrityViolation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDatabaseUnavailable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInvalidBindVariableName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNonExistentTableOrViewOrCol() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRowlockOrTimedOut() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUniqueConstraintViolation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVarParameterUnbound() {
		// TODO Auto-generated method stub
		return false;
	}

}
