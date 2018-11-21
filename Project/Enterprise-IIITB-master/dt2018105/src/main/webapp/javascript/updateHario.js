function editStudent(roolNo) {
				var date = document.getElementById('resultView').rows.namedItem('update'+roolNo).cells.item(2).innerHTML.substring(6, 10) + "-" ;
				var date = date + document.getElementById('resultView').rows.namedItem('update'+roolNo).cells.item(2).innerHTML.substring(3, 5) + "-" ;
				var date = date + document.getElementById('resultView').rows.namedItem('update'+roolNo).cells.item(2).innerHTML.substring(0, 2) ;
				
				var table = "<tr><td colspan='2'><b>Update Data Student With Roll Number "+ roolNo + "</b></td></tr>";
				table = table + "<tr><td>Name </td><td><input type='text' id='updateName' value='" + document.getElementById('resultView').rows.namedItem('update'+roolNo).cells.item(1).innerHTML + "'></td></tr>";
				table = table + "<tr><td>Date of Birth</td><td><input type='date' id='updateDob' value='" + date  +"'></td></tr>";
				table = table + "<tr><td>Mark In Physic</td><td><input type='number' id='updatemark_1' value='"+document.getElementById('resultView').rows.namedItem('update'+roolNo).cells.item(3).innerHTML +"'></td></tr>";
				table = table + "<tr><td>Mark In Chemistry</td><td><input type='number' id='updatemark_2' value='"+document.getElementById('resultView').rows.namedItem('update'+roolNo).cells.item(4).innerHTML +"' ></td></tr>";
				table = table + "<tr><td>Mark In Math</td><td><input type='number' id='updatemark_3' value='"+document.getElementById('resultView').rows.namedItem('update'+roolNo).cells.item(5).innerHTML +"'></td></tr>";
				table = table + "<tr><td>Upload Photo</td><td><input id='updateuplPhoto' type='file' name='file' size='20'  accept=''.png, .jpg, .jpeg' / >"+document.getElementById('resultView').rows.namedItem('update'+roolNo).cells.item(8).innerHTML+"</td></tr>";
				table = table + "<tr><td  style='text-align:right'><p></p><button onclick='UpdateButtonStudent("+roolNo+")' id='updatebtn' type='button' class='btn btn-default'>Update</button></td>";
				table = table + "<td  style='text-align:left'><p></p><button onclick='cncleUpdateButton()' id='cnclupdatebtn' type='button' class='btn btn-default'>Cancel</button></td></tr>";
				$("#studentUpdate").html(table);
				document.getElementById("updateName").focus();
}

function UpdateButtonStudent(roolNo){
	var r = confirm("You try to UPDATE Student Information.\nPress a button!\nEither OK or Cancel.");
	if (r == true) {
		if (document.getElementById("updateName").value == ""){
	    	alert("Please enter Name");	
	    } else if (document.getElementById("updateDob").value == ""){
	    	alert("Please enter Date of Birth");
	    } else if (document.getElementById("updatemark_1").value == "" || document.getElementById("updatemark_1").value < 0 || document.getElementById("updatemark_1").value > 10){
	    	alert("Please enter Mark in Physic, range value 0 - 10");
	    } else if (document.getElementById("updatemark_2").value == "" || document.getElementById("updatemark_2").value < 0 || document.getElementById("updatemark_2").value > 10){
	    	alert("Please enter Mark in Chemistry, range value 0 - 10");
	    } else if (document.getElementById("updatemark_3").value == "" || document.getElementById("updatemark_3").value < 0 || document.getElementById("updatemark_3").value > 10){
	    	alert("Please enter Mark in Math, range value 0 - 10");
	    }else {
			execUpdateStudent(roolNo);
	    }
	}else {
	    alert("You pressed Cancel!");
	}
};

//update data student
function execUpdateStudent(roolNo)
{
    var jersey_url = "/dt2018105/stud/iStudent/updateStudent";
    $.ajax({
        type: 'POST', // GET
        url: jersey_url,
        data: updateFormToJSONImage(roolNo),
        cache : false,
        contentType : false,
        processData : false,
        error : function(jqXHR, textStatus, errorThrown) {
            $("#messages").append("<li style='color: red;'>" + textStatus + " apa "+ errorThrown.responseText + "</li>");
        },
        success: view
    });
}

function updateFormToJSONImage(roolNo) 
{
    var name = $("#updateName").val();
    var dob = $("#updateDob").val();
    var mark_1 = $("#updatemark_1").val();
    var mark_2 = $("#updatemark_2").val();
    var mark_3 = $("#updatemark_3").val();
    alert($("#lastImg").val());
    var fileInput = document.getElementById('updateuplPhoto');
	var file = fileInput.files[0];
	var formData = new FormData();
	
	formData.append('name',name);
	formData.append('dob',dob);
	formData.append('rollNo',roolNo);
	formData.append('mark_1', mark_1);
	formData.append('mark_2', mark_2);
	formData.append('mark_3', mark_3);
	formData.append('file', file);

	return formData;
}

function cncleUpdateButton(){
	view();
};
