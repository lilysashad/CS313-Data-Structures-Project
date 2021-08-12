package project;

public class StudentNode {
	
	Student data;

	StudentNode next;
	
	//CONSTRUCTORS
	
	//default constructor to initialize
	public StudentNode() {
		
		data = null;
		
		next = null;
		
	}
	
	//add new node to list
	public StudentNode(Student dataInput) {
		
		data = null;
		
		next = null;
		
	}
	
	//put data in place of already-existing node
	public StudentNode(Student dataInput, StudentNode nextInput) {
		
		data = dataInput;
		
		next = nextInput;
		
	}
	
	//GETTERS/SETTERS
	
	public Student getData() {
		
		return data;
	
	}

	public void setData(Student dataInput) {
	
		data = dataInput;
	
	}

	public StudentNode getNext() {
	
		return next;
	
	}

	public void setNext(StudentNode nextInput) {
	
		next = nextInput;
	
	}

}
