package com.student.login;


import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("user")
public class userResource {

	private userService userService = new userService();

// =================================================LOGIN FORM====================================================//
	@POST 
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public user userLogin(@FormParam("username") String username,
			@FormParam("password") String password) {
		return userService.userLogin(username, password);
	}
// =================================================REGISTER FORM=================================================//
	@POST 
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public user userRegister(user user) {
		return userService.userRegister(user);
	}
// ================================================UPDATE ADDRESS=================================================//
//	@POST 
//	@Path("/updateAddress/{user_email}")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	public user AddressUpdate(@PathParam("user_email") String user_email, @FormParam("country") String country,
//			@FormParam("address") String address, @FormParam("state") String state, @FormParam("pincode") int pincode,
//			@FormParam("emailid") String emailid, @FormParam("mobile") String mobile) {
//		userService addService = new userService();
//		return addService.updateAddress(user_email, country, address, state, pincode, emailid, mobile);
//	}
	
	
//	@GET
//	@Path("/getUserName/{user_id}")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String getUserName(@PathParam("user_id") int user_id) {
//		return userService.getUserName(user_id);
//	}
//// ============================================TESTING============================================================//
//	@GET
//	@Path("/getalluser")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<user> getalluser() {
//		return userService.getalluser();
//	}
}
