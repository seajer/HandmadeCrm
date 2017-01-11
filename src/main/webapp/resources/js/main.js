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
	multiselect();
	
});

function multiselect() {
	var config = {
		'.chosen-select' : {},
		'.chosen-select-deselect' : {
			allow_single_deselect : true
		},
		'.chosen-select-no-single' : {
			disable_search_threshold : 10
		},
		'.chosen-select-no-results' : {
			no_results_text : 'Oops, nothing found!'
		},
		'.chosen-select-width' : {
			width : "95%"
		}
	}
	for ( var selector in config) {
		$(selector).chosen(config[selector]);
	}
}