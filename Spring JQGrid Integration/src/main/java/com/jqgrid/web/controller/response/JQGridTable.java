package com.jqgrid.web.controller.response;

import java.util.List;

/**
 * A simple POJO that maps to the JSON structure of a jqGrid. 
 * <p> 
 
The property names of this POJO must match the property names of your jqGrid's jsonReader.
 * 
 * @see <a href="http://www.trirand.com/jqgridwiki/doku.php?id=wiki:retrieving_data#json_data">JSON Data</a>
 *
 */
public class JQGridTable {

	/**
	 * Current page of the query
	 */
	private String page;
	
	/**
	 * Total pages for the query
	 */
	private String total;
	
	/**
	 * Total number of records for the query
	 */
	private String records;
	
	/**
	 * An array containing the actual data
	 */
	private List<?> rows;

	public JQGridTable() {
		
	}
	
	public String getPage() {
		return page;
	}
	
	public void setPage(String page) {
		this.page = page;
	}

	public String getTotal() {
		return total;
	}
	
	public void setTotal(String total) {
		this.total = total;
	}

	public String getRecords() {
		return records;
	}
		
	public void setRecords(String records) {
		this.records = records;
	}
	
	public List<?> getRows() {
		return rows;
	}
	
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}