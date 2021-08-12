package project;

/**
 * This class implements a Student object with unique properties and methods
 * 
 * @author Lily Deng
 * @version 1.0
 * 
 */

public class Student{
	
	/**Student's first name
	 */
	public String firstName;
	
	/**Student's last name
	 */
	public String lastName;
	
	/**Student's ID number
	 */
	public String IDNo;
	
	/** Default constructor for Student object
	 */
	public Student() {

		firstName = "";
		
		lastName = "";

		IDNo = "";
		
	}
	
	/** Parameterized constructor for Student object
	 * @param firstInput first name string
	 * @param lastInput last name string
	 * @param inputID ID number string
	 */
	public Student(String firstInput, String lastInput, String inputID) {
		
		firstName = firstInput;
		
		lastName = lastInput;
		
		IDNo = inputID;
		
	}
	
	/**Compares one Student object to another Student object based on last name, first name, and ID number
	 * @param student2 existing Student object
	 * @return numerical value
	 */
	public Integer compareTo(Student student2) {
		
		if(lastName.equals(student2.lastName)) {
		
			if(firstName.equals(student2.firstName)) {
				
					if(IDNo.equals(student2.IDNo)) {
					
						return 0;
					
					}
					
					else {
						
						return IDNo.compareTo(student2.IDNo);
						
					}
				
			}
			
			else {
				
				return firstName.compareTo(student2.firstName);
				
			}
		
		}
		
		else 
			
			return lastName.compareTo(student2.lastName);
				
		}
	
	/**Compares one Student object to another Student object based on ID number
	 * @param student2 existing Student object
	 * @return true or false value representing whether Student ID numbers are equal
	 */
	public Boolean equals(Student student2) {
		
		if(IDNo.equals(student2.IDNo)) {
			
			return true;
			
		}
		
		else {
			
			return false;
			
		}
		
	}
	
	/**Determines whether Student's ID number matches criteria - contains 1 initial and 6 consecutive digits
	 * @return true or false value representing whether Student ID matches criteria
	 */
	public Boolean verifyID() {
		
		char[] array = lastName.toCharArray();
		
		char targetInitial = array[0];
		
		boolean initial = IDNo.matches(targetInitial + "[0-9]{6}");
		
		return initial;
		
	}
	
	/**Creates string of Student properties
	 * @return string of Student's information
	 */
	public String toString() {
		
		return (this.firstName + "\t" + this.lastName + "\t" + this.IDNo + "\n");
		
	}
	
}
