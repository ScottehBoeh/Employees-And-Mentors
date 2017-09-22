package com.scottehboeh.mep;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.scottehboeh.mep.entities.EntityEmployee;
import com.scottehboeh.mep.utilities.EmpHelper;

public class MEP {
	
	public static boolean exit = false;
	public static Scanner scannerInstance = new Scanner(System.in);

	/**
	 * Main Program Method
	 * @param args - Given Launch Arguments
	 */
	public static void main(String[] args){
		
		/** Run program whilst exit is false */
		while(!exit){
			
			/** Prompt the user to choose an Option from the Menu */
			System.out.println("Please choose an option:");
			
			/** Display Menu Options */
			System.out.println("1 - Create New Employee");
			System.out.println("2 - View Specific Employee");
			System.out.println("3 - Delete Specific Employee");
			System.out.println("4 - View Global Statistics");
			System.out.println("5 - Set Employee's New Mentor");
			
			System.out.println("9 - Quit");
			
			switch(scannerInstance.nextInt()){
				
				/** Create new Employee */
			case 1:
				System.out.println("You've chosen Option 1");
				initOption1();
				break;
				
				/** View Specific Employee */
			case 2:
				System.out.println("You've chosen Option 2");
				initOption2();
				break;
				
				/** View Specific Employee */
			case 3:
				System.out.println("You've chosen Option 3");
				initOption3();
				break;
				
			case 4:
				System.out.println("You've chosen Option 4");
				initOption4();
				break;
				
			case 5:
				System.out.println("You've chosen Option 5");
				initOption5();
				break;
				
				/** Exit the Program */
			case 9:
				exit = true;
				break;
			
			}
			
		}
		
		/** Inform the user that the Program is shutting down */
		System.out.println("Shutting down Program...");
		
		/** Close Scanner Instance/Stream */
		scannerInstance.close();
		
	}
	
	/**
	 * Option 1 - Create a new Employee
	 */
	public static void initOption1(){
		
		try {
			
			/** Initialize "Create New Employee" method in Employee Helper Class */
			EmpHelper.createNewEmployee(scannerInstance);
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	
	/**
	 * Option 2 - View a specific Employee
	 */
	public static void initOption2(){
		
		/** Prompt the user to input the employee's ID */
		System.out.println("Please enter the Employee UUID (Unique ID):");
		System.out.println("Example: emp-e29fd3cc-c0bd-47c2-bef0-e9f490d9a790");
		
		/** Do not proceed whilst there is no input */
		while(scannerInstance.nextLine() == null);
		
		/** Get the given input as a String */
		String givenEmployeeID = scannerInstance.nextLine();
		
		/** Fetch the employee entity from the saved employees and display to the user */
		try {
			
			if(EmpHelper.doesEmployeeExist(givenEmployeeID)){
				
				try {
					
					/** Get the Employee Entity from the given Employee ID */
					EntityEmployee theEmployee = EmpHelper.getEmployeeFromUUID(givenEmployeeID);
				
					/** Display all the employee information */
					System.out.println("=------------------------------------------------=");
					
					System.out.println("Employee Name: " + theEmployee.getFullName());
					System.out.println("Employee ID: " + theEmployee.getEmployeeID());
					System.out.println("Employee Hours Worked: " + theEmployee.getHoursWorked());
					System.out.println("Employee Hourly Rate: £" + theEmployee.getHourlyRate());
					
					/** If the employee has a mentor, display it's information too! */
					if(theEmployee.getMentorID() != null){
						EntityEmployee theMentor = EmpHelper.getEmployeeFromUUID(theEmployee.getMentorID());
						System.out.println("Employee Mentor: " + theMentor.getFullName());
						System.out.println("Employee Mentor ID: " + theMentor.getEmployeeID());
					}
					
					System.out.println("=------------------------------------------------=");
				
				} catch (IOException e) {
					
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				
				System.out.println("That employee doesn't exist!");
				
			}
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	

	/**
	 * Option 3 - Delete a specific Employee
	 */
	public static void initOption3(){
		
		/** Prompt the user to input the employee's ID */
		System.out.println("Please enter the Employee UUID (Unique ID) to Delete:");
		System.out.println("Example: emp-e29fd3cc-c0bd-47c2-bef0-e9f490d9a790");
		
		/** Do not proceed whilst there is no input */
		while(scannerInstance.nextLine() == null);
		
		/** Get the given input as a String */
		String givenEmployeeID = scannerInstance.nextLine();
		
		/** If the Employee Exists, ask the user if it should be deleted */
		try {
			
			if(EmpHelper.doesEmployeeExist(givenEmployeeID)){
				
				/** Cast the Employee to the given Employee ID */
				EntityEmployee theEmployee = EmpHelper.getEmployeeFromUUID(givenEmployeeID);
				
				/** Prompt the user to enter if they truely want to delete this record */
				System.out.println("Are you sure you wan't to delete " + theEmployee.getFullName() + " from the Records? (Y/n):");
				
				/** Get user choice as Scanner Input (String) */
				String userChoice = scannerInstance.nextLine();
				
				/** If the user input is "y" or "yes" (Yes), continue */
				if(userChoice.toLowerCase().contains("y") || userChoice.toLowerCase().contains("yes")){
					
					/** Inform the user that the Employee is being Deleted */
					System.out.println("Deleting... ");
					
					/** Delete the Employee using Employee Helper */
					EmpHelper.deleteEmployeeFromUUID(givenEmployeeID);
					
					/** Inform the user that the Employee has been deleted successfully */
					System.out.println("SUCCESS! " + theEmployee.getFullName() + " was Successfully deleted!");
					
				} else {
					
					/** Else, inform the user that the deletion process was canceled */
					System.out.println("Deletion of Employee was canceled!");
					
				}
				
			} else {
				
				/** Inform the user that the given Employee ID does not exist */
				System.out.println("That employee doesn't exist!");
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Option 4 - Get Global Statistics, such as Employee Count etc.
	 */
	public static void initOption4(){
		
		try {
			
			/** Cast List of Employee to a new Array List (Using Employee Helper) */
			ArrayList<EntityEmployee> employeeList = EmpHelper.getAllEmployeesAsList();
			
			/** Display all the global employee information */
			/** Start Formatting */
			System.out.println("=------------------------------------------------=");
			
			/** Total Employee List */
			System.out.println("Total Employee Count: " + employeeList.size());
			
			System.out.println("Person Type | Full Name | Hours Worked | Hourly Rate | Salary");
			
			/** For each Employee in List, display their Information */
			for(int i = 0; i < employeeList.size(); i++){
				
				/** Print out information from Employee data file */
				System.out.println("Employee: " + employeeList.get(i).getFullName() 
						+ " | " + employeeList.get(i).getHoursWorked()
						+ " | " + employeeList.get(i).getHourlyRate()
						+ " | £" + employeeList.get(i).getCurrentSalary());
			}

			/** End Formatting */
			System.out.println("=------------------------------------------------=");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("An error occured whilst trying to load the Employee's!");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Option 5 - Set an Employee's Mentor using a given Mentor and Employee ID
	 */
	public static void initOption5(){
		
		/** Prompt the user to input the ID of the Employee they wish to give a Mentor */
		System.out.println("Please enter the ID of the Employee you wish to add a Mentor to:");
		System.out.println("(Or type 'cancel' to cancel this Process)");
		
		/** Get the given employee ID as String from the user input */
		String givenEmployeeID = scannerInstance.next();
		
		/** If the User did not Cancel input */
		if(!givenEmployeeID.toLowerCase().equals("cancel")){
		
			/** Prompt the user to input the ID of the Mentor */
			System.out.println("Please enter the ID of the Mentor:");
			System.out.println("(Or type 'cancel' to cancel this Process)");
		
			/** Get given mentor ID as String from user input */
			String givenMentorID = scannerInstance.next();
		
			/** If the User did not Cancel input */
			if(!givenMentorID.toLowerCase().equals("cancel")){
				
				try {
					
					/** If the given Employee and Mentor IDs are Valid */
					if(EmpHelper.doesEmployeeExist(givenEmployeeID)
							&& EmpHelper.doesEmployeeExist(givenMentorID)){
						
						/** Get new Employee Entity from the given Employee ID */
						EntityEmployee theEmployee = EmpHelper.getEmployeeFromUUID(givenEmployeeID);
						
						/** Set the Employee Mentor ID using the given Mentor ID */
						theEmployee.setMentorID(givenMentorID);
						
						/** Save the Employee's Information */
						EmpHelper.saveEmployee(theEmployee);
						
					} else {
						
						/** Inform the user that the given Employee ID or Mentor ID was Invalid */
						System.out.println("The given Employee ID or Mentor ID was Invlalid!");
						
					}
					
				} catch (IOException e) {
					
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
				
			}
			
		}
		
	}
	
}
