/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.model;

/**
 *
 * @author Hario
 */
public class student {
	
   String name,dob,photo;
   int rollNo, mark_1, mark_2, mark_3;

   /**
    * Name
     * @return 
    */
   public String getName() {
      return name;
   }
  
   public void setName(String name) {
      this.name = name;
   }
   
   public String getPhoto() {
	      return photo;
	   }
	  
   public void setPhoto(String photo) {
	      this.photo = photo;
}   
  
   /**
    * Roll Number
     * @return 
    */
   public int getRollNo() {
      return rollNo;
   }
   
   public void setRollNo(int rollNo) {
      this.rollNo = rollNo;
   }
   
   /**
    * Date
     * @return 
    */
   public String getdob() {
      return dob;
   }
   
   public void setDob(String dob) {
      this.dob = dob;
   }
   
   /**
    *  Mark
     * @return 
    */
   public int getMarkInPhysics() {
      return mark_1;
   }
   
   public void setMarkInPhysics(int mark_1) {
      this.mark_1 = mark_1;
   }
   
   public int getMarkInChemistry() {
      return mark_2;
   }
   
   public void setMarkInCHemistry(int mark_2) {
      this.mark_2 = mark_2;
   }
   
   public int getMarkInMath() {
      return mark_3;
   }
   
   public void setMarkInMath(int mark_3) {
      this.mark_3 = mark_3;
   }
   
	@Override
	public String toString()
	{
		return "("+rollNo+") - ("+name +") - ("+dob+") - ("+ mark_1 +") - ("+mark_2 +") - (" +mark_3+ ")" ;
	}
}
