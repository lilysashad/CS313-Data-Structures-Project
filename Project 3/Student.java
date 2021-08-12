
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

public class Student{
	
	public String firstName;
	
	public String lastName;
	
	public String IDNo;
	
	public Student() {

		firstName = "";
		
		lastName = "";

		IDNo = "";
		
	}
	
	public Student(String firstInput, String lastInput, String inputID) {
		
		firstName = firstInput;
		
		lastName = lastInput;
		
		IDNo = inputID;
		
	}
	
	//MENU OPTION: Search student by name
	//compares one object (StudentRecord) to another object (StudentRecord)
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
	
	//MENU OPTION: Search student by ID
	//true-false if one object's ID (StudentRecord) is equal to another object's ID (StudentRecord)
	public Boolean equals(Student student2) {
		
		if(IDNo.equals(student2.IDNo)) {
			
			return true;
			
		}
		
		else {
			
			return false;
			
		}
		
	}
	
	//checks student's ID to have initial and 6 digits
	public Boolean verifyID() {
		
		char[] array = lastName.toCharArray();
		
		char targetInitial = array[0];
		
		boolean initial = IDNo.matches(targetInitial + "[0-9]{6}");
		
		return initial;
		
	}
	
	public String toString() {
		
		return (this.firstName + "\t" + this.lastName + "\t" + this.IDNo + "\n");
		
	}
	
}
