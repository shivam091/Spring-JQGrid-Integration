package com.jqgrid.web.controller.response;

import java.util.List;

/**
 * A POJO containing the status of an action and a {@link List} of messages. 
 * This is mainly used as a DTO for the presentation layer
 */
public class JQResponse {

	protected Boolean success;
	protected List<?> messages;
	
	public JQResponse(Boolean success) {
		super();
		this.success = success;
	}
	
	public JQResponse(Boolean success, List<?> messages) {
		super();
		this.success = success;
		this.messages = messages;
	}
	
	public Boolean getSuccess() {
		return success;
	}
	
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	public List<?> getMessages() {
		return messages;
	}
	
	public void setMessagse(List<?> messages) {
		this.messages = messages;
	}
}