package project;

/**
 * This class implements a Binary Tree that stores data in up to 20 nodes.
 * 
 * @author Lily Deng
 * @version 1.0
 * 
 */
public class BinaryTree extends StudentNode{
	
	/**Topmost node of Binary Tree for traversal
	 */
	public StudentNode root;
	
	/**Student array to store Students from Binary Tree inside
	 */
	public static Student[] studentArray;
	
	/**Size of Binary Tree, maximum is 20 
	 */
	int size;
	
	/**Global variable for incrementing studentArray in copy() method
	 */
	int i = 0;
	
	/** Default constructor for BinaryTree object
	 */
	public BinaryTree() {
		
		root = null;
		
		size = 0;
		
	}
	
	/**States whether or not Binary Tree contains root
	 * @return true or false value representing empty status of Binary Tree
	 */
	public boolean isEmpty() {
		
		return (root == null);
		
	}
	
	/**Recursive call to insert new student into Binary Tree while incrementing size property - 
	 * 
	 * Best/average-case runtime is O(log(n)), worst-case runtime is O(n)
	 * @param s a newly-created Student object
	 * @return void
	 */
	public void insert(Student s) {
		
		root = insert(s, root);
		
		size++;
		
	}

	/**Adds new Student object into new node of Binary Tree recursively - 
	 * * Best/average-case runtime is O(log(n)), worst-case runtime is O(n)
	 * @param s a newly-created Student object
	 * @param root traversing tree for available nodes to place new Student object data
	 * @return first available node to contain new Student data
	 */
	private StudentNode insert(Student s, StudentNode root){
		
		if(root == null) {
			
			return new StudentNode(s);
			
		}
		
		else{
			
			if((size < 20)) {
			
				if(s.compareTo(root.data) < 0){
				
					root.left = insert(s, root.left);
			
				}
			
				else{
				
					root.right = insert(s, root.right);
			
				}
			
			}
			
		}
		
		return root;
		
	}
	
	/**Recursive call to delete existing student in Binary Tree while decrementing size property -
	 * Best/average-case runtime is O(log(n)), worst-case runtime is O(n)
	 * @param s an existing-Student object
	 * @return void
	 */
	public void delete(Student s){
		
		if(isEmpty()){
			
			
			throw new IllegalArgumentException("Tree is empty");
		}
		
		delete(s, root);
		
		size--;
		
	}
	
	/**Deletes existing student by traversing Binary Tree for correct node, while decrementing size property -
	 * Best/average-case runtime is O(log(n)), worst-case runtime is O(n)
	 * @param s an existing-Student object
	 * @param curr potential node that traverses tree
	 * @return node in which data of Student s exists in
	 */
	private static StudentNode delete(Student s, StudentNode curr){

		//if not null
		if(s.compareTo(curr.data) < 0){
		
			curr.left = delete(s, curr.left);
		
		}
		
		else if(s.compareTo(curr.data) > 0){
		
				curr.right = delete(s, curr.right);
	
		}
		
		else{ //none or 1 child
		
			if(curr.right == null){
				
				return curr.left;
			
			}
			
			if(curr.left == null){
			
				return curr.right;
		
			}	
		
			//2 children
			curr.data = findMax(curr.left);
			
			curr.left = delete(curr.data, curr.left);
	
		}
		
		return curr;
	
	}
	
	/**Recursive call to traverse Binary Tree to find node in which exists the highest Student object value exists
	 * @return highest-value Student data
	 */
	public Student findMax(){
		
		if(isEmpty()){
			
			throw new IllegalArgumentException("Tree is empty");
		
		}
		
		return findMax(root);
	
	}
	
	/**Recursively calls itself to traverse Binary Tree to find node in which exists the highest Student object value exists
	 * @param s node in which data of potential Student exists in
	 * @return Student data with highest value
	 */
	private static Student findMax(StudentNode s){
		
		if(s.next != null){
		
			return findMax(s.next);
		
		}
		
		return s.data;
	
	}
	
	/**Recursive call to traverse Binary Tree for particular String value -
	 * Best/average-case runtime is O(log(n)), worst-case runtime is O(n)
	 * @param s string value (Student last name or Student ID)
	 * @return Student data that matches String
	 */
		public Student search(String s){
			
			if(isEmpty()){
			
				throw new IllegalArgumentException("Tree is empty");
			
			}
			
			return search(s, root);
		
		}
	
		/**Recursively calls itself to search for node that contains specific String value -
		 * Best/average-case runtime is O(log(n)), worst-case runtime is O(n)
		 * @param s string value (Student last name or Student ID)
		 * @param curr potential node that traverses tree
		 * @return Student whose last name or ID matches that of string value
		 */
		private Student search(String s, StudentNode curr){
			
			if(curr == null) {
				
				return null;
				
			}
			
			//if s matches with current node's last name or ID
			else if ((s.equals(curr.data.lastName)) || (s.equals(curr.data.IDNo))) {
				
				return curr.data;
				
			}
			
			//search left child of curr node
			else if (((s.compareTo(curr.data.lastName)) < 0) || ((s.compareTo(curr.data.IDNo)) < 0)) {
				
				return search(s, curr.left);
				
			}
			
			else {
				
				return search(s, curr.right);
				
			}
			
		}
	
		/**Recursive call to populate array with particular data of node -
		 * Runtime is O(n)
		 * @return alphabetically sorted Student array in which contains data from Binary Tree
		 */
	public Student[] copy() {
		
		if(isEmpty()){
			
			throw new IllegalArgumentException("Tree is empty");
		
		}
		
		studentArray = new Student[20];
		
		copy(root);
		
		return studentArray;
		
	}
	
	/**Recursively calls itself to populate array index with data of node -
	 * Runtime is O(n)
	 * @param root node from Binary Tree
	 * @return void
	 */
	private void copy(StudentNode root) {
			
		if(root != null){
			
			copy(root.left);
			
			studentArray[i++] = root.data;
						
			copy(root.right);
		
		}
		
	}
	
	/**Recursive call to traverse Binary Tree from lowest node data value to highest -
	 * Runtime is O(n)
	 * @return void
	 */
	public void inorder() {
		
		inorder(root);
		
	}
	
	/**Recursively calls itself to traverse Binary Tree from node's left to node's right, while printing node's data -
	 * Runtime is O(n)
	 * @param s node from Binary Tree
	 * @return void
	 */
	private void inorder(StudentNode s) {
		
		if(s != null) {
		
			inorder(s.left);
		
			System.out.println(s.data);
					
			inorder(s.right);
		
		}
		
	}
	
	/**Creates string of array values -
	 * Runtime is O(n)
	 * @return string of all of array's data
	 */
	public String toString(){
		
		String result = "";
		
		int i = 0;
		
		while(i < size) {
			
			result += studentArray[i].firstName + "\t" + studentArray[i].lastName + "\t" + studentArray[i].IDNo + "\n";
			
			i++;
			
		}
			
		return result;
		
	}
	
}
