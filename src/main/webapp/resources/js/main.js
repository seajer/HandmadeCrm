jQuery(document).ready(function($) {
	
	$("#phone").mask("+380999999999", {
		placeholder : "+380xxxxxxxxx"
	});

});

jQuery(document).ready(function($) {
	
	// add new answer (new answer)
	$('body').on('click', '.addAnswer', function(){
		$("#answers").append('<input name="answer"><br/>');
	});

	// add new answer (edit answer)
	$('.editAnswer').click(function(){
		$("#answers").append('<div><input name="answerId" type="hidden" value="0"/><input name="answerText"/><input type="button" class="remove" value="Remove"/></div>');
	});

	// remove answer (new or edit question)
	$('#answers').on('click','.remove', function(){
		 $(this).parents('div').eq(0).remove();
	});
	
	var customAnswer = false;
	
	// move from question to next question (new or edit poll)
	$(".next").on('click',function(){
		saveQuestion(customAnswer);
		next();
	});
	
	// move from question to previous question (new or edit poll)
	$(".prev").on('click',function(){
		saveQuestion(customAnswer);
		prev();
	});
	
	// move from table to next question (new or edit poll)
	$(".nextTable").on('click',function(){
		saveTable();
		next();
	});
	
	// move from table to previous question (new or edit poll)
	$(".prevTable").on('click',function(){
		saveTable();
		prev();
	});
	
	// add custom answer field (new or edit pool)
	$(".customAnswer").click(function(){
		customAnswer = true;
		$("div.shown .customAnswer").hide();
		$("div.shown").append('<input class="custom_answer"/>');
	});
	
	// move to question with such number (new pool)
	$(".headerQuestion").on('click',function(){
		var typeId = $("div.shown input.questionType").val();
		if(containing([8, 9, 10], typeId)){
			saveTable();
		} else {
			saveQuestion(customAnswer);
		}
		$(".shown").hide();
		$(".shown").removeClass("shown").addClass("hidden");
		var a = $(this).val();
		$("div.hidden:eq("+(a-1)+")").removeClass("hidden").addClass("shown").show();
	});
	
	//clear form and set there new HTML tags (for table or question)
	$(".questionType").change(function(){
		var tableIds = [8, 9, 10];
		var type = $(".questionType").val();
		if(containing(tableIds, type)){
			$("#creatingQuestion").attr("action", "new_table");
			$(".question").empty();
			$(".question").html("<table border='5'>" +
					"<tr><th>запитання &#8595; відповіді &#8594;</th><th><input type='hidden' name='answerId'/>" +
					"<input name='answer'></th><th><input type='hidden' name='answerId'/><input name='answer'></th></tr>" +
					"<tr><td><input type='hidden' name='questionsId' class='tableQuestionId'><input name='question'>" +
					"</td><td></td><td></td</tr>" +
					"</table><input type='button' value='Add question' class='addRow'/>" +
					"<input type='button' value='Add answer' class='addColumn'/>");
		} else if(type == 7){
			$("#creatingQuestion").attr("action", "new_open");
			$(".question").empty();
			$(".question").html("Text<input name='question'/>");
		}else{
			$("#creatingQuestion").attr("action", "new_question");
			$(".question").empty();
			$(".question").html("Text<input name='question'/><br>Answers<div id='answers'>" +
					"<input name='answer'><br/></div><input type='button' value='Add answer' class='addAnswer'/>");
		}
	});
	
	//add row to table on creating table (new question)
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
	
	//add column to table on creating table (new question)
	$('body').on('click', 'input.addColumn',function(){
		$("tr").first().append("<th><input name='answer'/></th>");
		$("tr").not(":eq(0)").append("<td></td>");
	});
	
	// add new container for mapping customer database fields
	$("#addCDOField").on('click', function(){
		var element = "<div><select name='paramName'>";
		var values = [];
		$("#firstSelect option").each(function(){
			values.push($(this).val());
		})
		$.each(values, function(index, value){
			element += "<option>"+value+"</option>";
		})
		element += "</select><input type='number' name='paramNumber' value='0'></div>";
		$(".customertDB-ourDB-chain").append(element);
	});
	
	//change client result status
	$(".changeStatus").on('click', function(){
		var statusId = $('.status').val();
		var resultId = $('#resultId').val();
		sendJsonGet("changeStatus_s="+statusId+"_r="+resultId);
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

function sendJsonPost(result, url) {
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

function sendJsonGet(url){
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : url,
		dataType : 'json',
		timeout : 100000,
		success : function() {
			console.log("SUCCESS");
		},
		error : function(e) {
			console.log("ERROR", e);
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
	var answers = [];
	var resultId = $("input[name='resultId']").val();
	var questionId = $('div.shown input[name="questionId"]').val();
	if(type == 1 || type == 2 || type == 3 || type == 4){
		answers = getCheckedAnswers();
	} else if(type == 5 || type == 6){
		answers = getPersentageAnswers();
	}else{
		answers.push($(".openAnswer").val());
	}
	if(customAnswer){
		answers = answers +", "+ getStringAnswers();
	}
	customAnswer = false;
	$("div.hidden .customAnswer").show();
	var result = {resultId : resultId, questionId: questionId, answers : answers}
	sendJsonPost(result, "saveResultAnswers");
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
	if(type == 8){
		typeString = ":radio:checked";
	} else if(type == 9){
		typeString = ":checked";
	} else{
		typeString = "";
	}
	var result = getRadioTableAnswer(typeString);
	sendJsonPost(result, "saveTable");
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