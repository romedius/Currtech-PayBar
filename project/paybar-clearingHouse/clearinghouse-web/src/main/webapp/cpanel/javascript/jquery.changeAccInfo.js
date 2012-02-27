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
			}
		}
	});
});