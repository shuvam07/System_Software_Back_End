package conn.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import core.control.fetchStudentData;
import core.model.student;

/**
 * JDBC Connection
 */
public class dataSource{
	 static Connection conn;
	 static ResultSet temp;
	 int condition= 0;

	 static String insertrecordSQL = "INSERT INTO student (Roll_Number,Name,Date_Of_Birth,Mark_In_Physic,Mark_In_Chemistry,Mark_In_Math,Photo)" +
             "VALUES (?,?,?,?,?,?,?)"; 
	 static String deleterecordSQL = "DELETE FROM student WHERE Roll_Number=?";
	 static String updateRecordSQL = "UPDATE student SET Name = ?, Date_Of_Birth=?, Mark_In_Physic=?, Mark_In_Chemistry=?, Mark_In_Math=? WHERE Roll_Number = ?";
	 static String updateRecordImgSQL = "UPDATE student SET Name = ?, Date_Of_Birth=?, Mark_In_Physic=?, Mark_In_Chemistry=?, Mark_In_Math=?, Photo=? WHERE Roll_Number = ?";
	 public static String selectrecordSQL = "SELECT Roll_Number,Name,DATE_FORMAT(Date_Of_Birth,'%d/%m/%Y') AS Date_Of_Birth,Mark_In_Physic,Mark_In_Chemistry,Mark_In_Math,Photo FROM student";
	 static String selectMaxIDdSQL = "SELECT MAX(Roll_Number) FROM student";
	 
	  static Connection openConnection(){
	        try {
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	            String url = "jdbc:mysql://localhost:3306/students";
	            conn = DriverManager.getConnection(url, "root", "root");
	        } catch (Exception e) {
	            e.printStackTrace();;
	        }
	        return conn;
	    }
	    
	  public void closeConnection() {
	        try {
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	  public int updateStudent(student stu ) throws SQLException, ParseException {
		  condition= 0;
		  try {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = sdf1.parse(stu.getdob());
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			
	        PreparedStatement statement = openConnection().prepareStatement(updateRecordSQL);
	        statement.setString(1, stu.getName());
	        statement.setDate(2, sqlDate);
	        statement.setInt(3, stu.getMarkInPhysics());
	        statement.setInt(4, stu.getMarkInChemistry());
	        statement.setInt(5, stu.getMarkInMath());
	        statement.setInt(6, stu.getRollNo());

	        int rowsInserted = statement.executeUpdate();
	        if (rowsInserted > 0) {
	            System.out.println("New Record Update Successfully!!!");
	            condition = 1;
	        	}
	        }  catch (SQLException ex) {
	            ex.printStackTrace();
	        }
		  closeConnection();
		  return condition;
	  }
	  
	  
	  public int updateStudentImg(student stu, InputStream fileInputStream, String filename ) throws SQLException, ParseException {
		  condition= 0;
		  String extension=""; 
		  try {
			//set name of image
			int i = filename.lastIndexOf('.');
	        	if (i > 0) {
	        		extension = filename.substring(i);
	        	}
	        filename= stu.getRollNo() + extension;

			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = sdf1.parse(stu.getdob());
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			
	        PreparedStatement statement = openConnection().prepareStatement(updateRecordImgSQL);
	    
	        statement.setString(1, stu.getName());
	        statement.setDate(2, sqlDate);
	        statement.setInt(3, stu.getMarkInPhysics());
	        statement.setInt(4, stu.getMarkInChemistry());
	        statement.setInt(5, stu.getMarkInMath());
	        statement.setString(6, filename);
	        statement.setInt(7, stu.getRollNo());

	        int rowsInserted = statement.executeUpdate();
	        
	        if (rowsInserted > 0) {
	            System.out.println("New Record Update Successfully!!!");
	            condition = 1;
	        	}
	        }  catch (SQLException ex) {
	            ex.printStackTrace();
	        }
		  
		  condition = new fetchStudentData().uploadImg(fileInputStream, filename);
		  closeConnection();
		  return condition;
	  }
	  
	  
	  public int Add(student stu ) throws SQLException, ParseException {
		  condition= 0;
		  try {
			
			//Create ID
			temp = DisplayRecords(selectMaxIDdSQL);  
			if(temp.next()==false)
				stu.setRollNo(1);
			else 
				stu.setRollNo(1+temp.getInt(1));
			closeConnection();
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = sdf1.parse(stu.getdob());
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			
	        PreparedStatement statement = openConnection().prepareStatement(insertrecordSQL);
	        statement.setInt(1, stu.getRollNo());
	        statement.setString(2, stu.getName());
	        statement.setDate(3, sqlDate);
	        statement.setInt(4, stu.getMarkInPhysics());
	        statement.setInt(5, stu.getMarkInChemistry());
	        statement.setInt(6, stu.getMarkInMath());

	        int rowsInserted = statement.executeUpdate();
	        if (rowsInserted > 0) {
	            System.out.println("New Record Inserted Successfully!!!");
	            condition = 1;
	        	}
	        }  catch (SQLException ex) {
	            ex.printStackTrace();
	        }
		  closeConnection();
		  return condition;
	  }
	  
	  
	  public int AddStudent(student stu, InputStream fileInputStream, String filename ) throws SQLException, ParseException {
		  condition= 0;
		  String extension=""; 
		  try {
			
			//Create ID
			temp = DisplayRecords(selectMaxIDdSQL);  
			if(temp.next()==false)
				stu.setRollNo(1);
			else 
				stu.setRollNo(1+temp.getInt(1));
			closeConnection();
			
			//set name of image
			int i = filename.lastIndexOf('.');
	        	if (i > 0) {
	        		extension = filename.substring(i);
	        	}
	        filename= stu.getRollNo() + extension;

			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = sdf1.parse(stu.getdob());
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			
	        PreparedStatement statement = openConnection().prepareStatement(insertrecordSQL);
	        statement.setInt(1, stu.getRollNo());
	        statement.setString(2, stu.getName());
	        statement.setDate(3, sqlDate);
	        statement.setInt(4, stu.getMarkInPhysics());
	        statement.setInt(5, stu.getMarkInChemistry());
	        statement.setInt(6, stu.getMarkInMath());
	        statement.setString(7, filename);

	        int rowsInserted = statement.executeUpdate();
	        
	        if (rowsInserted > 0) {
	            System.out.println("New Record Inserted Successfully!!!");
	            condition = 1;
	        	}
	        }  catch (SQLException ex) {
	            ex.printStackTrace();
	        }
		  
		  condition = new fetchStudentData().uploadImg(fileInputStream, filename);
		  closeConnection();
		  return condition;
	  }

	  
	  public int delete (String roll_No) throws SQLException { 
		  condition= 0;
	      try {
	    	 PreparedStatement statement = openConnection().prepareStatement(deleterecordSQL);
	    	 statement.setString(1,roll_No);
	         
	         int rowsDeleted = statement.executeUpdate();
	         if (rowsDeleted > 0) {
	        	condition=1;
	         	}
	        }  catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        closeConnection();
			return condition;
	     }
	  
	  public int edit(String name,Date date,Integer physic, Integer Chemistry, Integer Math, String roll_No) throws SQLException { 
		   condition= 0;
	       try {
	        
	        PreparedStatement statement = openConnection().prepareStatement(updateRecordSQL);
	    
	        statement.setString(1,name);    
	        statement.setDate(2, date);
	        statement.setInt(3,physic);
	        statement.setInt(4,Chemistry);
	        statement.setInt(5,Math);
	        statement.setString(6,roll_No);

	        int rowsUpdated = statement.executeUpdate();
	        	if (rowsUpdated > 0) {
	        	condition=1;
	        	}
	        }  catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	       	closeConnection();
			return condition;
	     }
	  
	  public ResultSet DisplayRecords(String query) throws SQLException {
		  	ResultSet resultSet = null;
	        try {
	             PreparedStatement statement = openConnection().prepareStatement(query);
	             resultSet = statement.executeQuery(query);
	        } catch (SQLException ex) {
	            System.out.println("The following error has occurred: " + ex.getMessage());
	        }     
			return resultSet;
	 } 
	  
	  public ResultSet SearchRecords(String roll_No) throws SQLException {
		  	ResultSet resultSet = null;
		  	String searchStudent =selectrecordSQL +" where Roll_Number=" + roll_No;
	        try {
	             PreparedStatement statement = openConnection().prepareStatement(searchStudent);
	             resultSet = statement.executeQuery(searchStudent);
	        } catch (SQLException ex) {
	            System.out.println("The following error has occurred: " + ex.getMessage());
	        }     
			return resultSet;
	 } 

}
