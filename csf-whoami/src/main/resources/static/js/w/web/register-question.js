let getQuizTemplateAPI = "/w/api/v1/quiz/quizTmp?questionType=";

// Call Ajax.
$('#question-type').on('change', function() {
	console.log(this.value);
	let questionType = window.btoa(unescape(encodeURIComponent(this.value)));
	$.ajax({
		url : getQuizTemplateAPI + questionType,
		type : "GET",
		success : function(data) {
			if(data.data){
				$("div.quiz-content").html(data.data.temp_content);
				setRequired(questionType);
			}
		},
		error : function(xhr) {
			// modal.openOneModalMessage("Not success");
			console.log("Not success");
		}
	});
});

function setRequired(questionType){
	
}