/* when document is ready */
$(function() {
	
	$("#changeForm").validate({
		rules: {
			firstname: {
				required: true,
				digits: false
			},
			surename: {
				required: true,
				digits: false
			},
			address: {
				required: true
			},
			password2: {
				equalTo: "#password1"
			},
			phoneNumber: {
				required: true,
				digits: true
			}
		}
	});
	
	$("#changeForm").submit(function() {
		var pw1 = $("#password1").val();
		var pw2 = $("#password2").val();
		if (pw1 == "" && pw2 == "" ||
			pw1.length > 5 && pw1 == pw2 ){
			$("#passfield").html("");
			return true;
			
		} else if (pw1 == pw2){
			$("#passfield").html("wrong or less than 6 characters");
			return false;
		} else {
			$("#passfield").html("");
			return false;
		}
	});
});