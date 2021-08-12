package project;

/**
 * This class implements a Student waiting list queue with LinkedList implementation
 * 
 * @author Lily Deng
 * @version 1.0
 * 
 */

public class StudentQueue {

	/**Pointer that references rear of LinkedStudentList
	 */
	private StudentNode rear;
	
	/**States whether or not queue contains rear data
	 * @return true or false value representing empty status of queue
	 */
	public boolean isEmpty() {
		
		return (rear == null);
		
	}
	
	/**Inserts Student into rear of queue -
	 * Best/average-case runtime is O(1)
	 * @param s a newly-created Student object
	 * @return void
	 */
	public void enqueue(Student s) {
		
		if(isEmpty()){
			
			rear = new StudentNode(s);
			
			rear.next = rear;
			
		}
		
		else{
			
			rear.next = new StudentNode(s, rear.next);
			
			rear = rear.next;
			
		}
		
	}
	
	/**Deletes first Student in queue -
	 * Best/average-case runtime is O(1)
	 * @return first Student in queue
	 */
	public Student dequeue() {
		
		if(isEmpty()){
			
			throw new ArrayIndexOutOfBoundsException("Queue is empty");
		}
		
		Student oldFront = rear.next.data;
		
		if(rear == rear.next){
			
			rear = null;
			
		}
		
		else{
			
			rear.next = rear.next.next;
			
		}
		
		return oldFront;	
	}
	
	/**Creates string of queue values -
	 * Runtime is O(n)
	 * @return string of all of queue's Student data
	 */
	public String toString() {
		
		String result = "";
		
		if(isEmpty()) {
			
			result = "Queue is empty";
			
		}
		
		else {
		
			StudentNode curr = rear.next;
		
			while(curr != rear) {
			
				result += curr.data.firstName + "\t" + curr.data.lastName + "\t" + curr.data.IDNo + "\n";
			
				curr = curr.next;
			
			}
		
			result += rear.data.firstName + "\t" + rear.data.lastName + "\t" + rear.data.IDNo + "\n";
		
			}
		
        return result;
		
	}
	
}