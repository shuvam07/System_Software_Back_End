
//viewfunction
function view() 
{	
	$("#studentUpdate").html("");
	var jersey_url = "/dt2018105/stud/iStudent/view";
	$.ajax({
		type: 'GET', // GET
		contentType: 'application/json',
		url: jersey_url,
		error: function(xhr, status, error) {
			console.log(arguments);
			alert(" Can't do because: " + xhr);
		},
		success: parseDatatoHTML
	});

}

//parser Data View
function parseDatatoHTML(data) {
	$("#resultView").html(data);

	var table = $('#resultView').DataTable({"destroy": true,});
	table.draw();
};

//information function
function information() 
{	
	var jersey_url = "/dt2018105/stud/iStudent/information";
	$.ajax({
		type: 'POST', // GET
		contentType: 'application/json',
		url: jersey_url,
		data : document.getElementById("gradeOption").value,
		error: function(xhr, status, error) {
			console.log(arguments);
			alert(" Can't do because: " + xhr);
		},
		success: parseDatatoHTMLInformation
	});

}

//parser Data Inforamtion
function parseDatatoHTMLInformation(data) {

	$("#resultSearch").html(data);

	reloadCarousel();
	//$("#resultSearch").amazingcarousel();
	//var table = $('#resultSearch').DataTable({"destroy": true,});
	//table.draw();
};

