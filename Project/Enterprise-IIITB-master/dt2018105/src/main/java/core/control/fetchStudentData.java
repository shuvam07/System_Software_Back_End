package core.control;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.WebApplicationException;

import core.model.student;

public class fetchStudentData{
	public static ArrayList<student> getAllStudent(ResultSet rs) throws SQLException{
		ArrayList<student> stu = new ArrayList<student>();
		    while(rs.next()) {  
		        student Student=new student();
		        Student.setRollNo(rs.getInt(1));
		        Student.setName(rs.getString(2));
		        Student.setDob(rs.getString(3));
		        Student.setMarkInPhysics(rs.getInt(4));
		        Student.setMarkInCHemistry(rs.getInt(5));
		        Student.setMarkInMath(rs.getInt(6));
		        Student.setPhoto(rs.getString(7));
		        stu.add(Student);
		    }
		return stu;
	}
	
	
	
	public int uploadImg(InputStream fileInputStream, String fileName) {
		int status =0;
		//String UPLOAD_PATH = "C:/xampp2/htdocs/img/";
		String UPLOAD_PATH = "/home/pulkit/Documents/SE_LAB/Enterprise-IIITB-master/dt2018105/src/main/webapp/picture/";
		
		try
        {
            int read = 0;
            byte[] bytes = new byte[1024];
            OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + fileName));
            while ((read = fileInputStream.read(bytes)) != -1)
            {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
            status=1;
        } catch (IOException e)
        {
        	System.out.println(e.getMessage());
            throw new WebApplicationException("Error while uploading file. Please try again !!");
        }
		return status;
	}
}
