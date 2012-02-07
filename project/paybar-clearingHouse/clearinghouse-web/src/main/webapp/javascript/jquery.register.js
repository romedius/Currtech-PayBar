/* when document is ready */
$(function() {
	
	$("#Registerform").validate({
		rules: {
			firstname: "required",
			lastname: "required",
			street: "required",
			postcode: "required number",
			city: "required",
			username: {
				required: true,
				minlength: 2
			},
			password1: {
				required: true,
				minlength: 5
			},
			password2: {
				required: true,
				minlength: 5,
				equalTo: "#password1"
			}
			/*
			 * , email: { required: true, email: true },
			 */
		},
		messages: {
			firstname: "Please enter your firstname",
			lastname: "Please enter your lastname",
			username: {
				required: "Please enter a username",
				minlength: "Your username must consist of at least 2 characters"
			},
			password1: {
				required: "Please provide a password",
				minlength: "Your password must be at least 5 characters long"
			},
			password2: {
				required: "Please provide a password",
				minlength: "Your password must be at least 5 characters long",
				equalTo: "Please enter the same password as above"
			},
			// email: "Please enter a valid email address",
		}
	});
	
	$("#Registerform").submit(function() {
		if ($("#Registerform").valid()){
			
			var dataToSend = "{\"firstName\":\"" + $('#firstname').val() +
			"\"," + "\"sureName\":\"" + $('#lastname').val() + "\"," +
			"\"userName\":\"" + $('#username').val() + "\"," +
			"\"password\":\"" + $('#password1').val() + "\"," +
			"\"phoneNumber\":\"23423234\"," + "\"locationHash\":\"tirol\"," +
			"\"moveToLocationHash\":null," + "\"credit\":100," +
			"\"coupons\":[]," + "\"securityKey\":\"sadfwaerawr\"}";
			
			$.ajax({
				  type: 'POST',
				  url: 'http://localhost:8080/clearinghouse/rest/account/create',
				  data: dataToSend,
				  contentType: 'application/json; charset=utf-8',
				  dataType: 'json',
				  success: function(json){
					  alert(json.message);
				  },
				  error: function(jqXHR, textStatus, errorThrown){
					  alert(jqXHR);
				  }
			});
			
		} else {
			alert("The formular is not valid");
		}
		return false;
		
	});
});