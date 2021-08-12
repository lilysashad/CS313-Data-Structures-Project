package project;

/**
 * This class contains the main program
 * 
 * @author Lily Deng
 * @version 1.0
 * 
 */

public class Test extends UserMenu{
	
	/**Runs main program with user menu options
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {
		
		UserMenu userMenu = new UserMenu();
		
		userMenu.loadRoster();
		
		userMenu.startMenu();
		
	}
	
}

	
