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
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class UserMenu {
	
	public static String fileName = "/Users/Forla/eclipse/Workspace/CS313_SUMProj_LILY/src/project/exportedRoster.txt";
			
	public BinaryTree rosterTree = new BinaryTree();
	
	public Student[] rosterArray;
	
	public StudentQueue waitingList = new StudentQueue();

	//method for user menu options
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
	
	//Reads file and tokenizes unordered information to insert into ordered linked list
	public void loadRoster() {
		
		TextFileInput in = new TextFileInput(fileName);
		
		String line = in.readLine();
		
		while(line != null) {
			
			//reads info between commas
			StringTokenizer token = new StringTokenizer(line, "\t");
			
			if (token.countTokens() < 5) {
				
				Student s = new Student(token.nextToken().trim(), token.nextToken().trim(), token.nextToken().trim());
			
				rosterTree.insert(s); //puts Student into ordered binary tree
				
			}
			
			line = in.readLine();
			
		}
		
		rosterTree.inorder();
	}
	
	//add student in linked list roster
	public void addStudentRoster(Student newStudent){
	    
	    if((newStudent.verifyID()) && (rosterTree.size <= 20)) {
		
	    	rosterTree.insert(newStudent);
	    
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
	
	//drop student from linked list roster
	public void removeStudentRoster(Student removeStudent) {
		
		//student's last name and id number must already exist in linked list
		if((idSearch(removeStudent.IDNo) != null) && (nameSearch(removeStudent.lastName) != null)) {
	    			
			rosterTree.delete(removeStudent);
	    
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
	
	//if student id exists in ordered linked list, return Student
	//if not, return null
	//BINARY SEARCH - 0(logn)
	public Student idSearch(String IDInput) {
		
		Student result = rosterTree.search(IDInput);

		if(result != null) {
			
			JOptionPane.showMessageDialog(null,
					"Student found!\n" + 
					result.firstName + 
			" " + result.lastName + 
			" " + result.IDNo);
		
		}
		
		else {
			
			JOptionPane.showMessageDialog(null, "Student not found");
			
		}
		
		return result;
		
	}
	
	//if student last name exists in ordered linked list, return Student
	//if not, return null
	//BINARY SEARCH - 0(logn)
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
	
	//convert linked list into array of capacity 10
	public void saveChanges() {
		
		rosterArray = rosterTree.copy();
		
	}
	
	//create new text file for updated roster
	//create new text file for queue waiting list
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
