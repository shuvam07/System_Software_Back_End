define(['jquery','handlebars','slick'], function($, hbs ,slick){

	function init(){
		$('#form-submit').click(addDetails);
		$('#roll-submit').click(getDetails);
		$('#submit').click(getDetailsToUpdate);
		$('#submitRoll').click(getRecords);
	}

	var baseUrl = "http://localhost:8080/hello/webapi/students/student/"
	var baseImgUrl = "/hello/client/images/"




	function sortData(res,addRecordTemplate){
		var result= []
		var imgCarousel = []
		var sortby = $("#sortDropDown").val();
		var data = []
		for (i in res){
			obj = res[i]
			for(key in obj){
				if(key == sortby){
		            data.push([i,obj[key]])
		        if(key == "imgUrl")
		        	imgCarousel.push(obj[key])
		                       }
		    }
		}

		data.sort(function(a, b)
		{
			if(typeof(a[1])!='number'){
				var x=a[1].toLowerCase(),
					y=b[1].toLowerCase();
				return x<y ? -1 : x>y ? 1 : 0;
			}
			else{
				return a[1] > b[1];
			}
		});

		for(i in data){
			result.push(res[data[i][0]])
		}
		// slides(imgCarousel)
		addRecordTemplate(result)
		showCarousel(res)
	}

	function showCarousel(res){
		var self =this;
		$.get('http://localhost:8080/hello/client/templates/imgCarouselHB.html', function(data) {
		    var indexTemplate = data;
			var compiledTemplate = hbs.compile(indexTemplate);
			var records = {result:res}
			// var res = "{\"chem\": \""+self.res.chem+"\",\"maths\": \""+self.res.maths+"\",\"name\": \""+self.res.name+"\",\"phy\": \""+self.res.phy+"\",\"rollNo\": \""+self.res.rollNo+"\",\"imgUrl\":\""+self.res.imgUrl+"\"}";
			var temp = compiledTemplate(records);
			$(".carousel").empty();
			$(".carousel").append(temp);
		});
	}


	function addRecordTemplate(res){
		this.res = res;
		if(res === undefined){
			$(".recordContainer").empty();
			$(".recordContainer").append("<div class =\"failure-data\">Sorry!! No Data Found</div>");
			$('.recordContainer').fadeIn().delay(3000).fadeOut();
			return;
		}
		var self =this;
		$.get('http://localhost:8080/hello/client/templates/recordsHB.html', function(data) {
		    var indexTemplate = data;
			var compiledTemplate = hbs.compile(indexTemplate);
			var records = {result:res}
			// var res = "{\"chem\": \""+self.res.chem+"\",\"maths\": \""+self.res.maths+"\",\"name\": \""+self.res.name+"\",\"phy\": \""+self.res.phy+"\",\"rollNo\": \""+self.res.rollNo+"\",\"imgUrl\":\""+self.res.imgUrl+"\"}";
			var temp = compiledTemplate(records);
			$(".recordContainer").empty();
			$(".recordContainer").append(temp);
		});
	};

	function getRecords(e){
		var fromRoll = $("#fromRoll").val();
		var toRoll = $("#toRoll").val();

		var url = baseUrl+"getRecords";
		$.ajax({
		  data: { 
	        fromRoll: fromRoll,
	        toRoll: toRoll 
		   },
		   cache: false,
		   processData: true,
		   dataType: 'json',
		  url: url,
		  crossDomain: true,
		  type: 'GET',
		  success: function(res){
		  		res = sortData(res,addRecordTemplate);
		  		// addRecordTemplate(res);
		  }
		});
	}

	function validMarks(x){
		if(isNaN(x) || x < 0 || x > 100) {
			return false;
		}
		return true;
	}

	function calcAge(dateString) {
		var today = new Date();
		var birthDate = new Date(dateString);
		var age = today.getFullYear() - birthDate.getFullYear();
		return age;
	}

	function checkValidation(formData){
		if(!$("#name").val().match(/^[a-zA-Z ]+$/)){
			alert("Name Invalid");
			return false;
		}

		if(isNaN($("#roll").val()) || $("#roll").val() == 0){
			alert("Roll Invalid - Must be a non-zero natural number")
			return false;
		}
		if(calcAge($("#dob").val())<18){
			alert("Age invalid - Must be greater than 18");
			return false;
		}

		var x = $("#phy").val()
		if(!validMarks(x)) {
			alert("Physics Marks Invalid - Must be a vlaue form 0 to 100")
			return false;
		}
		var x = $("#chem").val()
		if(!validMarks(x)) {
			alert("Chemistry Marks Invalid - Must be a vlaue form 0 to 100")
			return false;
		}
		var x = $("#maths").val()
		if(!validMarks(x)) {
			alert("Maths Marks Invalid - Must be a vlaue form 0 to 100")
			return false;
		}
		if($('#imageUrl').val()==""){
			alert("No Image Found")
			return false;
		}
		return true;

	}

	function addDetails(e){
		e.preventDefault();
		e.stopPropagation();

		var formData = new FormData($('#formDetails')[0]);
		if(!checkValidation(formData))
			return;
		$.ajax({
		  type: "POST",
		  enctype: 'multipart/form-data',
		  url: baseUrl+"addData",
		  contentType: false,
          cache: false,
		  data: formData,
		  processData: false,
		  success: function(res){
		  		if(res===undefined){
		  			$(".failure-data").empty();
				  	$(".failure-data").append("<p>Error while Submitting Data(Duplicate Entry)</p>");
				  	$('.failure-data').fadeIn().delay(3000).fadeOut();
		  		}
		  		else{
		  			$(".success-data").empty();
				  	$(".success-data").append("<p>Data Submitted Successfully</p>");
				  	$('.success-data').fadeIn().delay(3000).fadeOut();
		  		}
		  }
		});
	}

	function updateDetails(e){
		e.preventDefault();
		e.stopPropagation();
		var name = $("#name").val();
		var roll = $("#roll").val();
		var dob = $("#dob").val();
		var phy = $("#phy").val();
		var chem = $("#chem").val();
		var maths = $("#maths").val();
		var grade = $("#grade").val();
		var imgUrl = $("#imgUrl").val();
		// if(imgUrl != undefined){
		// 	imgUrl = $("#imageUrl").val();
		// 	imgUrl = baseImgUrl + imgUrl.split("\\").pop();
		// }
		var post_data = {
			"rollNo" : roll,
			"dob" : dob,
		  	"name" : name,
		  	"phy" : phy,
		  	"chem" : chem,
		  	"maths" : maths,
		  	"grade" : grade,
		  	"imgUrl" : imgUrl
		  };
		$.ajax({
		  type: "POST",
		  url: baseUrl+"updateData",
		  headers: {
		  	'Content-Type': 'application/json',		  	
		  },
		  data: JSON.stringify(post_data),
		  success: function(res){
		  		if(res===undefined){
		  			$(".failure-data").empty();
				  	$(".failure-data").append("<p>Error while Updating Data</p>");
				  	$('.failure-data').fadeIn().delay(3000).fadeOut();
		  		}
		  		else{
		  			$(".success-data").empty();
				  	$(".success-data").append("<p>Data Updated Successfully</p>");
				  	$('.success-data').fadeIn().delay(3000).fadeOut();
		  		}
		  },
		  dataType: "json"
		});
	}

	function getDetails(){
		var roll = $("#rollNo").val();
		var url = baseUrl+roll;
		$.ajax({
		  dataType: "json",
		  headers: {
	        'Content-Type': 'application/json'		    
	      },
		  url: url,
		  crossDomain: true,
		  type: 'GET',
		  success: function(res){
		  	addTemplate(res);
		  }
		});
	}

	function getDetailsToUpdate(){
		var roll = $("#rollNo").val();
		var url = baseUrl+roll;
		$.ajax({
		  dataType: "json",
		  headers: {
	        'Content-Type': 'application/json'		    
	      },
		  url: url,
		  crossDomain: true,
		  type: 'GET',
		  success: function(res){
		  	addTemplateToUpdate(res);
		  }
		});
	}

	function addTemplate(res){
		this.res = res;
		if(res === undefined){
			$(".studentDetails").empty();
			$(".studentDetails").append("<div class =\"failure-data\">Sorry!! Data Not Found</div>");
			$('.studentDetails').fadeIn().delay(3000).fadeOut();
			return;
		}
		var self =this;
		$.get('http://localhost:8080/hello/client/templates/dispDetailsHB.html', function(data) {
		    var indexTemplate = data;
			var compiledTemplate = hbs.compile(indexTemplate);
			// var res = "{\"chem\": \""+self.res.chem+"\",\"maths\": \""+self.res.maths+"\",\"name\": \""+self.res.name+"\",\"phy\": \""+self.res.phy+"\",\"rollNo\": \""+self.res.rollNo+"\",\"imgUrl\":\""+self.res.imgUrl+"\"}";
			var result = compiledTemplate(self.res);
			$(".studentDetails").empty();
			$(".studentDetails").append(result);
		});
	}

	function addTemplateToUpdate(res){
		this.res = res;
		if(res === undefined){
			$(".studentDetails").empty();
			$(".studentDetails").append("<div class =\"failure-data\">Sorry!! Data Not Found</div>");
			$('.studentDetails').fadeIn().delay(3000).fadeOut();
			return;
		}
		var self =this;
		$.get('http://localhost:8080/hello/client/templates/updateDetailsHB.html', function(data) {
		    var indexTemplate = data;
			var compiledTemplate = hbs.compile(indexTemplate);
			// var res = "{\"chem\": \""+self.res.chem+"\",\"maths\": \""+self.res.maths+"\",\"name\": \""+self.res.name+"\",\"phy\": \""+self.res.phy+"\",\"rollNo\": \""+self.res.rollNo+"\",\"imgUrl\":\""+self.res.imgUrl+"\"}";
			var result = compiledTemplate(self.res);
			$(".studentDetails").empty();
			$(".studentDetails").append(result);
			$('#update').click(updateDetails);
		});
	}

	$(document).ready(init);
});

