/*
 * Name - Dhruv Arora
 * Date : 09/14/2014
 * Purpose : Assignment 1 ; Class CS-6301-015
 */


import java.awt.EventQueue;

public class MainClass   {

    /*
     * About the class : This class will store the main() method.
     * This class invokes the GUI for the program, (MainGUI.java).
     */
    public static void main(String[] args) {

//		System.out.println("Hello.. Starting the program");

        //Create a object for the FileIO Class to do the various file operations.
		FileIO fileOperations = new FileIO();

		// Function to check if dir & File exists, if not create it.
		fileOperations.fileStatus();

//		System.out.println("Launching GUI ... ");
		/*
		 * Launch The Main GUI from the presentation layer
		 */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

        System.out.println("Hello");

    }
}