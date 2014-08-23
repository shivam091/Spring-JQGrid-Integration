package com.jqgrid.web.controller.response;

/**
 * A POJO containing the status of an action and message. 
 * This is mainly used as a DTO for the presentation layer
 * 
 * A custom POJO that will be automatically converted to JSON format. 
 * <p>
 * We can use this to send generic messages to our JqGrid, whether a request is successful or not.
 * Of course, you will use plain JavaScript to parse the JSON response. 
 * @author shivam
 */
public class JQResponse {

	/**
	  * true if operation is successful. 
	  */
	protected Boolean success;
	
	/**
	  * Any custom message, i.e, 'Your request has been processed successfully!'
	  */
	protected String messages;
	
	public JQResponse() {
		super();
	}
	
	public JQResponse(Boolean success) {
		super();
		this.success = success;
	}
	
	public JQResponse(Boolean success, String messages) {
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
	
	public String getMessages() {
		return messages;
	}
	
	public void setMessagse(String messages) {
		this.messages = messages;
	}
}