//insert data student
//function execInsertData()
//{
//	var jersey_url = "/dt2018105/stud/iStudent/insert";
//	$.ajax({
//		type: 'POST', // GET
//		contentType: 'application/json',
//		url: jersey_url,
//		dataType: "json", // data type of response
//		data: insertFormToJSON(),
//		error: function(xhr, status, error) {
//			console.log(arguments);
//			alert(" Can't do because: " + xhr);
//		},
//		success: renderDetailsInsert
//	});
//}

//function insertFormToJSON() 
//{
//	var name = $("#name").val();
//	var dob = $("#dob").val();
//	var mark_1 = $("#mark_1").val();
//	var mark_2 = $("#mark_2").val();
//	var mark_3 = $("#mark_3").val();
//	var student = JSON.stringify({
//		"name":name,
//		"dob":dob,
//		"rollNo":"0",
//		"mark_1":mark_1,
//		"mark_2":mark_2,
//		"mark_3":mark_3
//	});
//	return student;
//}

function execInsertDataImage()
{
	var jersey_url = "/dt2018105/stud/iStudent/insertImage";

	$.ajax({
		type: 'POST', // GET
		url: jersey_url,
		data: insertFormToJSONImage(),
		cache : false,
		contentType : false,
		processData : false,
		error : function(jqXHR, textStatus, errorThrown) {
			$("#messages").append("<li style='color: red;'>" + textStatus + " apa "+ errorThrown.responseText + "</li>");
		},
		success: renderDetailsInsert
	});
}

//insert data student
function insertFormToJSONImage() 
{
	var name = $("#name").val();
	var dob = $("#dob").val();
	var mark_1 = $("#mark_1").val();
	var mark_2 = $("#mark_2").val();
	var mark_3 = $("#mark_3").val();

	var fileInput = document.getElementById('uplPhoto');
	var file = fileInput.files[0];
	var formData = new FormData();

	formData.append('name',name);
	formData.append('dob',dob);
	formData.append('rollNo','0');
	formData.append('mark_1', mark_1);
	formData.append('mark_2', mark_2);
	formData.append('mark_3', mark_3);
	formData.append('file', file);

	return formData;
}


//Remnder data Student
function renderDetailsInsert(data)
{	
	$("#resultInsert").html(" Result Query : New Record Inserted Successfully!!! ");

}

//parseImageExample
//function parseImg() 
//{
//	var fileInput = document.getElementById('uplImg');
//	var file = fileInput.files[0];
//	var formData = new FormData();
//	formData.append('file', file);
//
//	$.ajax({
//		url : '/dt2018105/upl/upload/image',
//		type : 'POST',
//		data : formData,
//		cache : false,
//		contentType : false,
//		processData : false,
//		success : function(data, textStatus, jqXHR) {
//			var message = jqXHR.responseText + " " + textStatus;
//			$("#messages").append("<li>" + message + "</li>");
//		},
//		error : function(jqXHR, textStatus, errorThrown) {
//			$("#messages").append("<li style='color: red;'>" + textStatus + " apa "+ errorThrown.responseText + "</li>");
//		}
//	});
//}



