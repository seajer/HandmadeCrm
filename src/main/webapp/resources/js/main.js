jQuery(document).ready(function($) {
	
	$("#phone").mask("+380999999999", {
		placeholder : "+380xxxxxxxxx"
	});
	

});

jQuery(document).ready(function($) {
	
	$('.addAnswer').click(function(){
		$("#answers").append('<input name="answer"><br/>');
	});
});