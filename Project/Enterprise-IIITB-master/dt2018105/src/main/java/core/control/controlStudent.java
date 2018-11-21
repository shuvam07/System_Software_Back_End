package core.control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

import core.model.student;

/**
 *
 * @author Hario
 */
public class controlStudent{
   private student model;
   private int AverageMark;
   

   public controlStudent(student student){
      this.model = student;
   }

   public void setStudentName(String name){
      model.setName(name);		
   }

   public String getStudentName(){
      return model.getName();		
   }

    /**
    * Date
     * @return 
    */
   public String getStudentDob() {
      return model.getdob();
   }
   
   public void setStudentDob(String StudentDob) {
      model.setDob(StudentDob);
   }
   
   public int getStudentRollNo(){
	      return model.getRollNo();		
	   }
   
   public void setStudentRollNo(int rollNo){
	      model.setRollNo(rollNo);		
	   }
	   
   /**
    *  Mark
     * @return 
    */
   public int getStudentMarkInPhysics() {
      return model.getMarkInPhysics();
   }
   
   public void setStudentMarkInPhysics(int StudentMark1) {
      model.setMarkInPhysics(StudentMark1);
   }
   
   public int getStudentInChemistry() {
      return model.getMarkInChemistry();
   }
   
   public void setStudentMarkInChemistry(int StudentMark2) {
      model.setMarkInCHemistry(StudentMark2);
       
   }
   
   public int getStudentMarkInMath() {
      return model.getMarkInMath();
   }
   
   public void setStudentMarkInMath(int StudentMark3) {
      model.setMarkInMath(StudentMark3);
   }
   
   public int getStudentAverageMark(){
       AverageMark = model.getMarkInPhysics() + model.getMarkInChemistry() + model.getMarkInMath();
       AverageMark = AverageMark / 3;
       return AverageMark;
   }
   
   public int getStudentTotalMark(){
       return model.getMarkInPhysics() + model.getMarkInChemistry() + model.getMarkInMath();
   }
   
   public String getStudentGrade(){
	   String grade = "";
	   if(getStudentAverageMark() > 7)
		   grade ="A";
	   else if (getStudentAverageMark() > 6) 
		   grade ="B";
	   else if (getStudentAverageMark() > 5)
		   grade ="C";
	   else if (getStudentAverageMark() > 3)
		   grade ="D";
	   else 
		   grade ="E";
		   
       return grade;
   }
   
   
   
   public int getStudentAge() throws ParseException{
       LocalDate today = LocalDate.now();  
       Calendar cal = Calendar.getInstance();
       String parseDate = model.getdob();
      // 1988-11-11
       parseDate = model.getdob().substring(6,10) + "-" + model.getdob().substring(3, 5) + "-" + model.getdob().substring(0, 2);
       Date date = new SimpleDateFormat("yyyy-MM-dd").parse(parseDate);
       cal.setTime(date);
       int year = cal.get(Calendar.YEAR);
       int month = cal.get(Calendar.MONTH)+1;
       int day = cal.get(Calendar.DAY_OF_MONTH);
       LocalDate DoB = LocalDate.of(year, month, day);
//       System.out.println(month+" "+year+" "+day);
//       System.out.println(cal);
       Period p = Period.between(DoB, today);
       return p.getYears();
   }
   
  
   
   

}
