/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.view;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.control.controlStudent;
import core.model.student;


/**
 *
 * @author Hario
 */

public class viewStudent extends HttpServlet{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

   public viewStudent(){
       super();
   }
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String name=request.getParameter("name");  
        int rollno =Integer.parseInt(request.getParameter("rollno"));  
        int mark1= Integer.parseInt(request.getParameter("mark1"));  
        int mark2= Integer.parseInt(request.getParameter("mark2"));  
        int mark3= Integer.parseInt(request.getParameter("mark3"));  
        String dob = request.getParameter("dob");
        
        student model = new student();
        controlStudent cStudent = new controlStudent(model);
        cStudent.setStudentName(name);
        cStudent.setStudentRollNo(rollno);
        cStudent.setStudentDob(dob);
        cStudent.setStudentMarkInPhysics(mark1);
        cStudent.setStudentMarkInChemistry(mark2);
        cStudent.setStudentMarkInMath(mark3);
        request.setAttribute("student", cStudent);
        RequestDispatcher rd=request.getRequestDispatcher("/student/confirmation.jsp");  
        rd.forward(request, response); 
	}   
    
}

