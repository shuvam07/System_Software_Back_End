package com.restapi.hello;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

@Path("students")
public class StudentResource {
	
	@Inject
	StudentResource() throws Exception{
		
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public void here() {
		System.out.println("here....");
	}
	
//	@POST
//	@Path("student")
//	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
//	public void addStudent(@PathParam("name") String name) throws Exception {
//		JDBCConnection db = new JDBCConnection();
//		db.connect();
//		db.addDetails();
//	}
	
	@GET
	@Path("student/{roll}")
	@Produces({/*MediaType.APPLICATION_XML,*/MediaType.APPLICATION_JSON})
	public Student getStudent(@PathParam("roll") String roll) throws Exception {
		System.out.println("Roll : " +roll);
		JDBCConnection db = new JDBCConnection();
		db.connect();
		Student s = db.getDetails(roll);
		if(s==null) {
			System.out.println("Student not found");
			return null;
		}
		db.closeConnection();
		System.out.println("Total Marks = "+s.getTotalMarks());
		return s;
	}
	
	@POST
	@Path("student/addData")
	@Consumes({MediaType.APPLICATION_JSON/*,MediaType.APPLICATION_XML*/})
	@Produces({MediaType.APPLICATION_JSON})
	public Student addStudent(Student s) throws Exception {
		System.out.println(s.toString());
		JDBCConnection db = new JDBCConnection();
		db.connect();
		db.addDetails(s);
		db.closeConnection();
		return s;
		
	}
	
	
}
