//Check function delete
function delStudent(delID) {

	var r = confirm("You try to DELETE Student Information.\nPress a button!\nEither OK or Cancel.");
	if (r == true) {
		var delStudent = JSON.stringify(delID);
		var jersey_url = "/dt2018105/stud/iStudent/delete";
		$.ajax({
			type: 'POST', // GET
			contentType: 'application/json',
			url: jersey_url,
			dataType: "json", // data type of response
			data: delStudent,
			error: function(xhr, status, error) {
				console.log(arguments);
				alert(" Can't do because: " + xhr);
			},
			success: view
		});
	} else {
		alert("You pressed Cancel!");
	}

}