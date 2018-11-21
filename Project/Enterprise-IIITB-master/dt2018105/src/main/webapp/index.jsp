<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% String WsUrl = getServletContext().getInitParameter("WsUrl"); %>
<html>
    <head>
        <title>Student Information System</title>
        <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
       
        <!-- jquery -->
        <script src="/dt2018105/carouselengine/jquery.js"></script>
      	<script src="/dt2018105/jquery/jquery-3.3.1.min.js"></script>
       
       	<!-- Caraousal -->
		<script type="text/javascript" src="/dt2018105/carouselengine/amazingcarousel.js"></script>
		<script type="text/javascript" src="/dt2018105/carouselengine/initcarousel-1.js"></script>
    	<link rel="stylesheet" type="text/css" href="/dt2018105/carouselengine/initcarousel-1.css">
       
      	<!-- bootsrap -->
		<script src="/dt2018105/bootstrap/js/bootstrap.js"></script>
        <link rel="stylesheet" href="/dt2018105/bootstrap/css/bootstrap.css">
		
		<!-- Data Table -->
		<script src="/dt2018105/datatables/js/jquery.dataTables.js"></script>
		<link type="text/css" rel="stylesheet" href="/dt2018105/datatables/css/jquery.dataTables.css">
		
		<!-- Hario -->
		<script src="/dt2018105/javascript/readyHario.js"></script>
		<script src="/dt2018105/javascript/carouselHario.js"></script>
		<script src="/dt2018105/javascript/readHario.js"></script>
		<script src="/dt2018105/javascript/updateHario.js"></script>
		<script src="/dt2018105/javascript/insertHario.js"></script>
		<script src="/dt2018105/javascript/deleteHario.js"></script>
		<script src="/dt2018105/javascript/gradeHario.js"></script>
		
		<!-- Chat -->
		<script src="/dt2018105/javascript/chatroom.js"></script>
		<link rel="stylesheet" type="text/css" href="/dt2018105/css/site.css">
		
		<style>
		#slideshow {
		  padding-top: 10px;
		  position: absolute;
		  box-shadow: 0 0 20px rgba(0, 0, 0, 0.4);
		  transition-duration: 5s;
		}
		
		#slideshow > div {
		  position: absolute;
		}
		
		div.scroolbar {
    		height: 480px;
    		overflow: auto;
    		transition-duration: 5s;
		}
		</style>
		<script type="text/javascript">
		//grade function
		

		//slide show
		$("#slideshow > div:gt(0)").hide();
			setInterval(function() {
		  	$('#slideshow > div:first')
		    	.fadeOut(1000)
		    	.next()
		    	.fadeIn(1000)
		    	.end()
		    	.appendTo('#slideshow');
		}, 3000);
			
		var wsUri = '<%=WsUrl%>';
		var proxy = CreateProxy(wsUri);

		document.addEventListener("DOMContentLoaded", function(event) {
			console.log(document.getElementById('loginPanel'));
			proxy.initiate({
				loginPanel: document.getElementById('loginPanel'),
				msgPanel: document.getElementById('msgPanel'),
				txtMsg: document.getElementById('txtMsg'),
				txtLogin: document.getElementById('txtLogin'),
				msgContainer: document.getElementById('msgContainer')
				});
			});
		</script>
    </head>
    
    <body>
    
    <div class="container">
  	<h3>Student Information System</h3>
    <ul class="nav nav-tabs">
  		<li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#home">Home</a></li>
  		<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#search" onclick="information();">Student</a></li>
  		<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#insert">Insert Student</a></li>
  		<li class="nav-item"><a class="nav-link" data-toggle="tab" id="studentView" href="#view" onclick="view();">RUD Student (Read/Edit/Delete)</a></li>
  		<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#grade">Student Grade</a></li>
  		<!-- <li class="nav-item"><a class="nav-link" data-toggle="tab" href="#chat">Officer Chat Room</a></li>
  		<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#example">Example</a></li>-->
	</ul>
	
	<div class="tab-content">
    <div id="home" class="tab-pane container active">
      
	 <div id="slideshow">
	 	<div><img src="img/Enterprise Architecture.png" height="321" width="1000" title="Enterprise Architecture"></div>
	 	<div><img src="img/12.png" height="321" width="1000" title="Enterprise Architecture"></div>
	 	<div><img src="img/Enterprise Architecture.png" height="321" width="1000" title="Enterprise Architecture"></div>
   	 	<div><img src="img/chaya.png" height="321" width="1000" title="Enterprise Architecture"></div>
	</div>
	 
      
      <div style="padding-top: 360px;">
      <p>Create student information systems with 1 table consist of variables:Name, Roll No, Date of Birth, Mark Physic, Chemistry and Math
      using bootsrap, jersey, java class (MVC) with MySql Database that exposes total mark function by student id.</p>
     
     </div>
      
    </div>
    
    <div id="insert" class="tab-pane container fade">
   	  <h3>Insert</h3>
      	<div class="container">  
      	<table>
            <tbody>
                <tr>
                    <td>Name </td>
                    <td><input type="text" id="name" name="name"></td>
                </tr>
                <tr>
                    <td>Date of Birth</td>
                    <td><input type="date" id="dob" name="dob" value='1988-11-11'></td>
                </tr>
                <tr>
                    <td>Mark In Physic</td>
                    <td><input type="number" id="mark_1" name="mark_1"></td>
                </tr>
                <tr>
                    <td>Mark In Chemistry</td>
                    <td><input type="number" id="mark_2" name="mark_2"></td>
                </tr>
                <tr>
                    <td>Mark In Math</td>
                    <td><input type="number" id="mark_3" name="mark_3"></td>
                </tr>
                <tr>
                    <td>Upload Photo</td>
                    <td><input id="uplPhoto" type="file" name="file" size="20"  accept=".png, .jpg, .jpeg" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                    	<p>  	 	</p> 
                    	<button id="insertbtn" type="button" class="btn btn-default">Insert</button>
                    </td>
                </tr>
                
            </tbody>
        </table>       
		</div>  
		 <p>  	 	</p> 	 	
      	<div class="container">
		  <div class="col-sm-offset-4 col-sm-5">           
		  <table style="text-align:center" id="result"   class="table table-condensed">
		    <thead>
		      <tr>
		        <th id="resultInsert" colspan="2">Result Query :</th>
		      </tr>
		    </thead>
		  </table>
		  </div>
		</div>
    </div>
    
    <div id="search" class="tab-pane container fade">
      <h3>Student Information</h3>
      		<table><tr>
                <td>Filter By Grade </td>
                <td>
                <select id="gradeOption">
  						<option value="All">All</option>
  						<option value="A">A</option>
  						<option value="B">B</option>
  						<option value="C">C</option>
  						<option value="D">D</option>
  						<option value="E">E</option>
					</select>
				</td>
               	<td>
					<button id="filterCarousel" type="button" class="btn btn-default">Go</button>
                </td>
            </tr>
            </table>
      		<div id="resultSearch"></div>
    </div>
    
    <div id="view" class="tab-pane container fade">
      <h3>RUD Student</h3>
      	<table style="text-align:left" id="studentUpdate"   class="table table-condensed">
		</table>
		<table>
	  <tbody>
        <tr>
            <td>Roll number from 
            	<input type="number" id="Rollmin" name="min"> 
            	to 
            	<input type="number" id="Rollmax" name="max">
            	<button id="submitRange" type="button" class="btn btn-default">Submit</button>
            </td>
        </tr>
        </tbody>
       </table>
	   <table id="resultView" class="display">
	   		<thead>
	   			<tr><th id="viewNumber">Roll Number</th>
	   			<th id="viewName">Name</th>
	   			<th id="viewDate">Date of Birth</th>
	   			<th>Mark in Physic</th>
        		<th>Mark In Chemistry</th>
        		<th>Mark In Math</th>
        		<th>Student Grade</th>
        		<th>Student Age</th>
        		<th>Photo</th>
        		<th>Edit</th>
        		<th>Delete</th>
        		</tr>
        	</thead>
        	<tbody>
	   			<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
        	</tbody>
	   </table>
    </div>
    
    <div id="grade" class="tab-pane container fade">
      <h3>Calculate Student Grade By Roll Number</h3>
      	<div class="container">
      	<table>
            <tbody>
                <tr>
                    <td>Roll Number </td>
                    <td><input type="number" id="rollNoSrc" name="rollNoSrc"></td>
                    <td>
                    	<button id="gradeStudent" type="button" class="btn btn-default">Calculate Student Grade</button>
                    </td>
                </tr>
            </tbody>
        </table>    
        <table style="text-align:left" id="resultGrade"   class="table table-condensed">

        </table>  
			<button id="clearGradeStudent" type="button" class="btn btn-default">Clear</button>
		</div>  		
    </div>
    
 <!--    <div id="chat" class="tab-pane container fade">
      <h3>Chat Area</h3>
      <div  class="scroolbar">
      	<div id="resultChat">
      		<div id="#containerChat">
				<div id="loginPanel">
					<div id="infoLabel">Type a name to join the room</div>
					<div style="padding: 10px;">
						<input id="txtLogin" type="text" class="loginInput"
							onkeyup="proxy.login_keyup(event)" />
						<button type="button" class="loginInput" onclick="proxy.login()">Login</button>
					</div>
				</div>
				<div id="msgPanel" style="display: none">
					<div id="msgContainer" style="overflow: auto;"></div>
					<div id="msgController">
						<textarea id="txtMsg" 
							title="Enter to send message"
							onkeyup="proxy.sendMessage_keyup(event)"
							style="height: 30px; width: 100%"></textarea>
						<button style="height: 30px; width: 100px" type="button"
							onclick="proxy.logout()">Logout</button>
					</div>
				</div>
			</div>
    	</div>
    	</div>
    </div>
    
    <div id="example" class="tab-pane container fade">
      <h3>1. Jersey Hello World</h3>
      <p><a href="rest/hello">Jersey</a></p>
      
      <h3>2. Jersey Hello "/Change URI"</h3>
      <p><a href="rest/hello/Change Uri">Jersey Method Get</a></p>
      
      <h3>3. Json</h3>
      <p><a href="rest/json">Json</a></p>
      
      <h3>4. Ajax Json Services</h3>
      <p><a href="bootstrapExam.jsp">AJAX JSON</a></p>
      
      <h3>5. Servlet</h3>
      <form action="viewStudent"  method="POST">
        <table>
            <tbody>
            	<tr>
                    <td>Roll No</td>
                    <td><input type="number" name="rollno"></td>
                </tr>
                <tr>
                    <td>Name </td>
                    <td><input type="text" name="name"></td>
                </tr>
                <tr>
                    <td>Date of Birth</td>
                    <td><input type="date" name="dob" value='1988-11-11'></td>
                </tr>
                <tr>
                    <td>Mark In Physic</td>
                    <td><input type="number" name="mark1"></td>
                </tr>
                <tr>
                    <td>Mark In Chemistry</td>
                    <td><input type="number" name="mark2"></td>
                </tr>
                <tr>
                    <td>Mark In Math</td>
                    <td><input type="number" name="mark3"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Submit" /></td>
                </tr>
            </tbody>
        </table>       
        </form>
        
        <h3>6. Upload File</h3>
      	<p>
            Select file 1: <input id="uplImg" type="file" name="file" size="450"  accept=".png, .jpg, .jpeg" />
        </p>
        <p>
            <button id="uploadImgBtn" type="button">Upload Image (PND/JPEG/JPG)</button>
        </p>
          <ul id="messages">   </ul>
      
    </div>-->
  </div>
	
      
     
        
        
        </div>
    </body>
</html>



