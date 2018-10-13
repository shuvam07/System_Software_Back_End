package com.restapi.hello;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student {
	
	private String RollNo;
	private String name;
	private float phy;
	private float chem;
	private float maths;
	private float totalMarks;
	/**
	 * @return the rollNo
	 */
	public String getRollNo() {
		return RollNo;
	}
	/**
	 * @param rollNo the rollNo to set
	 */
	public void setRollNo(String rollNo) {
		RollNo = rollNo;
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
	/**
	 * @param totalMarks the totalMarks to set
	 */
	public void setTotalMarks(float totalMarks) {
		this.totalMarks = totalMarks;
	}
	
	
	
}