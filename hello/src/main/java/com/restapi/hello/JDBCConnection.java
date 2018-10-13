package com.restapi.hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCConnection {
	
	Statement st;
	Connection con;
	
	public void connect()throws Exception{
		String url = "jdbc:mysql://localhost:3306/students?autoReconnect=true&useSSL=false";
		String uname = "admin";
		String pass ="admin";
		Class.forName("com.mysql.jdbc.Driver");
		this.con = DriverManager.getConnection(url,uname,pass);
		this.st = con.createStatement();
	}
	
	public Student getDetails(String roll) throws Exception {
		String query = "select * from student_info where roll = '"+roll+"'";
		ResultSet rs = st.executeQuery(query);
		if(rs.next()) {
			
			Student s = new Student();
			s.setRollNo(rs.getString(1));
			s.setName(rs.getString(2));
			
			s.setPhy(rs.getFloat(3));
			s.setChem(rs.getFloat(4));
			s.setMaths(rs.getFloat(5));
			s.setTotalMarks(rs.getFloat(3)+rs.getFloat(4)+rs.getFloat(5));
			
			return s;
		}
		else
			return null;
	}
	
	public void closeConnection() throws Exception {
		this.st.close();
		this.con.close();
	}

}
