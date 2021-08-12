/**
 * 
 * AUTHOR: LILY DENG
 * CLASS: CS313 - PROFESSOR ANNE SMITH-THOMPSON
 * 
 * Create a Student class containing Student Name, 
 * (String) ID, and an “extended” class StudentRecord 
 * which includes an ArrayList of Courses (String) 
 * and write a tester to make sure the class works correctly. 
 * (Make sure that equals (ID) and compareTo (last name) work correctly!) 
 * We will review Inheritance and Polymorphism as needed!
 */

package project;

import java.util.ArrayList;

public class StudentRecord extends Student{
	
	ArrayList<String> Courses;

	public StudentRecord(String firstName, String lastName, String ID) {
		
		super(firstName, lastName, ID);
		
		Courses = new ArrayList<String>();
		
	}
	
	public String toString() {
		
		String courseString = "";
		
		for(String c : Courses) {
			
			courseString += c + "\n";
			
		}
		
		return super.toString() + courseString;
				
	}
	
}
