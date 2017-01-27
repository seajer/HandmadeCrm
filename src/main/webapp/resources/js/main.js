jQuery(document).ready(function($) {
	
	$("#phone").mask("+380999999999", {
		placeholder : "+380xxxxxxxxx"
	});

});

jQuery(document).ready(function($) {
	
	$('body').on('click', '.addAnswer', function(){
		$("#answers").append('<input name="answer"><br/>');
	});
	$('.editAnswer').click(function(){
		$("#answers").append('<div><input name="answerId" type="hidden" value="0"/><input name="answerText"/><input type="button" class="remove" value="Remove"/></div>');
	});
	$('#answers').on('click','.remove', function(){
		 $(this).parents('div').eq(0).remove();
	});
	
	var customAnswer = false;
	
	$(".next").on('click',function(){
		console.log("next question");
		saveQuestion(customAnswer);
		next();
	});
	
	$(".prev").on('click',function(){
		console.log("prev question");
		saveQuestion(customAnswer);
		prev();
	});
	
	$(".nextTable").on('click',function(){
		console.log("next table");
		saveTable();
		next();
	});
	
	$(".prevTable").on('click',function(){
		console.log("prev table");
		saveTable();
		prev();
	});
	
	$(".customAnswer").click(function(){
		customAnswer = true;
		$("div.shown .customAnswer").hide();
		$("div.shown").append('<input class="custom_answer"/>');
	});
	
	$(".headerQuestion").on('click',function(){
		saveQuestion(customAnswer);
		$(".shown").hide();
		$(".shown").removeClass("shown").addClass("hidden");
		var a = $(this).val();
		$("div.hidden:eq("+(a-1)+")").removeClass("hidden").addClass("shown").show();
	});
	
	$(".questionType").change(function(){
		var tableIds = [15, 16, 17];
		var type = $(".questionType").val();
		if(containing(tableIds, type)){
			$("#creatingQuestion").attr("action", "new_table");
			$(".question").empty();
			$(".question").html("<table border='5'>" +
					"<tr><th>запитання &#8595; відповіді &#8594;</th><th><input name='answer'></th><th><input name='answer'></th></tr>" +
					"<tr><td><input name='question'></td><td></td><td></td</tr>" +
					"</table><input type='button' value='Add question' class='addRow'/>" +
					"<input type='button' value='Add answer' class='addColumn'/>");
		} else{
			$(".question").empty();
			$(".question").html("Text<input name='question'/><br>Type<div id='answers'>" +
					"<input name='answer'><br/></div>	<input type='button' value='Add answer' class='addAnswer'/>");
		}
	});
	
	$('body').on('click', 'input.addRow',function(){
		var element = "<tr><td><input name='question'></td>"
		var count = $("th").length -1;
		while(count > 0){
			count -= 1;
			element += "<td></td>"
		}
		element += "</tr>"
		$("table").append(element);
	});
	
	$('body').on('click', 'input.addColumn',function(){
		$("tr").first().append("<th><input name='answer'/></th>");
		$("tr").not(":eq(0)").append("<td></td>");
	});
	
	multiselect();
});

function prev(){
	$(".shown").hide();
	$(".shown").prev().removeClass("hidden").addClass("shown").show();
	$(".shown").last().removeClass("shown").addClass("hidden");
}

function next(){
	$(".shown").hide();
	$(".shown").next().removeClass("hidden").addClass("shown").show();
	$(".shown").first().removeClass("shown").addClass("hidden");
}

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

function sendJson(result, url) {
	
	var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
      xhr.setRequestHeader(header, token);
    });
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : url,
		dataType : 'json',
		timeout : 100000,
		data : JSON.stringify({
			result: result
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
	var result = {resultId : resultId, questionId: questionId, answers : answers}
	sendJson(result, "saveResultAnswers");
}

function containing(array, obj){
	var ret = false;
	$.each(array, function(index, value){
		if (value == obj){
			ret = true;
		}
	});
	return ret;
}

function saveTable(){
	var typeString;
	var type = $("div.shown .questionType").val();
	if(type == 'Таблиця з одним варіантом'){
		typeString = ":radio:checked";
	} else if(type == 'Таблиця з багатьма варіантами'){
		typeString = ":checked";
	} else{
		typeString = "";
	}
	var result = getRadioTableAnswer(typeString);
	sendJson(result, "saveTable");
}

function getRadioTableAnswer(typeString){
	var tableId = $("div.shown input[name='questionId']").val();
	var resultId = $("input[name='resultId']").val();
	var questionId = []
	$('div.shown .tableQuestionId').each(function(){
		questionId.push($(this).val());
	});
	var result = [];
	$.each(questionId, function(index, value){
		var answers = [];
		var str = '.answer' + value + typeString;
		$(str).each(function(){
			var answ = $(this).val()
			if(typeString == ""){
				answ += "%";
			}
			answers.push(answ);
		})
		result.push({questionId: value, answers: answers});
	})
	return {resultId: resultId, results: result };
}