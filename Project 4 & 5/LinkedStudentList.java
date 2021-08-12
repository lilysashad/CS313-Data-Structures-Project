package project;

/**
 * This class implements a Linked List that stores data in up to 10 nodes
 * 
 * @author Lily Deng
 * @version 1.0
 * 
 */

public class LinkedStudentList extends StudentNode{
	
	/**Pointer that references first element of LinkedStudentList
	 */
	StudentNode head;
	
	/**Student array to store Students from LinkedStudentList inside
	 */
	Student[] studentArray;
	
	/**Size of LinkedStudentList, maximum is 20 
	 */
	int size;
	
	/** Default constructor for Linked List object
	 */
	public LinkedStudentList() {
		
		head = new StudentNode();
		
		size = 0;
		
	}
	
	/**States whether or not head's next pointer exists
	 * @return true or false value representing empty status of Linked List
	 */
	public boolean isEmpty() {
		
		return head.next == null;
		
	}
	
	/**Traverses and inserts node into alphabetical last name-data order into Linked List -
	 * Best/average-case runtime is θ(1), worst-case runtime is O(n)
	 * @param s a newly-created Student object
	 * @return void
	 */
	public void insertSorted(Student s){
		
//		if(size > 10) {
//			
//			throw new ArrayIndexOutOfBoundsException("Exceeds capacity of 10");
//			
//		}
		
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
	
	/**Traverses and reconfigures node pointers to exclude particular node and its data from Linked List while maintaining alphabetical order-
	 * Best/average-case runtime is θ(1), worst-case runtime is O(n)
	 * @param s already-existing Student object
	 * @return void
	 */
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
	
	/**Creates string of array values -
	 * Runtime is O(n)
	 * @return string of all of array's data
	 */
	public String toString() {
		
		String result = "";
		
		int i = 0;
		
		while(i < size) {
			
			result += this.studentArray[i].firstName + "\t" + this.studentArray[i].lastName + "\t" + this.studentArray[i].IDNo + "\n";
			
			i++;
			
		}
			
		return result;
		
	}
	
	/**Populates array with particular data of node -
	 * Runtime is O(n)
	 * @return alphabetically sorted Student array in which contains data from Linked List
	 */
	public Student[] toArray() {
		
		studentArray = new Student[20];
		
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
