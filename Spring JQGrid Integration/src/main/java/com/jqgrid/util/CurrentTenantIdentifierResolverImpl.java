package com.jqgrid.util;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class CurrentTenantIdentifierResolverImpl implements	CurrentTenantIdentifierResolver {
	
	public String resolveCurrentTenantIdentifier() {
		String tenantCode = null;
		if(tenantCode == null) {
			tenantCode = "quikonnect";
		}
		return tenantCode;
	}

	public boolean validateExistingCurrentSessions() {
		return true;
	}

}
