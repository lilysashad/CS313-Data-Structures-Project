
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

public class Test {
	
	public static void main(String[] args) {
		
		StudentRecord test1 = new StudentRecord("Lily", "Deng", "D718699");
		
		test1.Courses.add("CS313");
		
		test1.Courses.add("CS316");
		
		test1.Courses.add("CS323");
		
		System.out.println(test1.toString());
		
		
		StudentRecord test2 = new StudentRecord("Polly", "Flower", "D111111");
		
		test2.Courses.add("CS313");
		
		test2.Courses.add("CS316");
		
		test2.Courses.add("CS323");
		
		System.out.println(test2.toString());
		
		if(test1.compareTo(test2) < 0) {
			
			System.out.println(test1.toString());
			
		}
		
		else {
			
			System.out.println(test2.toString());
			
		}
						
		System.out.println("Is Lily's ID equal to Emily's ID? \t" + test1.equals(test2));
		
		System.out.println("Is Lily's ID valid? \t" + test1.verifyID());
		
		System.out.println("Is Polly's ID valid? \t" + test2.verifyID());

	}

}
