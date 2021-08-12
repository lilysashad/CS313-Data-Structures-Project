package project;

public class StudentQueue {

	private StudentNode rear;
	
	public boolean isEmpty() {
		
		return (rear == null);
		
	}
	
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