define(['jquery','handlebars'], function($, hbs ){

	function init(){
		$('#contact-submit').click(addDetails);
		$('#roll-submit').click(getDetails);
		
	}

	var baseUrl = "http://localhost/hello/webapi/students/student/"


	function addDetails(){
		var name = $("#name").val();
		var roll = $("#roll").val();
		var phy = $("#phy").val();
		var chem = $("#chem").val();
		var maths = $("#maths").val();
		$.ajax({
		  type: "POST",
		  url: baseUrl+addData,
		  headers: {
		  	'Content-Type': 'application/json'
		  },
		  data: {
		  	"name" : name,
		  	"RollNo" : roll,
		  	"phy" : phy,
		  	"chem" : chem,
		  	"maths" : maths
		  },
		  success: function(res){
		  	console.log(res);
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
	        'Content-Type': 'application/x-www-form-urlencoded'
	      },
		  url: url,
		  crossDomain: true,
		  type: 'GET',
		  success: function(res){
		  	addTemplate(res);
		  }
		});
	}

	function addTemplate(res){
		this.res = res;
		var self =this;
		$.get('http://localhost/client/templates/detailsHB.html', function(data) {
		    var indexTemplate = data;
			var compiledTemplate = hbs.compile(indexTemplate);
			var res = "{\"chem\": \""+self.res.chem+"\",\"maths\": \""+self.res.maths+"\",\"name\": \""+self.res.name+"\",\"phy\": \""+self.res.phy+"\",\"rollNo\": \""+self.res.rollNo+"\"}";
			var result = compiledTemplate(JSON.parse(res));
			$(".studentDetails").empty();
			$(".studentDetails").append(result);
		});
		console.log(toString(res));
	}

	$(document).ready(init);

});
