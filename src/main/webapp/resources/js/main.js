jQuery(document).ready(function($) {
	
	$("#phone").mask("+380999999999", {
		placeholder : "+380xxxxxxxxx"
	});
	

});

jQuery(document).ready(function($) {
	
	$('.addAnswer').click(function(){
		$("#answers").append('<input name="answer"><br/>');
	});
	$('.editAnswer').click(function(){
		$("#answers").append('<div><input name="answerId" type="hidden" value="0"/><input name="answerText"/> <a href="#" class="remove">Remove</a></div>');
	});
	$('#answers').on('click','a.remove', function(){
		 $(this).parents('div').eq(0).remove();
	});
	
});