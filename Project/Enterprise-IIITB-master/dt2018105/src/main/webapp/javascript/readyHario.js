$(document).ready(function() 
	{
	$('#clearGradeStudent').click(function()
	{
						$("#resultGrade").html("");
	});
	
	$('#filterCarousel').click(function(){
		information();
	});
	
	$('#submitRange').click(function() {
		$.fn.dataTable.ext.search.push(
				function( settings, data, dataIndex ) {
					var min = parseInt( $('#Rollmin').val(), 10 );
					var max = parseInt( $('#Rollmax').val(), 10 );
					var value = parseFloat( data[0] ) || 0; // use data for the age column

					if ( ( isNaN( min ) && isNaN( max ) ) ||
							( isNaN( min ) && value <= max ) ||
							( min <= value   && isNaN( max ) ) ||
							( min <= value   && value <= max ) )
					{
						return true;
					}
					return false;
				}
		);
		var table = $('#resultView').DataTable({"destroy": true,});
		table.draw();
	});

	//insert function
	$('#insertbtn').click(function()
			{
		if (document.getElementById("name").value == ""){
			alert("Please enter Name");	
		} else if (document.getElementById("dob").value == ""){
			alert("Please enter Date of Birth");
		} else if (document.getElementById("mark_1").value == "" || document.getElementById("mark_1").value < 0 || document.getElementById("mark_1").value > 10){
			alert("Please enter Mark in Physic, range value 0 - 10");
		} else if (document.getElementById("mark_2").value == "" || document.getElementById("mark_2").value < 0 || document.getElementById("mark_2").value > 10){
			alert("Please enter Mark in Chemistry, range value 0 - 10");
		} else if (document.getElementById("mark_3").value == "" || document.getElementById("mark_3").value < 0 || document.getElementById("mark_3").value > 10){
			alert("Please enter Mark in Math, range value 0 - 10");
		}else if (document.getElementById('uplPhoto').value == ""){
			alert("Please choose file to upload");
		}else {
			execInsertDataImage();
		}
	});

	//grade function
	$('#gradeStudent').click(function()
			{
		if (document.getElementById("rollNoSrc").value == "")
			alert("Please enter roll Number");	
		else 
			execGradeData();

	});

	//uploadButtonExample
//	$('#uploadImgBtn').click(function()
//	{
//		if(document.getElementById('uplImg'))
//			parseImg();
//	});
});









