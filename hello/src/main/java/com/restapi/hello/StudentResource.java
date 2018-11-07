package com.restapi.hello;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

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
		System.out.println(s.toString());
		db.closeConnection();
		System.out.println("Total Marks = "+s.getTotalMarks());
		return s;
	}
	
	@GET
	@Path("student/getRecords")
	@Produces({/*MediaType.APPLICATION_XML,*/MediaType.APPLICATION_JSON})
	public List<Student> getRecords(
			@QueryParam("fromRoll") String fromRoll,
			@QueryParam("toRoll") String toRoll
			) throws ServletException , Exception {
		System.out.println("here");
		JDBCConnection db = new JDBCConnection();
		db.connect();
//		String fromRoll = request.getParameter("fromRoll");
//		String toRoll = request.getParameter("toRoll");
		List<Student> sl = db.getRecords(fromRoll,toRoll);
		if(sl==null) {
			System.out.println("No Students found");
			return null;
		}
		db.closeConnection();
		return sl;
	}
	
	@POST
	@Path("student/addData")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@Produces({"application/json", "application/xml"})
	public Student addStudent(
			@FormDataParam("imageUrl") InputStream uploadedInputStream,
			@FormDataParam("imageUrl") FormDataContentDisposition fileDetail,
			@FormDataParam("name") String name,
			@FormDataParam("roll") int roll,
			@FormDataParam("phy") float phy,
			@FormDataParam("chem") float chem,
			@FormDataParam("maths") float maths
			
			) throws Exception {
		Student s = new Student();
		s.setName(name);
		s.setRollNo(roll);
		s.setPhy(phy);
		s.setChem(chem);
		s.setMaths(maths);
		s.setGrade(calcGrade(phy,chem,maths));
		String relPath = "/client/images/"+fileDetail.getFileName();
		String uploadedFileLocation="/home/shuvambosana/Desktop/System_Software/client/images/"+fileDetail.getFileName();
		s.setImgUrl(relPath);
		writeToFile(uploadedInputStream, uploadedFileLocation);
		JDBCConnection db = new JDBCConnection();
		db.connect();
		String res = db.addDetails(s);
		db.closeConnection();
		if(res.equals("success")) {
			System.out.println("success");
			return s;
		}
		else {
			System.out.println("failure");
			return null;
		}
//		return res;
	}
	
	private String calcGrade(float phy, float chem, float maths) {
		float avg = (phy+chem+maths)/3;
		if(avg>=90)
			return "A";
		if(avg>=80)
			return "B";
		if(avg>=70)
			return "C";
		else
			return "D";
	}

	@POST
	@Path("student/updateData")
	@Consumes({MediaType.APPLICATION_JSON/*,MediaType.APPLICATION_XML*/})
	@Produces({MediaType.APPLICATION_JSON})
	public Student updateStudent(Student s) throws Exception {
		System.out.println(s.toString());
		JDBCConnection db = new JDBCConnection();
		db.connect();
		s.setGrade(calcGrade(s.getChem(),s.getMaths(),s.getPhy()));
		String res = db.updateDetails(s);
		db.closeConnection();
		if(res.equals("success")) {
			System.out.println("success");
			return s;
		}
		else {
			System.out.println("failure");
			return null;
		}
//		return res;
	}
	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream,
		String uploadedFileLocation) {
		
		try {
			if(uploadedInputStream == null) {
				System.out.println("null hai");
				System.out.println(uploadedInputStream);
			}
			
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println("Exception "+e);
			//e.printStackTrace();
		}

	}
}
