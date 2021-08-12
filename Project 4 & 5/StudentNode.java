package project;

/**
 * This class implements StudentNode object with unique Student data and next/right/left pointers
 * 
 * @author Lily Deng
 * @version 1.0
 * 
 */

public class StudentNode {
	
	/**Student object containing first name, last name, and ID number
	 */
	Student data;

	/**Pointer that references next node in LinkedStudentList
	 */
	StudentNode next;
	
	/**Pointer that references node to right in LinkedStudentList
	 */
	StudentNode right;
	
	/**Pointer that references node to left in LinkedStudentList
	 */
	StudentNode left;
	
	//CONSTRUCTORS
	
	/** Default constructor for StudentNode object
	 */
	public StudentNode() {
		
		data = null;
		
		next = null;
		
	}
	
	/** Parameterized constructor for StudentNode object
	 * @param dataInput Student information (first name, last name, ID number)
	 */
	public StudentNode(Student dataInput) {
		
		data = dataInput;
		
		next = null;
		
	}
	
	/** Places Student data inside of particular StudentNode object
	 * @param dataInput Student information (first name, last name, ID number)
	 * @param nextInput particular node
	 */
	public StudentNode(Student dataInput, StudentNode nextInput) {
		
		data = dataInput;
		
		next = nextInput;
		
	}
	
	@Getter
	/**
	 * @return Student object information
	 */
	public Student getData() {
		
		return data;
	
	}

	/**
	 * @param dataInput Student data to set (first name, last name, ID number)
	 */
	public void setData(Student dataInput) {
	
		data = dataInput;
	
	}

	/**
	 * @return particular StudentNode
	 */
	public StudentNode getNext() {
	
		return next;
	
	}

	/**
	 * @param nextInput particular StudentNode
	 */
	public void setNext(StudentNode nextInput) {
	
		next = nextInput;
	
	}

}
