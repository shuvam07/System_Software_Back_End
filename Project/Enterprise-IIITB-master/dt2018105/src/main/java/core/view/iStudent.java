package core.view;

import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import conn.jdbc.dataSource;
import core.control.controlStudent;
import core.control.fetchStudentData;
import core.model.student;
//import example.model.QuadraticEquation;

@Path("/iStudent")
public class iStudent extends dataSource{
	String result;
	private ArrayList<student> stu;
	
//	@GET
//	@Produces(MediaType.TEXT_HTML)
//	public String getSampleQEInJSON()
//	{
//
//		QuadraticEquation qe = new QuadraticEquation();
//		qe.setA(6);
//		qe.setB(4);
//		qe.setC(2);
//
//		return qe.toString() + "<a href='http://localhost:8080/dt2018105/'> return</a>";
//	}
	
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	public String Student(student stu) throws SQLException, ParseException
	{
		new dataSource().Add(stu);
		return result;
	}
	
	@POST
	@Path("/grade")
	@Consumes(MediaType.APPLICATION_JSON)
	public String Student(String rool_No) throws SQLException, ParseException
	{
		stu = new ArrayList<student>();
		stu=fetchStudentData.getAllStudent(new dataSource().SearchRecords(rool_No));
		String resultStudent="";
		new dataSource().closeConnection();
		resultStudent = "<table>";
		controlStudent cStudent;
		if (stu.size() > 0)
		{
			cStudent = new controlStudent(stu.get(0));
			resultStudent= resultStudent+"<tr><td>Roll Number </td><td> : " + stu.get(0).getRollNo() + 
					"</td><td rowspan='6' align='center'><img src='picture/"+ stu.get(0).getPhoto() +"' height=200' width='150' /></td></tr><tr>" +   
        			"<td>Name </td><td> : " +stu.get(0).getName() + "</td></tr><tr>" +
        			"<td>Date of Birth </td><td> : " +stu.get(0).getdob() + "</td></tr><tr>" +
        			"<td>Marks in Physics </td><td> : " +stu.get(0).getMarkInPhysics() + "</td></tr><tr>" +
        			"<td>Marks in Chemistry </td><td> : " +stu.get(0).getMarkInChemistry() + "</td></tr><tr>" +
        			"<td>Marks in Maths </td><td> : " +stu.get(0).getMarkInMath() + "</td></tr><tr>" +
        			"<td bgcolor='yellow'><b>Grade</b></td><td  colspan='2' bgcolor='yellow'> <b>: " +cStudent.getStudentGrade() + "</b></td></tr><tr>" +
        			"<td bgcolor='yellow'><b>Average Marks</b></td><td colspan='2' > <b>: " +cStudent.getStudentAverageMark() + "</b></td></tr><tr>" +
        			"<td bgcolor='yellow'><b>Student Age</b></td><td colspan='2'> : <b>" +cStudent.getStudentAge() + "</b></td></tr><tr>" +
        			"<td bgcolor='yellow'><b>Total Marks</b></td><td colspan='2'> : <b>" +cStudent.getStudentTotalMark() + "</b></td></tr>" ;
		}
		
        resultStudent=resultStudent+"</table>";
		return resultStudent;
	}
	
	@GET
	@Path("/view")
	@Consumes(MediaType.APPLICATION_JSON)
	public String getStudent() throws SQLException, ParseException
	{
		ArrayList<student> stud=new ArrayList<student>();
		stud=fetchStudentData.getAllStudent(new dataSource().DisplayRecords(selectrecordSQL));
		new dataSource().closeConnection();
		String table = "";
		controlStudent stu ;
        table = "<table id='resultView' class='display' style='width:100%'><thead><tr><th id='viewNumber'>Roll Number</th><th id='viewName'>Name</th><th id='viewDate'>Date of Birth</th><th>Mark in Physic</th>"+
        		"<th>Mark In Chemistry</th><th>Mark In Math</th><th>Student Grade</th><th>Student Age</th><th>Photo</th><th>Edit</th><th>Delete</th></tr></thead><tbody>";
        for (int i = 0; i < stud.size(); i++) {
        	stu = new controlStudent(stud.get(i));   
        	table= table+"<tr id='update"+stud.get(i).getRollNo()+"'><td>" + stud.get(i).getRollNo() + "</td>" + 
        			"<td>" +stud.get(i).getName() + "</td>" +
        			"<td>" +stud.get(i).getdob() + "</td>" +
        			"<td>" +stud.get(i).getMarkInPhysics() + "</td>" +
        			"<td>" +stud.get(i).getMarkInChemistry() + "</td>" +
        			"<td>" +stud.get(i).getMarkInMath() + "</td>" +        			
        			"<td>" +stu.getStudentGrade()+ "</td>" +
        			"<td>" +stu.getStudentAge()+ "</td>" +
        			"<td><img id='lastImg' src='picture/" +stud.get(i).getPhoto() + "' height='42' width='42' /></td>" +
        			"<td><button type='button' class='btn btn-default' id='editStudent' onclick='editStudent("+ stud.get(i).getRollNo()+ ")' >Edit</button></td>" +
        			"<td><button type='button' class='btn btn-default' id='delStudent' onclick='delStudent(&#39;"+ stud.get(i).getPhoto()+ "&#39;)' >Delete</button></td></tr>" ;
        }
        table=table+"</table>";
        
		return table;
	}
	
	@POST
	@Path("/information")
	@Consumes(MediaType.APPLICATION_JSON)
	public String getStudentInformation(String gradeOption) throws SQLException, ParseException
	{
		ArrayList<student> stud=new ArrayList<student>();
		stud=fetchStudentData.getAllStudent(new dataSource().DisplayRecords(selectrecordSQL));
		new dataSource().closeConnection();
		String Head = "";
		String Body = "";
		String Foot = "";
		controlStudent stu ;
		
				Head = "<div id='amazingcarousel-container-1'>" +
						"<div id='amazingcarousel-1' style='display:none;position:relative;width:100%;max-width:720px;margin:0px auto 0px;'>"+
						"<div class='amazingcarousel-list-container'><ul class='amazingcarousel-list'>";
		        for (int i = 0; i < stud.size(); i++) {
		        stu = new controlStudent(stud.get(i));
		        	if(gradeOption.equals("All") || stu.getStudentGrade().equals(gradeOption) ) {
			        Body=Body+"<li class='amazingcarousel-item'>" +
			                        "<div class='amazingcarousel-item-container'>" +
			                        	"<div class='amazingcarousel-image'>"+
			                        	"<img id='studentImg' src='picture/" +stud.get(i).getPhoto() + "' ' height=300' width='220' alt='"+stud.get(i).getName()+"' /></div>" +
			                        		"<div class='amazingcarousel-title'><b>"+stud.get(i).getName()+"</b></div>" +
			                        		"<table><tr>"+
			                        			"<td>Roll No : </td><td> "+stud.get(i).getRollNo() +"</td></tr>" +
			                        			"<td>Grade : </td><td>"+stu.getStudentGrade() +"</td></tr></table>" +
			                        			
			                        	"</div>" + 
			                    "</li>";
		        	}
               	}
		        Foot=Foot+"</ul></div><div class='amazingcarousel-prev'></div>" + 
		        		"<div class='amazingcarousel-next'></div><div class='amazingcarousel-nav'></div>" +
		            "</div></div>" ;
		
		return Head+Body+Foot;
	}
	
	
	@POST
	@Path("/updateStudent")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public void updateStudent(@FormDataParam("name") String name,
			@FormDataParam("dob") String dob,
			@FormDataParam("rollNo") int rollNo,
			@FormDataParam("mark_1") int mark_1,
			@FormDataParam("mark_2") int mark_2,
			@FormDataParam("mark_3") int mark_3,
			@FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition fileMetaData) throws SQLException, ParseException, Exception
	{
		student stu = new student();
		stu.setName(name);
		stu.setDob(dob);
		stu.setRollNo(rollNo);
		stu.setMarkInCHemistry(mark_2);
		stu.setMarkInMath(mark_3);
		stu.setMarkInPhysics(mark_1);
		stu.setPhoto(fileMetaData.getFileName());
		
		if(fileMetaData.getFileName()==null) 
			new dataSource().updateStudent(stu);
		else
			new dataSource().updateStudentImg(stu, fileInputStream, fileMetaData.getFileName());
		
		//new dataSource().AddStudent(stu, fileInputStream, fileMetaData.getFileName());
	}
	
	@POST
	@Path("/insertImage")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public String StudentImage(@FormDataParam("name") String name,
			@FormDataParam("dob") String dob,
			@FormDataParam("rollNo") int rollNo,
			@FormDataParam("mark_1") int mark_1,
			@FormDataParam("mark_2") int mark_2,
			@FormDataParam("mark_3") int mark_3,
			@FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition fileMetaData) throws SQLException, ParseException, Exception
	{
		
		
		student stu = new student();
		stu.setName(name);
		stu.setDob(dob);
		stu.setRollNo(rollNo);
		stu.setMarkInCHemistry(mark_2);
		stu.setMarkInMath(mark_3);
		stu.setMarkInPhysics(mark_1);
		stu.setPhoto(fileMetaData.getFileName());
		
		new dataSource().AddStudent(stu, fileInputStream, fileMetaData.getFileName());
		return result;
	}
	
	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public void DeleteStudent(String fileName) throws SQLException, ParseException
	{
	    File file = new File("/home/pulkit/Documents/SE_LAB/Enterprise-IIITB-master/dt2018105/src/main/webapp/picture/"+fileName.substring(1,fileName.length()-1));
		file.delete();
	    new dataSource().delete(fileName.substring(1,fileName.lastIndexOf(".")));
	}

}
