/* when document is ready */
$(function() {
	
	$("#chargeForm").validate({
		rules: {
			creditcard: {
				required: true,
				digits: true
			},
			amount: {
				required: true,
				number: true,
				min: 0
			}
		}
	});
});