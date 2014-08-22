package com.jqgrid.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import com.mysql.jdbc.Driver;

@SuppressWarnings("serial")
public class MultiTenantConnectionProviderImplMultiCP extends AbstractMultiTenantConnectionProvider {
	private String conUrlPrefix = null;
	private String username = null;
	private String password = null;

	public MultiTenantConnectionProviderImplMultiCP() {
		try {
			DriverManager.registerDriver(new Driver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected ConnectionProvider getAnyConnectionProvider() {
		return new TenantConnectionProvider("quikonnect",
				getConnectionForTenant("quikonnect"));
	}

	@Override
	protected ConnectionProvider selectConnectionProvider(String tenantCode) {
		return new TenantConnectionProvider(tenantCode,
				getConnectionForTenant(tenantCode));
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setConUrlPrefix(String conUrlPrefix) {
		this.conUrlPrefix = conUrlPrefix;
	}

	private Connection getConnectionForTenant(String tenantDb) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(conUrlPrefix + tenantDb + "?"
					+ "user=" + username + "&password=" + password);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}
}
