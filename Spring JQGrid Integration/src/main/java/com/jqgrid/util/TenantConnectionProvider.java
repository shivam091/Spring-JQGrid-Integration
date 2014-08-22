package com.jqgrid.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;


@SuppressWarnings("serial")
public class TenantConnectionProvider implements ConnectionProvider {
	
	@SuppressWarnings("unused")
	private String tenantCode;
	private Connection connection;
	
	public TenantConnectionProvider(String tenantCode, Connection connection) {
		this.tenantCode = tenantCode;
		this.connection = connection;
	}

	public boolean isUnwrappableAs(@SuppressWarnings("rawtypes") Class unwrapType) {
		return false;
	}

	public <T> T unwrap(Class<T> unwrapType) {
		return null;
	}

	public Connection getConnection() throws SQLException {
		return connection;
	}

	public void closeConnection(Connection conn) throws SQLException {
		conn.close();

	}

	public boolean supportsAggressiveRelease() {
		return false;
	}
}