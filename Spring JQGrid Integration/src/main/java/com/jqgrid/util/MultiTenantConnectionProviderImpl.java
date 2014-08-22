package com.jqgrid.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.c3p0.internal.C3P0ConnectionProvider;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.engine.config.spi.ConfigurationService;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.UnknownUnwrapTypeException;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;

public class MultiTenantConnectionProviderImpl implements
		MultiTenantConnectionProvider, ServiceRegistryAwareService {

	private static final long serialVersionUID = -3792307191218532011L;
	private C3P0ConnectionProvider connectionProvider = null;

	private Properties getConnectionProperties() {
		Properties properties = new Properties();
		properties.put(AvailableSettings.DIALECT,
				"org.hibernate.dialect.MySQLDialect");
		properties.put(AvailableSettings.DRIVER, "com.mysql.jdbc.Driver");
		properties.put(AvailableSettings.URL,
				"jdbc:mysql://localhost:3306/qwmaster");
		properties.put(AvailableSettings.USER, "root");
		properties.put(AvailableSettings.PASS, "root");
		properties.put(AvailableSettings.HBM2DDL_AUTO, "create-drop");
		//properties.put(AvailableSettings.HBM2DDL_IMPORT_FILES, "classpath:import.sql");
		//properties.put(AvailableSettings.MULTI_TENANT, "DATABASE");
		//properties.put(AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER, "MultiTenantConnectionProviderImpl");
		//properties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, "CurrentTenantIdentifierResolverImpl");
		return properties;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean isUnwrappableAs(Class unwrapType) {
		// TODO Auto-generated method stub
		 return ConnectionProvider.class.equals(unwrapType) || MultiTenantConnectionProvider.class.equals(unwrapType) || MultiTenantConnectionProviderImpl.class.isAssignableFrom(unwrapType);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T unwrap(Class<T> unwrapType) {
		if (isUnwrappableAs(unwrapType)) {
            return (T) this;
        } else {
            throw new UnknownUnwrapTypeException(unwrapType);
        }
	}

	@Override
	public Connection getAnyConnection() throws SQLException {
		System.out.println("******* SchemaBasedMultiTenantConnectionProvider.getAnyConnection() *******");
		return connectionProvider.getConnection();
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		connection.close();
	}

	@Override
	public Connection getConnection(String tenantIdentifier)
			throws SQLException {
		final Connection connection = getAnyConnection();
		try {
			// This is DB specific syntax. This work for MSSQL and MySQL
			// Oracle uses the ALTER SESSION SET SCHEMA command
			connection.createStatement().execute("USE " + tenantIdentifier);
		} catch (SQLException e) {
			throw new HibernateException(
					"Could not alter JDBC connection to specified schema ["	+ tenantIdentifier + "]", e);
		}
		return connection;
	}

	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection)
			throws SQLException {
		try {
            this.releaseAnyConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public boolean supportsAggressiveRelease() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void injectServices(ServiceRegistryImplementor serviceRegistry) {
		@SuppressWarnings("rawtypes")
		Map lSettings = serviceRegistry.getService(ConfigurationService.class).getSettings();
		//Map lSettings = serviceRegistry.getService(ConfigurationService.class)
		//		.getSettings();
		lSettings = getConnectionProperties();
		connectionProvider = new C3P0ConnectionProvider();
		connectionProvider.injectServices(serviceRegistry);
		connectionProvider.configure(lSettings);
	}
}
