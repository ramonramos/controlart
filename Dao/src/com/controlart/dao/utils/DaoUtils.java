package com.controlart.dao.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUtils {
	public static final String SERVER_NAME = "localhost";
	public static final int PORT_NUMBER = 5432;
	public static final String DATABASE_NAME = "probd_controlart";
	public static final String USER = "postgres";
	public static final String PASSWORD = "postgres";

	public static final String RAISE_EXCEPTION_CODE = "p0001";

	public static final void closeConnection(Connection connection)
			throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

	public static final void closePreparedStatement(PreparedStatement pStmt)
			throws SQLException {
		if (pStmt != null) {
			pStmt.close();
		}
	}

	public static final void closeResultSet(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}

	public static final void closeStatementAndResultSet(
			PreparedStatement pStmt, ResultSet rs) throws SQLException {
		closeResultSet(rs);
		closePreparedStatement(pStmt);
	}
}
