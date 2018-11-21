package com.restapi.hello;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student {
	
	private int rollNo;
	private String name;
	private String dob;
	private float phy;
	private float chem;
	private float maths;
	private float totalMarks;
	private String imgUrl;
	private String grade;
	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * @return the rollNo
	 */
	public int getRollNo() {
		return rollNo;
	}
	/**
	 * @param rollNo the rollNo to set
	 */
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the phy
	 */
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public float getPhy() {
		return phy;
	}
	/**
	 * @param phy the phy to set
	 */
	public void setPhy(float phy) {
		this.phy = phy;
	}
	/**
	 * @return the chem
	 */
	public float getChem() {
		return chem;
	}
	/**
	 * @param chem the chem to set
	 */
	public void setChem(float chem) {
		this.chem = chem;
	}
	/**
	 * @return the maths
	 */
	public float getMaths() {
		return maths;
	}
	/**
	 * @param maths the maths to set
	 */
	public void setMaths(float maths) {
		this.maths = maths;
	}
	/**
	 * @return the totalMarks
	 */
	public float getTotalMarks() {
		return totalMarks;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	
	/**
	 * @param totalMarks the totalMarks to set
	 */
	public void setTotalMarks(float totalMarks) {
		this.totalMarks = totalMarks;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", name=" + name + ", dob=" + dob + ", phy=" + phy + ", chem=" + chem
				+ ", maths=" + maths + ", totalMarks=" + totalMarks + ", imgUrl=" + imgUrl + ", grade=" + grade + "]";
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
	
}
