package com.controlart.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.controlart.dao.factory.ConnFactory;

public class RelatoriosDao {
	private Connection connection;
	
	public RelatoriosDao() throws SQLException {
		setConnection(ConnFactory.getConnection());
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}


}
