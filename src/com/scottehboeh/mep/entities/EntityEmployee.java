package com.scottehboeh.mep.entities;

public class EntityEmployee {
	
	private String employeeID;
	private String firstName;
	private String secondName;
	private int hoursWorked;
	private float hourlyRate;
	private String employeeMentorID;
	
	public EntityEmployee(){
		
		/** Put extra stuff here */
		
	}
	
	/**
	 * Set Values Methods
	 */
	
	/**
	 * Set the ID of the Employee
	 * @param givenID - Given Employee ID
	 */
	public void setEmployeeID(String givenID){
		this.employeeID = givenID;
	}
	
	/**
	 * Set the First Name of the Employee
	 * @param givenFirstName - Given First Name
	 */
	public void setFirstName(String givenFirstName){
		this.firstName = givenFirstName;
	}
	
	/**
	 * Set the Second Name of the Employee
	 * @param givenSecondName - Given Second Name
	 */
	public void setSecondName(String givenSecondName){
		this.secondName = givenSecondName;
	}
	
	/**
	 * Set the Hours Worked of the Employee
	 * @param givenHours - Given Hours Worked
	 */
	public void setHoursWorked(int givenHours){
		this.hoursWorked = givenHours;
	}
	
	/**
	 * Add hours to Hours Worked
	 * @param givenHours - Given Hours Added
	 */
	public void addHoursWorked(int givenHours){
		this.hoursWorked += givenHours;
	}
	
	/**
	 * Remove hours from Hours Worked
	 * @param givenHours - Given Hours Removed
	 */
	public void removeHoursWorked(int givenHours){
		this.hoursWorked -= givenHours;
	}
	
	/**
	 * Set the Hourly Rate
	 * @param givenRate - Given Hourly Rate
	 */
	public void setHourlyRate(float givenRate){
		this.hourlyRate = givenRate;
	}
	
	/**
	 * Set the Mentor of the Employee
	 * @param givenMentor - Given Mentor
	 */
	public void setMentorID(String givenMentorID){
		this.employeeMentorID = givenMentorID;
	}
	
	/**
	 * Fetched Values Methods
	 */

	/**
	 * Returns the First Name of the Employee
	 * @return - Employee's First Name (String)
	 */
	public String getFirstName(){
		return this.firstName;
	}
	
	/**
	 * Returns the Second Name of the Employee
	 * @return - Employee's Second Name (String)
	 */
	public String getSecondName(){
		return this.secondName;
	}
	
	/**
	 * Returns the Full Name of the Employee
	 * @return - First and Second Name (String)
	 */
	public String getFullName(){
		return this.getFirstName() + " " + this.getSecondName();
	}
	
	/**
	 * Get the Unique ID of the Employee
	 * @return - Employee's UUID (String)
	 */
	public String getEmployeeID(){
		return this.employeeID;
	}
	
	/**
	 * Get the Hours Worked by the Employee
	 * @return - Hours Worked (int)
	 */
	public int getHoursWorked(){
		return this.hoursWorked;
	}
	
	/**
	 * Get the Hourly Rate of the Employee
	 * @return - Employee Hourly Rate (float)
	 */
	public float getHourlyRate(){
		return this.hourlyRate;
	}
	
	/**
	 * Get the Salary of the Employee
	 * @return - Returns Hourly Rate * the Hours Worked (float)
	 */
	public float getCurrentSalary(){
		return this.hourlyRate * this.hoursWorked;
	}
	
	/**
	 * Get the Employee's Mentor
	 * @return - Mentor Entity
	 */
	public String getMentorID(){
		return this.employeeMentorID;
	}
	
}
