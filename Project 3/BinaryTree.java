/*
 * 
 * AUTHOR: LILY DENG
 * CLASS: CS313 - PROFESSOR ANNE SMITH-THOMPSON
 * 
 * 
 * You will increase the class size to 20, using the 
 * waiting list to fill the class, and implement the 
 * initial linked structure as a BST. Once the 
 * registration is completed (“end of withdraw period”)
 *  the BST will be saved again as a sorted array, 
 *  and the final roster will be saved as a text file
 * 
 */

package project;

public class BinaryTree extends StudentNode{
	
	public StudentNode root;
	
	public static Student[] studentArray;
	
	int size;
	
	int i = 0;
	
	//constructor to initialize tree
	public BinaryTree() {
		
		root = null;
		
		size = 0;
		
	}
	
	public boolean isEmpty() {
		
		return (root == null);
		
	}
	
	//recursively insert Student into tree
	public void insert(Student s) {
		
		root = insert(s, root);
		
		size++;
		
	}

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
	
	//delete node from tree
	public void delete(Student s){
		
		if(isEmpty()){
			
			
			throw new IllegalArgumentException("Tree is empty");
		}
		
		delete(s, root);
		
		size--;
		
	}
	
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
	
	//RECURSIVE FINDMAX()
	public Student findMax(){
		
		if(isEmpty()){
			
			throw new IllegalArgumentException("Tree is empty");
		
		}
		
		return findMax(root);
	
	}
	
	private static Student findMax(StudentNode s){
		
		if(s.next != null){
		
			return findMax(s.next);
		
		}
		
		return s.data;
	
	}
	
	//SEARCH for input s's ID OR last name
		public Student search(String s){
			
			if(isEmpty()){
			
				throw new IllegalArgumentException("Tree is empty");
			
			}
			
			return search(s, root);
		
		}
	
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
	
	public Student[] copy() {
		
		if(isEmpty()){
			
			throw new IllegalArgumentException("Tree is empty");
		
		}
		
		studentArray = new Student[20];
		
		copy(root);
		
		return studentArray;
		
	}
	
	private void copy(StudentNode root) {
			
		if(root != null){
			
			copy(root.left);
			
			studentArray[i++] = root.data;
						
			copy(root.right);
		
		}
		
	}
	
	public void inorder() {
		
		inorder(root);
		
	}
	
	private void inorder(StudentNode s) {
		
		if(s != null) {
		
		inorder(s.left);
		
		System.out.println(s.data);
					
		inorder(s.right);
		
		}
		
	}
	
	//INORDER print- left, root, right
	//recursive call
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
