package com.scottehboeh.mep.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scottehboeh.mep.entities.EntityEmployee;

public class EmpHelper {
	
	public static final String empDir = "employees/";
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	/**
	 * Generate a new Employee UUID (Unique Identifier)
	 * @return - The Generated UUID (Unique Identifier)
	 */
	public static String empIDGen(){
		
		/** Returns random UUID with 'EMP' */
		return "emp-" + UUID.randomUUID().toString();
		
	}

	/**
	 * Create a new Employee Entity and store its data
	 * @param givenFirstName - Given First Name of Employee
	 * @param givenSecondName - Given Second Name of Employee
	 * @param givenHours - Given Employee Hours
	 * @param givenHourlyRate - Given Hourly Rate
	 * @throws IOException 
	 */
	public static void createNewEmployee(Scanner givenScanner) throws IOException{
		
		/** Create a new Employee Entity and give it the required attributes */
		EntityEmployee newEmployee = new EntityEmployee();
		
		System.out.println("Please enter the Employee's First Name");
		while(givenScanner.nextLine() == null);
		newEmployee.setFirstName(givenScanner.nextLine()); /** First Name */
		
		System.out.println("Please enter the Employee's Second Name");
		newEmployee.setSecondName(givenScanner.nextLine()); /** Second Name */
		
		System.out.println("Please enter the Employee's Starting Hours (0 by Default)");
		newEmployee.setHoursWorked(givenScanner.nextInt()); /** Given Hours */
		
		System.out.println("Please enter the Employee's Hourly Rate");
		newEmployee.setHourlyRate(givenScanner.nextFloat()); /** Given Hourly Rate */
		
		System.out.println("Does the Employee have a Mentor? Enter their Employee ID:");
		System.out.println("(Or type 'cancel' to Skip)");
		
		/** Prompt for an input whilst the Scanner has no next line */
		while(!givenScanner.hasNext());
		
		/** Get given employee ID as String from Scanner Instance */
		String givenEmployeeID = givenScanner.next();

		//System.out.println("You wrote " + givenEmployeeID + "!");
		
			/** If the Mentor Section wasn't canceled, set the new Mentor */
			if(!givenEmployeeID.toLowerCase().equals("cancel")){
				
				/** Set the Employee's Mentor using the given Employee ID */
				newEmployee.setMentorID(givenEmployeeID);
				
				/** Inform the user that the Mentor was set */
				System.out.println("Set " + getEmployeeFromUUID(givenEmployeeID).getFullName() + " as Mentor"); 
				
			}
		
		newEmployee.setEmployeeID(empIDGen()); /** Employee ID (Generated) */
		
		/** Inform the user that the new Employee was created successfully */
		System.out.println("SUCCESS! Created a new Employee with the id " + newEmployee.getEmployeeID());
		
		/** Save the new Employee */
		saveEmployee(newEmployee);
		
	}
	
	/**
	 * Save the Employee to a File
	 * @param givenEmployee - Given Employee to Save
	 * @throws IOException 
	 */
	public static void saveEmployee(EntityEmployee givenEmployee) throws IOException{
		
		/** Get the Employee File Instance */
		File employeeFile = new File(empDir + givenEmployee.getEmployeeID() + ".json");
		
		/** If the File does not exist, create it */
		if(!employeeFile.exists()){
			employeeFile.createNewFile();
		}
		
		/** Write the employee to the File */
		Writer writer = new FileWriter(employeeFile);
        writer.write(gson.toJson(givenEmployee));
        writer.close();
		
	}
	
	/**
	 * Delete an Employee from the Saved Employees directory
	 * @param givenEmployeeID - The Given Employee ID
	 * @throws IOException
	 */
	public static void deleteEmployeeFromUUID(String givenEmployeeID) throws IOException {

        /** Get Employee File */
        File clanFile = new File(empDir + givenEmployeeID + ".json");

        /** If clan file doesn't exist, create one */
        if (clanFile.exists()) {
            PrintWriter writer = new PrintWriter(clanFile);
            writer.print("");
            writer.close();
            
            clanFile.delete();
        }

    }
	
	/**
	 * Get an Employee Entity using the given UUID
	 * @param givenUUID - Given Employee ID (UUID String)
	 * @return - Employee Entity (EntityEmployee)
	 * @throws IOException 
	 */
	public static EntityEmployee getEmployeeFromUUID(String givenUUID) throws IOException{
		
		//System.out.println("Getting " + givenUUID + "!");
		
		/** Get the Employee File Instance */
		File employeeFile = new File(empDir + givenUUID + ".json");
		
		/** If the Employee File Exists, load it! */
		if(doesEmployeeExist(givenUUID)){
			
			/** Read from Employee File */
			BufferedReader br = new BufferedReader(new FileReader(employeeFile));
			
			/** Cast Employee Entity to the given file */
			EntityEmployee theEmployee = new Gson().fromJson(br, EntityEmployee.class);

			/** Close Reader stream */
            br.close();
            
            /** Return the Employee */
            return theEmployee;
            
        /** Else, return null */
        } else {
            return null;
        }
		
	}
	
	/**
	 * Get all Employees as an Array List
	 * @return - The List of Employees (ArrayList)
	 * @throws IOException 
	 */
	public static ArrayList<EntityEmployee> getAllEmployeesAsList() throws IOException{
		
		ArrayList<EntityEmployee> employeeList = new ArrayList<EntityEmployee>();
		
		File employeesDirectory = new File(empDir);
		File[] listOfEmpFiles = employeesDirectory.listFiles();

		/** For each File in Employee File List */
		for (File file : listOfEmpFiles) {
		    if (file.isFile()) {
		    	
		        System.out.println(file.getName());
		        
		        /** Get the Employee ID from File Name without .json extention */
		        String nakedName = file.getName().replace(".json", "");
		        
		        /** If the Employee from the given Employee ID exists, add it */
		        if(doesEmployeeExist(nakedName)){
		        	
		        	/** Cast new Employee Entity to given Employee ID */
		        	EntityEmployee newEmployee = getEmployeeFromUUID(nakedName);
		        	
		        	/** Add Employee Entity to Employee's List */
		        	employeeList.add(newEmployee);
		        	
		        }
		        
		    }
		}
		
		/** Return the Array List */
		return employeeList;
		
	}
	
	/**
	 * Check if a Specific Employee exists from the given UUID
	 * @param givenEmployeeID - Given Employee UUID (String)
	 * @return - True (Does exist) False (Doesn't Exist)
	 * @throws IOException
	 */
	public static boolean doesEmployeeExist(String givenEmployeeID) throws IOException {

		/** The Employee File */
        File employeeFile = new File(empDir + givenEmployeeID + ".json");

        /** If the Employee File Exists, return true */
        if (employeeFile.exists() && employeeFile.length() > 0) {
            return true;
        }

        /** Else, if the employee doesn't exist, return false */
        return false;

    }
	
}
