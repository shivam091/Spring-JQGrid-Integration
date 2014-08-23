<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url value="${pageContext.request.contextPath}" var="rootUrl" />
<c:url value="/resources" var="resourcesUrl" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>

<!-- CSS Imports-->
<link rel="stylesheet" type="text/css" media="screen"
	href="${resourcesUrl}/css/jquery/pepper-grinder/jquery-ui-1.8.12.custom.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="${resourcesUrl}/css/jqgrid/ui.jqgrid.css" />

<!-- JS Imports -->
<script type="text/javascript"
	src="${resourcesUrl}/js/jquery/jquery-1.5.2.min.js"></script>
<script type="text/javascript"
	src="${resourcesUrl}/js/jquery/jquery-ui-1.8.12.custom.min.js"></script>
<script type="text/javascript"
	src="${resourcesUrl}/js/datejs/date.js"></script>
<script type="text/javascript"
	src="${resourcesUrl}/js/jqgrid/grid.locale-en.js"></script>
<script type="text/javascript"
	src="${resourcesUrl}/js/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="${resourcesUrl}/js/admin.js"></script>

<link
	href="${pageContext.request.contextPath}/resources/css/admin.style.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/resources/css/admin.style_responsive.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/resources/css/admin.style_default.css"
	rel="stylesheet" id="style_color" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/admin.core.css" />
<title>Student Details</title>
</head>
<body>					
	<form id="couponForm">
		<div class="widget-body form">
			<div id="jqgrid">
				<table id="grid"></table>
			</div>
		</div>
		<div id="pager"></div>
	</form>
<script type="text/javascript">
 $(function () {
		  $("#grid").jqGrid({
		      url:'<%= request.getContextPath()%>/rest/student/getStudentDetails.json',
		   	  datatype: 'json',
		      mtype:'POST',
		      colNames:['ID',
		                'First Name',
		                'Last Name',
		                'Date of Birth',
		                'Qualification'],
		      colModel:[
		          {name:'studentId',index:'studentId', width:55, editable:true, editoptions: {readonly:true}},
		          {name:'studentFirstName',index:'studentFirstName', width:90, sortable:true, editable:true, editrules:{regex:/^[a-zA-Z\s]*$/, required:true}, editoptions: {readonly:false, 'onKeyUp':checkName}},
		          {name:'studentLastName',index:'studentLastName', width:90, sortable:true, editable:true, editrules:{regex:/^[a-zA-Z\s]*$/, required:true}, editoptions: {readonly:false, 'onKeyUp':checkName}},
		          {name:'studentBirthDate',index:'studentBirthDate', width:90, formatter:'date', formatoptions:{newformat: 'l, F d'}, editable:true, editrules:{required:true}},
		          {name:'studentQualification',index:'studentQualification', width:50, editable:true, editrules:{required:true}, 
			   			edittype:"select", formatter:'select', stype: 'select', 
			   			editoptions:{value:"1:B.Sc.Computer Science;2:M.Sc.Computer Science;3:B.C.A;4:M.C.A."},
			   			formatoptions:{value:"1:B.Sc.Computer Science;2:M.Sc.Computer Science;3:B.C.A;4:M.C.A."}, 
			   			searchoptions: {sopt:['eq'], value:":;1:B.Sc.Computer Science;2:M.Sc.Computer Science;3:B.C.A;4:M.C.A."}},
		      ],
		      loadComplete: function (data) {
		          
		      },
		      loadComplete: function () {
		    	  // remove error div if exist
		    	  $('#' + this.id + '_err').remove();
		      },
		      loadError: function (jqXHR, textStatus, errorThrown) {
		          alert('HTTP status code: ' + jqXHR.status + '\n' +
		                'textStatus: ' + textStatus + '\n' +
		                'errorThrown: ' + errorThrown);
		          alert('HTTP message body (jqXHR.responseText): ' + '\n' + jqXHR.responseText);
		      },
		      rowNum:20,
		      rowList:[20, 30, 40, 50, 60, 70, 80, 90, 100],
		      autowidth: true,
		      height:410,
		      rownumbers: true,
		      multisort: false,
		      pager: '#pager',
		      sortname: 'id',
		      viewrecords: true,
		      sortorder: "asc",
		      caption:'Student Details',
		      emptyrecords: "No records to view",
		      loadonce: false,
		      jsonReader : {
		          root: "rows",
		          page: "page",
		          total: "total",
		          records: "records",
		          repeatitems: false,
		          cell: "cell",
		          id: "id"
		      }		      
		  });
		
		  $("#grid").jqGrid('navGrid','#pager',
		          {edit:false,add:false,del:false,search:true},
		          { },
		          { },
		          { },
		          {
		              sopt:['eq', 'ne', 'bw', 'ew'],		              
		              closeOnEscape: true,
		              multipleSearch: false,
		              closeAfterSearch: true 
		          }
		  );
		
		  $("#grid").navButtonAdd('#pager',
		          {   
			  		  caption:"Add",
		              buttonicon:"ui-icon-plus",
		              onClickButton: addRow,
		              position: "last",
		              title:"add",
		              errorTextFormat: function (data) {
		            	  if (data.responseText.substr(0, 6) == "<html ") {
		            	  return jQuery(data.responseText).html();
		            	  }
		            	  else {
		            	  return data.responseText;
		            	  }
		            	  },
		              cursor: "pointer"
		          }
		  );
		
		  $("#grid").navButtonAdd('#pager',
		          {   
			  		  caption:"Edit",
		              buttonicon:"ui-icon-pencil",
		              onClickButton: editRow,
		              position: "last",
		              title:"edit",
		              cursor: "pointer"
		          }
		  );
		
		  $("#grid").navButtonAdd('#pager',
		     	  {     
			  	  	  caption:"Delete",
		          	  buttonicon:"ui-icon-trash",
		          	  onClickButton: deleteRow,
		          	  position: "last",
		          	  title:"delete",
		          	  cursor: "pointer"
		      }
		  );
		});
		
		function addRow() {
		  // Get the currently selected row
		  $("#grid").jqGrid('editGridRow','new',
		          {     
			          url: '<%= request.getContextPath()%>/rest/student/saveStudentDetails.json',
		              serializeEditData: function(data){
		                  data.id = 0;
		                  data.studentBirthDate = new Date(data.studentBirthDate).addDays(1).toISOString();
		                  return $.param(data);
		              },
		              recreateForm: true,
		              closeAfterAdd: true,
		              reloadAfterSubmit:true,
		              closeOnEscape:true,
		              beforeShowForm: function(form) {
		            	  $("#studentBirthDate").datepicker({
		                      changeMonth: true,
		                      changeYear: true
		                  });
		              },		              
		              afterSubmit : function(response, postdata)
		              {
		                  var result = eval('(' + response.responseText + ')');
		                  var errors = "";
		
		                  if (result.success == false) {
		                      for (var i = 0; i < result.message.length; i++) {
		                          errors +=  result.message[i] + "<br/>";
		                      }
		                  }  else {
		                	  $("#dialog").text('Student details have been added successfully');
		                      $("#dialog").dialog(
		                              {   title: 'Success',
		                                  modal: true,
		                                  buttons: {"Ok": function()  {
		                                      $(this).dialog("close");}
		                                  }
		                              });
		                  }
		                  // only used for adding new records
		                  var new_id = null;
		
		                  return [result.success, errors, new_id];
		              }
		          });
		}
		
		function editRow() {
		  // Get the currently selected row
		  var row = $("#grid").jqGrid('getGridParam','selrow');
		
		  if( row != null )
		      $("#grid").jqGrid('editGridRow',row,
		          {    	  url: '<%= request.getContextPath()%>/rest/student/updateStudentDetails.json',
		              	  serializeEditData: function(data){
		              	  data.studentBirthDate = new Date(data.studentBirthDate).addDays(1).toISOString();
		                  return $.param(data);
		              },
		              recreateForm: true,
		              closeAfterEdit: true,
		              reloadAfterSubmit:true,
		              closeOnEscape:true,
		              beforeShowForm: function(form) {
		                  $("#studentBirthDate").datepicker({
		                      changeMonth: true,
		                      changeYear: true
		                  });
		              },
		              afterSubmit : function(response, postdata)
		              {
		                  var result = eval('(' + response.responseText + ')');
		                  var errors = "";
		
		                  if (result.success == false) {
		                      for (var i = 0; i < result.message.length; i++) {
		                          errors +=  result.message[i] + "<br/>";
		                      }
		                  }  else {
		                	  $("#dialog").text('Student details have been edited successfully.');
		                      $("#dialog").dialog(
		                              {    title: 'Success',
		                                  modal: true,
		                                  buttons: {"Ok": function()  {
		                                      $(this).dialog("close");}
		                                  }
		                              });
		                  }
		
		                  return [result.success, errors, null];
		              }
		          });
		  else alert("Please select a row to edit");
		}
		
		function deleteRow() {
			  // Get the currently selected row
			  var row = $("#grid").jqGrid('getGridParam','selrow');
			  var celValue = $("#grid").jqGrid ('getCell', row, 'studentId');
			  // A pop-up dialog will appear to confirm the selected action
			  if( row != null )
			      $("#grid").jqGrid( 'delGridRow', row,
			            {
			          	url: '<%= request.getContextPath()%>/rest/student/deleteStudentDetails.json?studentId=' + celValue,
			          	
			                      recreateForm: true,
			                      beforeShowForm: function(form) {
			                        //change title
			                        $(".delmsg").replaceWith('<span style="white-space: pre;">' +
			                                'Are you sure?'+ '</span>');
			
			                        //hide arrows
			                        $('#pData').hide();
			                        $('#nData').hide();
			                      },
			                        reloadAfterSubmit:false,
			                        closeAfterDelete: true,
			                        closeOnEscape:true,
			                        afterSubmit : function(response, postdata) {
			                          var result = eval('(' + response.responseText + ')');
			                          var errors = "";
			
			                          if (result.success == false) {
			                              for (var i = 0; i < result.message.length; i++) {
			                                  errors +=  result.message[i] + "<br/>";
			                              }
			                          }  else {
			                              $("#dialog").text('Student details have been deleted successfully.');
			                              $("#dialog").dialog(
			                                      {   title: 'Success',
			                                          modal: true,
			                                          buttons: {"Ok": function()  {
														$(this).dialog("close");}
			                                          }
			                                      });
			                          }
			                          // only used for adding new records
			                          var new_id = null;
			                          return [result.success, errors, new_id];
			                      }
			            });
			  else alert("Please select a row to delete.");
			}
		
		
</script>
<div id="dialog" title="Feature not supported" style="display: none">
			<p>That feature is not supported.</p>
</div>
</body>
</html>