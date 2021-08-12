/**
 * 
 * AUTHOR: LILY DENG
 * CLASS: CS313 - PROFESSOR ANNE SMITH-THOMPSON
 * 
 * Write a program that can maintain a roster of Student, 
 * using a LinkdStudentList to enter students originally, and 
 * then storing the students into a sorted array. with a maximum 
 * of 10 students in the roster. The program should be able to 
 * search for a given student, add a student, and drop a student. 
 * The roster should be saved as a text file. The data type should
 * be Student for this part. (We will use the polymorphism for 
 * StudentRecord  in Project #2.) 
 * 
 * You may use a text file to store the original students 
 * (to make it easier for you to test) and load that unsorted 
 * roster into the linked list.
 */

package project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This class implements a JOptionPane implementation of the user menu that provides users options to adjust Student roster
 * 
 * @author Lily Deng
 * @version 1.0
 * 
 */

/**Initializes menu with options
 */
public class UserMenu {
	
	/**Name of specific .txt file to read roster from
	 */
	public static String fileName = "/Users/Forla/eclipse/Workspace/CS313_SUMProj_LILY/src/project/exportedRoster.txt";
			
	/**Initialized Binary Tree to store Students in 
	 */
	public BinaryTree rosterTree = new BinaryTree();
			
	/**Initialized Student hash table with chaining (list) to prevent collisions
	 */
	public ArrayList<Student> rosterTable[] = new ArrayList[100];
	
	/**Student array to save changes in and read to export file
	 */
	public Student[] rosterArray;
	
	/**Initialized queue for waiting list
	 */
	public StudentQueue waitingList = new StudentQueue();

	/**Initializes JOptionPane window with user options
	 * @return void
	 */
	public void startMenu() {
		
		JFrame optionsFrame = new JFrame("Roster Options Menu");
		
		String[] menuItems = {
				"Add student",
				"Drop student",
				"Search student by ID",
				"Search student by name",
				"Save"};
		
		String chooseOption = (String) JOptionPane.showInputDialog
				(optionsFrame, "Select action", 
						"Roster Options Menu", 
						JOptionPane.QUESTION_MESSAGE, 
						null, 
						menuItems, 
						menuItems[0]);
		
		//exit user menu
		if(chooseOption == null) {
			
			export();
			
			JOptionPane.showMessageDialog(null, "Goodbye!");
			
	    	System.exit(0);
			
		}
		
		//operate specific user selection
		else{
			
			userAction(chooseOption);
			
		}
		
	}
	
	/**Reads user's selection and executes respective method
	 * @param userOption user's selected menu option
	 * @return void
	 */
	public void userAction(String userOption) {
	    
	    if(userOption.equals("Add student")) {
	    	
			JFrame userInformation = new JFrame("New Student Information");
	    	
	    	String firstName = JOptionPane.showInputDialog(userInformation, "Enter student's first name");
		    
	    	String lastName = JOptionPane.showInputDialog(userInformation, "Enter student's last name");
		    
	    	String IDNo = JOptionPane.showInputDialog(userInformation, "Enter student's ID number");
		    
		    Student newStudent = new Student(firstName, lastName, IDNo);
	    	
	    	addStudentRoster(newStudent);
	    	
	    }
	    
	    else if(userOption.equals("Drop student")) {
	    	
	    	JFrame userInformation = new JFrame("New Student Information");
	    	
	    	String firstName = JOptionPane.showInputDialog(userInformation, "Enter student's first name");
		    
	    	String lastName = JOptionPane.showInputDialog(userInformation, "Enter student's last name");
		    
	    	String IDNo = JOptionPane.showInputDialog(userInformation, "Enter student's ID number");
		    
		    Student removeStudent = new Student(firstName, lastName, IDNo);
	    	
	    	removeStudentRoster(removeStudent);
		    
	    }
	    
	    else if(userOption.equals("Search student by ID")) {
	    	
	    	JFrame searchFrame = new JFrame("Search Roster");
	    	
	    	String studentID = JOptionPane.showInputDialog(searchFrame, "Enter student's ID");
	    	
	    	idSearch(studentID);
		    
	    }
	    
	    else if(userOption.equals("Search student by name")) {
	    	
	    	JFrame searchFrame = new JFrame("Search Roster");
	    	
	    	String lastName = JOptionPane.showInputDialog(searchFrame, "Enter student's last name");
	    	
	    	nameSearch(lastName);
		    
	    }
	    
	    else if(userOption.equals("Save changes")) {
	    	
	    	saveChanges();
	    	
	    }
	    
	    else if(userOption.equals("Save")) {
	    	
	    	export();
	    	
	    }

    	startMenu();
	    
	}
	
	/**Reads .txt file and tokenizes unordered Student information to insert into Binary Tree and roster hash table
	 * @return void
	 */
	public void loadRoster() {
		
		TextFileInput in = new TextFileInput(fileName);
		
		String line = in.readLine();
		
		for(int i = 0; i < 100; i++) {
			
			rosterTable[i] = new ArrayList<Student>();
			
		}
		
		while(line != null) {
			
			//reads info between commas
			StringTokenizer token = new StringTokenizer(line, "\t");
			
			if (token.countTokens() < 5) {
				
				Student s = new Student(token.nextToken().trim(), token.nextToken().trim(), token.nextToken().trim());
			
				rosterTree.insert(s); //puts Student into ordered binary tree
				
				rosterTable[getKey(s.IDNo)].add(s);
				
			}
			
			line = in.readLine();
			
		}
		
		rosterTree.inorder();
	}
	
	/**Inserts student into Binary Tree and roster hash table, and if tree surpasses size of 20, places student into waiting list queue-
	 * Average/best case runtime is Θ(1), worst case for Θ(n) for roster hash table
	 * @param newStudent newly-created Student object
	 * @return void
	 */
	public void addStudentRoster(Student newStudent){
	    
	    if((newStudent.verifyID()) && (rosterTree.size <= 20)) {
		
	    	rosterTree.insert(newStudent);
	    	
	    	rosterTable[getKey(newStudent.IDNo)].add(newStudent);
	    
	    	JOptionPane.showMessageDialog(null, "New student " + newStudent.firstName + " " + newStudent.lastName + " has been added!");
	    		    	    	
	    	rosterTree.inorder();
	    	
	    }
	    
	    else if ((!newStudent.verifyID())){
	    	
		    JOptionPane.showMessageDialog(null, "Can not add student");
	    	
	    }
	    
	    else {
	    	
	    	waitingList.enqueue(newStudent);
	    	
	    	JOptionPane.showMessageDialog(null, newStudent.firstName + " " + newStudent.lastName + " placed on waiting list");
	    	
	    }
		
	}
	
	/**Hash function to delegate Students to buckets in roster hash table based on last 2 digits of ID number
	 * @param ID New student's ID number
	 * @return unique bucket number for particular student
	 */
	public int getKey(String ID) {
		
		int slot = Integer.parseInt(ID.substring(1)) % 100;
		
		return slot;
		
	}
	
	/**Deletes student from Binary Tree and roster hash table if student's ID and last name matches with any in Binary Tree, and if tree size is less than 20, dequeues from waiting list queue and inserts into Binary Tree - 
	 * Average/best runtime is Θ(1), worst case runtime is Θ(n) for roster hash table
	 * @param removeStudent already-existing Student object
	 * @return void
	 */
	public void removeStudentRoster(Student removeStudent) {
		
		if((idSearch(removeStudent.IDNo) != null) && (nameSearch(removeStudent.lastName) != null)) {
	    			
			rosterTree.delete(removeStudent);
			
			rosterTable[getKey(removeStudent.IDNo)].remove(removeStudent);
	    
			JOptionPane.showMessageDialog(null, "Student has been removed!");
			
			rosterTree.inorder();
			
		}
		
		else {
			
			JOptionPane.showMessageDialog(null, "Can not remove student");
			
		}
				    
		if(rosterTree.size < 20) {
		
	    	if(!waitingList.isEmpty()) {
		    	
	    		addStudentRoster(waitingList.dequeue());
	    		
	   		}
	    		
	   	}
	    			
	}
	
	/**Searches for key Student in roster hash table that matches particular ID- calls Binary Tree nameSearch if one bucket contains multiple Students - 
	 * Average/best runtime is Θ(1), worst case runtime is Θ(n) for roster hash table
	 * @param IDInput ID string
	 * @return Student whose ID matches input
	 */
	public Student idSearch(String IDInput) {
		
		Student result = null;
		
		if(rosterTable[getKey(IDInput)] != null) {
			
			if(rosterTable[getKey(IDInput)].size() > 1) {
				
				JFrame searchFrame = new JFrame("Search Roster");
		    	
		    	String lastName = JOptionPane.showInputDialog(searchFrame, "Enter student's last name");
		    	
		    	result = nameSearch(lastName);
					
			}
			
			else {
			
				result = rosterTable[getKey(IDInput)].get(0);
				
			}

			JOptionPane.showMessageDialog(null,
					"Student found!\n" + 
						result.firstName + 
						" " + result.lastName + 
						" " + result.IDNo);
				
		}
			
		return result;
		
	}
	
	/**Searches for Student in Binary Tree that matches particular last name - calls Search method in BinaryTree class
	 * @param lastNameInput last name string
	 * @return Student whose last name matches input
	 */
	public Student nameSearch(String lastNameInput) {
		
		Student result = rosterTree.search(lastNameInput);
		
		if(result != null) {
			
			JOptionPane.showMessageDialog(null,
					"Student found!\n" + 
			rosterTree.search(lastNameInput).firstName + 
			" " + rosterTree.search(lastNameInput).lastName + 
			" " + rosterTree.search(lastNameInput).IDNo);
			
		}
		
		else {
			
			JOptionPane.showMessageDialog(null, "Student not found");
			
		}
		
		return result;
	
	}
	
	/**converts Binary Tree into array of capacity 20 - calls copy method in BinaryTree class
	 * @return void
	 */
	public void saveChanges() {
		
		rosterArray = rosterTree.copy();
		
	}
	
	/**Writes BinaryTree roster and waiting list queue into text files
	 * @return void
	 */
	public void export() {
		
		saveChanges();
		
    	File rosterFile = new File("newRoster.txt");
    	
    	File waitingListFile = new File("waitingList.txt");
    	
    	JFrame exportFrame = new JFrame("Export roster file");

        try (Writer writer = new BufferedWriter(new FileWriter(rosterFile))) {
        	
            String contents = rosterTree.toString();

            writer.write(contents);
            
            JOptionPane.showMessageDialog(exportFrame, "Roster has been exported!");
            
        } catch (IOException e) {
        	
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(exportFrame, "Can not export roster file");
            
        }
        
        try (Writer writer = new BufferedWriter(new FileWriter(waitingListFile))) {

            writer.write(waitingList.toString());
            
            JOptionPane.showMessageDialog(exportFrame, "Waiting list has been exported!");
            
        } catch (IOException e) {
        	
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(exportFrame, "Can not export waiting list file");
            
        }
		
	}
	
}
