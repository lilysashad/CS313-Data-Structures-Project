/**
 * 
 * AUTHOR: LILY DENG
 * CLASS: CS313 - PROFESSOR ANNE SMITH-THOMPSON
 * 
 * Write your own LinkedStudentList class, including 
 * insertSorted(Student s), delete(Student s), isEmpty, 
 * toString()  You will create a sorted array for the roster 
 * from the list when the user selects “Save Changes”
 */

package project;

public class LinkedStudentList extends StudentNode{
	
	StudentNode head;
	
	LinkedStudentList studentRoster;
	
	Student[] studentArray;
	
	int size;
	
	//DEFAULT CONSTRUCTOR
	public LinkedStudentList() {
		
		head = new StudentNode();
		
		size = 0;
		
	}
	
	//MENU OPTION: Add student
	//insert elements alphabetically into linked list
	public void insertSorted(Student s){
		
		if(size > 10) {
			
			throw new ArrayIndexOutOfBoundsException("Exceeds capacity of 10");
			
		}
		
		StudentNode current = head.next;
		
		StudentNode prev = head;
		
		//must be negative for alphabetical order, and can NOT be null
		
		while((current != null) && (current.data.compareTo(s) < 0)) {
			
			prev = current;
			
			current = current.next;
			
		}
		
		prev.next = new StudentNode(s, current);
		
		size++;
		
	}
	
	//MENU OPTION: Remove student
	//remove element from linked list but maintain alphabetical sorted order
	public void delete(Student s){
		
		if(isEmpty()) {
			
			throw new ArrayIndexOutOfBoundsException("Roster is already empty");
			
		}
		
		StudentNode current = head.next;
		
		StudentNode prev = head;
		
		while((current != null) && (current.data.compareTo(s) < 0)) {
			
			prev = current;
			
			current = current.next;
			
		}
		
		if(current.data.equals(s)) {
		
			prev.next = current.next;
		
		}
		
		size--;
		
	}
	
	//check if list is empty
	public boolean isEmpty() {
		
		return head.next == null;
		
	}
	
	//Convert sorted array to String
	public String toString() {
		
		String result = "";
		
		int i = 0;
		
		while(i < size) {
			
			result += this.studentArray[i].firstName + "\t" + this.studentArray[i].lastName + "\t" + this.studentArray[i].IDNo + "\n";
			
			i++;
			
		}
			
		return result;
		
	}
	
	//MENU OPTION: Save change
	//Copies list into sorted array with max. 10
	public Student[] toArray() {
		
		studentArray = new Student[10];
		
		StudentNode current = head.next;
		
		StudentNode prev = head;
		
		int i = 0;
		
		while((current != null) && (i < size)) {
			
			prev = current;
			
			current = current.next;
			
			studentArray[i] = prev.data;
						
			i++;
			
		}
		
		return studentArray;
		
	}

}
