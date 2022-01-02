let getQuizTemplateAPI = "/w/api/v1/quiz/quizTmp?questionType=";

// Call Ajax.
$('#question-type').on('change', function() {
	console.log(this.value);
	let questionType = window.btoa(unescape(encodeURIComponent(this.value)));
	$.ajax({
		url : getQuizTemplateAPI + questionType,
		type : "GET",
		success : function(data) {
			console.log("data: ", data);
		},
		error : function(xhr) {
			// modal.openOneModalMessage("Not success");
			console.log("Not success");
		}
	});
});