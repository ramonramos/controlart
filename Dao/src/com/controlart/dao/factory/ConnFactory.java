package com.controlart.dao.factory;

import java.sql.Connection;
import java.sql.SQLException;

import org.postgresql.ds.PGPoolingDataSource;

import com.controlart.dao.utils.DaoUtils;

public class ConnFactory {

	private static PGPoolingDataSource pgPooDataSource;

	public final static Connection getConnection() throws SQLException {
		return getDataSourceInstance().getConnection();
	}

	private final static PGPoolingDataSource getDataSourceInstance()
			throws SQLException {
		if (pgPooDataSource == null) {
			return getDataSource();
		} else {
			return pgPooDataSource;
		}
	}

	private final static PGPoolingDataSource getDataSource()
			throws SQLException {
		pgPooDataSource = new PGPoolingDataSource();

		pgPooDataSource.setServerName(DaoUtils.SERVER_NAME);
		pgPooDataSource.setUser(DaoUtils.USER);
		pgPooDataSource.setPassword(DaoUtils.PASSWORD);
		pgPooDataSource.setPortNumber(DaoUtils.PORT_NUMBER);
		pgPooDataSource.setDatabaseName(DaoUtils.DATABASE_NAME);

		return pgPooDataSource;
	}
}
