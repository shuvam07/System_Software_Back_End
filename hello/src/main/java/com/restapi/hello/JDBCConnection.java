package com.restapi.hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		String query = "select * from student_info where roll = "+roll;
		ResultSet rs = st.executeQuery(query);
		if(rs.next()) {
			
			Student s = new Student();
			s.setRollNo(rs.getInt(1));
			s.setName(rs.getString(2));
			
			s.setPhy(rs.getFloat(3));
			s.setChem(rs.getFloat(4));
			s.setMaths(rs.getFloat(5));
			s.setImgUrl(rs.getString(6));
			s.setTotalMarks(rs.getFloat(3)+rs.getFloat(4)+rs.getFloat(5));
			s.setGrade(rs.getString(7));
			return s;
		}
		else
			return null;
	}
	
	public String addDetails(Student s) throws SQLException {
		try {
		String query = "Insert into student_info VALUES("+s.getRollNo()+",\""+s.getName()+"\","+s.getPhy()+","+s.getChem()+","+s.getMaths()+",\""+s.getImgUrl()+"\""+",\""+s.getGrade()+"\")";
		System.out.println(query);
		st.executeUpdate(query);
		return "success";
		}
		catch(SQLException se){
//		      return se.getMessage();
			return "failure";
		}
	}
	
	public String updateDetails(Student s) throws SQLException {
		try { 
		String query = "UPDATE student_info SET name = \""+s.getName()+"\","+" phy="+s.getPhy()+","+" chem="+s.getChem()+", maths="+s.getMaths()+", grade="+s.getGrade()+", imgUrl=\""+s.getImgUrl()+"\" where roll="+s.getRollNo();
		System.out.println(query);
		st.executeUpdate(query);
		return "success";
		}
		catch(SQLException se){
//		      return se.getMessage();
			return "failure";
		}
	}
	
	public void closeConnection() throws Exception {
		this.st.close();
		this.con.close();
	}

	public List<Student> getRecords(String fromRoll, String toRoll)throws SQLException {
		try { 
			String query = "select * from student_info where roll >= "+fromRoll+" and roll <= "+toRoll+" ORDER BY roll";
			System.out.println(query);
			ResultSet rs = st.executeQuery(query);
			List <Student> sl = new ArrayList<Student>();
			while(rs.next()) {
				Student s = new Student();
				s.setRollNo(rs.getInt(1));
				s.setName(rs.getString(2));
				
				s.setPhy(rs.getFloat(3));
				s.setChem(rs.getFloat(4));
				s.setMaths(rs.getFloat(5));
				s.setTotalMarks(rs.getFloat(3)+rs.getFloat(4)+rs.getFloat(5));
				s.setImgUrl(rs.getString(6));
				s.setGrade(rs.getString(7));
				sl.add(s);
			}
			return sl;
			}
			catch(SQLException se){
//			      return se.getMessage();
				return null;
			}
		
	}

}
