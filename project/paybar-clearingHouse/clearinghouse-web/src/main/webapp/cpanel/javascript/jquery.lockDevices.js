/* when document is ready */
$(function() {
	
	$('#lockDevice').click(function() {
	    return window.confirm(this.title || 'Are you sure to lock your devices?');
	});
});