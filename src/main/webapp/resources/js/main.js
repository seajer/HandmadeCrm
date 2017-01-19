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
	
	var customAnswer = false;
	
	$(".next").on('click',function(){
		console.log("next");
		saveQuestion(customAnswer);
		$(".shown").hide();
		$(".shown").next().removeClass("hidden").addClass("shown").show();
		$(".shown").first().removeClass("shown").addClass("hidden");
	});
	
	$(".prev").on('click',function(){
		console.log("prev");
		saveQuestion(customAnswer);
		$(".shown").hide();
		$(".shown").prev().removeClass("hidden").addClass("shown").show();
		$(".shown").last().removeClass("shown").addClass("hidden");
	});
	
	$(".customAnswer").click(function(){
		customAnswer = true;
		$("div.shown .customAnswer").hide();
		$("div.shown").append('<input class="custom_answer"/>');
	});
	
	$(".headerQuestion").on('click',function(){
		console.log("headerQuestion");
		saveQuestion(customAnswer);
		$(".shown").hide();
		$(".shown").removeClass("shown").addClass("hidden");
		var a = $(this).val();
		$("div.hidden:eq("+(a-1)+")").removeClass("hidden").addClass("shown").show();
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

function sendAnswers(resultId, answers, questionId) {
	
	var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
      xhr.setRequestHeader(header, token);
    });
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "saveResultAnswers",
		dataType : 'json',
		timeout : 100000,
		data : JSON.stringify({
			resultId : resultId,
			questionId: questionId,
			answers : answers
		}),
		success : function(data) {
			console.log("SUCCESS: ", data);
		},
		error : function(e) {
			console.log("ERROR: ", e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
	
}

function getCheckedAnswers(){
	var elements = [];
	$("div.shown input:checked").each(function(){
		elements.push($(this).val());
	});
	return elements;
}
function getPersentageAnswers(){
	var elements = [];
	$("div.shown input.persentage").each(function(){
		var text = $(this).parent().children(".text").text();
		console.log("text = " + text);
		elements.push($(this).val()+"% "+text);
	});
	return elements;
}
function getStringAnswers(){
	var elements = [];
	$("div.shown input.custom_answer").each(function(){
		elements.push($(this).val());
	});
	return elements;
}

function saveQuestion(customAnswer){
	var type = $("div.shown .questionType").val();
	console.log("isTypeCnanged = " + customAnswer);
	var answers = [];
	var resultId = $("input[name='resultId']").val();
	var questionId = $('div.shown input[name="questionId"]').val();
	if(type == 'Одна відповідь' || type == 'Кілька відповідей' || type == 'Одна вдповідь з можливістю обрати свій варіант' || type == 'Кілька відповідей з можливістю обрати свій варіант'){
		answers = getCheckedAnswers();
	} else if(type == 'Процентре співвідношення представлених варіантів' || type == 'Процентне співвідношення зі своїм варіантом'){
		answers = getPersentageAnswers();
	}else{
	}
	if(customAnswer){
		answers = answers +", "+ getStringAnswers();
	}
	customAnswer = false;
	$("div.hidden .customAnswer").show();
	sendAnswers(resultId, answers, questionId);
}