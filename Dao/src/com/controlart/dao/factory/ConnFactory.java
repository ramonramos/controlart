package com.controlart.dao.factory;

import java.sql.Connection;
import java.sql.SQLException;

import org.postgresql.ds.PGPoolingDataSource;

import com.controlart.dao.utils.DaoUtils;

public class ConnFactory {
	public final static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}

	private final static PGPoolingDataSource getDataSource()
			throws SQLException {
		PGPoolingDataSource pgDataSource = new PGPoolingDataSource();

		pgDataSource.setServerName(DaoUtils.SERVER_NAME);
		pgDataSource.setUser(DaoUtils.USER);
		pgDataSource.setPassword(DaoUtils.PASSWORD);
		pgDataSource.setPortNumber(DaoUtils.PORT_NUMBER);
		pgDataSource.setDatabaseName(DaoUtils.DATABASE_NAME);

		return pgDataSource;
	}
}
