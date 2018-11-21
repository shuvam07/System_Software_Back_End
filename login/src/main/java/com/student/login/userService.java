package com.student.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userService {
	
	Connection conn;

	public userService() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userinfo?autoReconnect=true&useSSL=false","admin", "admin");
			System.out.println("connection !");
		} catch (Exception e) {
			System.out.println("Exception found" + e);
			try {
				conn.close();
			} catch (Exception ee) {
				System.out.println("Connection close error");
			}
		}
	}

	public user userLogin(String username, String password) {
		user user = null;
		PreparedStatement preparedstmnt;
		try {
			preparedstmnt = conn
					.prepareStatement("select name from user where username = ? and password = ?");
			preparedstmnt.setString(1, username);
			preparedstmnt.setString(2, password);
			ResultSet rs = preparedstmnt.executeQuery();
			if (rs.next())
				user = getUserDetail(rs.getString("username"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public user userRegister(user user) {
		PreparedStatement preparedstmnt;
			try {
				preparedstmnt = conn.prepareStatement(
						"INSERT INTO `user`(`name`,`username`,`password`) VALUES (?,?,?)");
				preparedstmnt.setString(1, user.getName());
				preparedstmnt.setString(2, user.getUsername());
				preparedstmnt.setString(3, user.getPassword());
				int success = preparedstmnt.executeUpdate();
				if (success == 1) {
					user = getUserDetail(user.getUsername());
					return user;
				}
			} catch (Exception e) {
				System.out.println("Exception raised!!" + e);
			}
		return null;
	}
	
	public user getUserDetail(String username) {

		user user = null;
		String sql = "select * from user where username = ?";
		PreparedStatement preparedstatement;
		try {
			preparedstatement = conn.prepareStatement(sql);
			preparedstatement.setString(1, username);
			ResultSet rs = preparedstatement.executeQuery();

			if (rs.next()) {
				user = new user();
				user.setName(rs.getString(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println("GetUserProfile !");
		return user;
	}

//	public user updateAddress(String user_email, String country, String address, String state, int pincode,
//			String emailid, String mobile) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public String getUserName(int user_id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public java.util.List<user> getalluser() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
}
