//Remnder grade Student
function renderDetailsGrade(data)
{	
	$("#resultGrade").html(data);
}

//grade Student
function execGradeData()
{
	var rollNoSrc = $("#rollNoSrc").val();
	var rool_No = JSON.stringify(rollNoSrc);
	var jersey_url = "/dt2018105/stud/iStudent/grade";
	$.ajax({
		type: 'POST', // GET
		contentType: 'application/json',
		url: jersey_url,
		data: rool_No,
		error: function(xhr, status, error) {
			console.log(arguments);
			alert(" Can't do because: " + xhr +error);
		},
		success: renderDetailsGrade
	});
}

