package project;

import java.util.ArrayList;

/**
 * This class implements a StudentRecord object, inheriting same properties as Student class
 * 
 * @author Lily Deng
 * @version 1.0
 * 
 */

public class StudentRecord extends Student{
	
	/**Array List of type String for Student's courses
	 */
	ArrayList<String> Courses;

	/** Parameterized constructor for StudentRecord object that also creates Courses array list
	 * @param firstName first name string
	 * @param lastName last name string
	 * @param ID ID number string
	 */
	public StudentRecord(String firstName, String lastName, String ID) {
		
		super(firstName, lastName, ID);
		
		Courses = new ArrayList<String>();
		
	}
	
	/**Creates string of Student properties
	 * @return string of Student's information
	 */
	public String toString() {
		
		String courseString = "";
		
		for(String c : Courses) {
			
			courseString += c + "\n";
			
		}
		
		return super.toString() + courseString;
				
	}
	
}
