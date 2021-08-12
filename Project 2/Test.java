
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

public class Test extends UserMenu{
	
	public static void main(String[] args) {
		
		UserMenu userMenu = new UserMenu();
		
		userMenu.loadRoster();
		
		userMenu.startMenu();
		
	}
	
}

	
