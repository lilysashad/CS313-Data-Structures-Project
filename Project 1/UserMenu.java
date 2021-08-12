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
	
	public static String fileName = "/Users/Forla/eclipse/Workspace/CS313_SUMProj_LILY/src/project/CS313_Test_file.txt";
	
	public LinkedStudentList rosterList = new LinkedStudentList();
			
	public Student[] rosterArray;

	//method for user menu options
	public void startMenu() {
		
		JFrame optionsFrame = new JFrame("Roster Options Menu");
		
		String[] menuItems = {"Load roster from file", 
				"Add student",
				"Remove student",
				"Search student by ID",
				"Search student by name",
				"Save changes",
				"Export roster file"};
		
		String chooseOption = (String) JOptionPane.showInputDialog
				(optionsFrame, "Select action", 
						"Roster Options Menu", 
						JOptionPane.QUESTION_MESSAGE, 
						null, 
						menuItems, 
						menuItems[0]);
		
		//exit user menu
		if(chooseOption == null) {
			
			JOptionPane.showMessageDialog(null, "Goodbye!");
	    	
	    	System.exit(0);
			
		}
		
		//operate specific user selection
		else{
			
			userAction(chooseOption);
			
		}
		
	}
	
	public void userAction(String userOption) {
	    
	    if(userOption.equals("Load roster from file")) {
	    	
	    	loadRoster();
	    	
	    }
	    
	    else if(userOption.equals("Add student")) {
	    	
			JFrame userInformation = new JFrame("New Student Information");
	    	
	    	String firstName = JOptionPane.showInputDialog(userInformation, "Enter student's first name");
		    
	    	String lastName = JOptionPane.showInputDialog(userInformation, "Enter student's last name");
		    
	    	String IDNo = JOptionPane.showInputDialog(userInformation, "Enter student's ID number");
		    
		    Student newStudent = new Student(firstName, lastName, IDNo);
	    	
	    	addStudentRoster(newStudent);
	    }
	    
	    else if(userOption.equals("Remove student")) {
	    	
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
	    
	    else if(userOption.equals("Export roster file")) {
	    	
	    	File file = new File("exportedRoster.txt");
	    	
	    	JFrame exportFrame = new JFrame("Export roster file");

	        try (Writer writer = new BufferedWriter(new FileWriter(file))) {
	        	
	            String contents = rosterList.toString();

	            writer.write(contents);
	            
	            JOptionPane.showMessageDialog(exportFrame, "Roster has been exported!");
	            
	        } catch (IOException e) {
	        	
	            e.printStackTrace();
	            
	            JOptionPane.showMessageDialog(exportFrame, "Can not export roster file");
	            
	        }
	    	
	    }

    	startMenu();
	    
	}
	
	//Reads file and tokenizes unordered information to insert into ordered linked list
	public void loadRoster() {
		
		JFrame rosterLoadFrame = new JFrame("Roster Loaded");
		
		TextFileInput in = new TextFileInput(fileName);
		
		String line = in.readLine();
		
		while(line != null) {
			
			//reads info between commas
			StringTokenizer token = new StringTokenizer(line, ",");
			
			if (token.countTokens() < 5) {
				
				Student s = new Student(token.nextToken().trim(), token.nextToken().trim(), token.nextToken().trim());
				
				rosterList.insertSorted(s); //puts Student into ordered linked list
			
			}
			
			line = in.readLine();
			
		}
		
		saveChanges();
		
		System.out.println(rosterList.toString());
    	
    	JOptionPane.showMessageDialog(rosterLoadFrame, "Roster has been loaded!");
    	
	}
	
	public void addStudentRoster(Student newStudent){
	    
	    if(newStudent.verifyID()) {
	    	
	    	rosterList.insertSorted(newStudent);	
	    	
	    	saveChanges();
	    
	    	JOptionPane.showMessageDialog(null, "New student " + newStudent.firstName + " " + newStudent.lastName + " has been added!");
	    	
	    	System.out.println(rosterList.toString());
	    
	    }
	    
	    else {
	    	
		    JOptionPane.showMessageDialog(null, "Can not add student");
	    	
	    }
		
	}
	
	public void removeStudentRoster(Student removeStudent) {
		
		//student's last name and id number must already exist in linked list
		if((idSearch(removeStudent.IDNo) != null) && (nameSearch(removeStudent.lastName) != null)) {
	    
			rosterList.delete(removeStudent);
	    
			JOptionPane.showMessageDialog(null, "Student has been removed!");
	    
			saveChanges();
			
			System.out.println(rosterList.toString());
			
		}
		
		else {
			
			JOptionPane.showMessageDialog(null, "Can not remove student");
			
		}
	    			
	}
	
	//if student id exists in ordered linked list, return Student
	//if not, return null
	public Student idSearch(String IDInput) {
		
		saveChanges();
		
    	int i = 0;
    	
    	Student result = null;
    	
    	while(rosterArray[i] != null){
			
			if(IDInput.equals(rosterArray[i].IDNo)) {
			
				JOptionPane.showMessageDialog(null,
						"Student found!\n" + 
				rosterArray[i].firstName + 
				" " + rosterArray[i].lastName + 
				" " + rosterArray[i].IDNo);
				
				result = rosterArray[i];
			
			}
	
			i++;
			
    	}
    	
    	if(result == null) {
    		
    		JOptionPane.showMessageDialog(null, "Student not found");
    		
    	}
    	
    	return result;
		
	}
	
	//if student last name exists in ordered linked list, return Student
	//if not, return null
	public Student nameSearch(String lastNameInput) {
		
		saveChanges();
    	
		int i = 0;
		
		Student result = null;
		
	    while((rosterArray[i] != null)){
				
				if(lastNameInput.compareTo(rosterArray[i].lastName) == 0) {
				
					JOptionPane.showMessageDialog(null,
							"Student found!\n" + 
					rosterArray[i].firstName + 
					" " + rosterArray[i].lastName + 
					" " + rosterArray[i].IDNo);
					
					result = rosterArray[i];
				
				}
		
			i++;
				
		}
	    
	    if(result == null) {
    		
    		JOptionPane.showMessageDialog(null, "Student not found");
    		
    	}
    	
    	return result;
			
	}
	
	//convert linked list into array of capacity 10
	public void saveChanges() {
		
		rosterArray = rosterList.toArray();
		
	}
	
}
