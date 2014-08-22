/**
 * 
 */

   
/**
* JQGrid Validations
*/
		function checkName(value, colname) {
			 if (value == '') 
				   return [false, colname + " can\'t be left blank."];
			 else 
				   return [true,""];
			 }
		 function checkValidFrom(value, colname) {
			 if (value == '') 
				   return [false, colname + " must be selected."];
			 else 
				   return [true,""];
			 }
		 function checkValidTo(value, colname) {
			 if (value == '') 
				   return [false, colname + " must be selected."];
			 else 
				   return [true,""];
			 }
		 function checkDetails(value, colname) {
			 if (value == '') 
				   return [false, colname + " can\'t be left blank."];
			 else 
				   return [true,""];
			 }
		 function goBack() {
			    window.history.back();
			 }