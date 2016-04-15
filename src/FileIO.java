/*
 * Name - Dhruv Arora
 * UTD ID :  - 2021212546
 * Date : 09/14/2014
 * Purpose : Assignment 1 ; Class CS-6301-015  
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileIO {
	
	// Define the Directory and the File path
	private String dirName = "./../ContactManager";
	private String fileName = "./../contactmanager/ContactManager.txt";
	
	
//	private List <Person> listAddr = new ArrayList<Person>();
	
	
	/* public void fileStatus() ->
	 * Method to check if a file & directory exists.
	 * If it doesn't exist it'll call a function to create them.
	 * This is just a stub function,which is available in main
	 * * and calls other functions to do the job.
	 * This method is public and available outside the class.
	 */
	public void fileStatus()
	{
		// Create a file to handle File & Dir Creation 
		File directoryHandle = new File(dirName);
		File fileHandle = new File(fileName);
		
//		System.out.println("Checking file Status ... ");
		// Checks if directory doesn't exist, if it doesn't then calls the create Dir function
		if(!directoryHandle.exists())
		{
//			System.out.println("Dir doesn't exist will b created.. ");
			// Call the method to create the directory
			createDir();
		}
		// Checks if file doesn't exist, if it doesn't then calls the create File function
		if(!fileHandle.exists())
		{
//			System.out.println("File doesn't exist will b created.. ");
			// Call the method to create the file
			createFile();
			
//			System.out.println("\tSome Sample Records will be created");
			// Creating some default records
			saveRecord("Dhruv","","Arora","M","dhruvstrz@gmail.com","+123456","Chatham","","Dallas","TX","12345","USA");
			saveRecord("Harry","J","Potter","M","harry@hogwarts.com","+000","Hogwarts","","Unknown","UK","000-UK","Unknown");
			saveRecord("Ron","","Weasely","M","RON@hogwarts.COM","+123","Hogwarts","","Unknown","UK","000-UK","Unknown");
			saveRecord("Hermoine","","Granger","F","Hermoine.Granger@hogwarts.com","+0001","Hogwarts School of Witchcraft","","Unknown","UK","000-UK","UnK");
		}
	}
	
	/* Public class accessible to others, 
	 * will call a function to read the contacts from the saved file, 
	 * and returns the result as a List<Person>
	 */
	public List<Person> readFile()
	{
		// Calls the method responsible for reading the file contents from the Address List on the Disk
		return readFileFromDisk();
	}
	
	/* Public class accessible to others, 
	 * will call a function to save the contacts to saved file
	 */
	public boolean saveRecord(String varFirstName, String varMiddleName,
			String varLastName, String varGender, String varEmail, String varPhoneNo, String varAddress1,
			String varAdress2, String varCity, 
			String varState, String varZipCode, String varCountry)
	{
//		writeToFile(listAddr);
		return saveRecordToDisk(varFirstName, varMiddleName,
				varLastName, varGender, varEmail, varPhoneNo, varAddress1,
				varAdress2, varCity, varState, varZipCode, varCountry);
	}
	
	/*
	 * This method creates a file at the above given path.
	 * Method is private and not available outside this class.
	 */
	private void createFile()
	{
		// Create a fileHandle for File Creation 
		File fileHandle = new File(fileName);
		
		try {
			
			//Create the File
			fileHandle.createNewFile();
//			System.out.println("\tFile created");
		} 
		catch (IOException ioException) 
		{
		    // Some I/O Failure
//			System.out.println("File create error: ");
		    ioException.printStackTrace();
		}
	}
	
	/* private void createDir() ->
	 * This method creates a directory at the above given path.
	 * Method is private and not available outside this class.
	 */
	private void createDir()
	{
		// Create a DirectoryHandle for Dir Creation 
		File directoryHandle = new File(dirName);
		
		try{
//			System.out.println("\tDir will be created in "+directoryHandle.getCanonicalPath());
			//Create the Directory
			directoryHandle.mkdir();
//			System.out.println("\tDir created");
		}
		catch (Exception exception) 
		{
		    // Some I/O Failure
//		    System.out.println("Directory create error: ");
		    exception.printStackTrace();
		}
	}
	

	/*
	 * This method is used to read the File contents from the Disk, and store them in a list and then returns the list.
	 * * First it reads from the file line by line, then we use a delimiter to split the line into each constituent property, 
	 * * and then we stores each User Contact as an Object of the Person Class.
	 * * Then stores it in a list of objects, List<Person>, and then finally returns that list.
	 *
	private List<Person> readFileFromDisk()
	{
		boolean flg=false;
		// List will be used to hold the Person objects as they are read from the file
		List <Person> listPerson = new ArrayList<Person>();
		try
		{
//			System.out.println("Starting reading from file...");
			// Read in default encoding
			FileReader fileReader = new FileReader(fileName);
			// Wrapping in Buffered Reader
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			//This string will be used to take the value as each line os read from the file
			String line ;

			
				/* While loop used for reading, reads from the file line by line till the end of file is reached.
				 * The split function is used to split each line read into it's constituent properties, then the result is stored in a string array.
				 * this string array is used to initialize a Person object. Each Person object is stored in a List of Person objects then, and the list is returned to the calling func
				 *
				while((line = bufferedReader.readLine())!=null)
				{
					String delimited[] = null;
					// Split each line into it's constituent properties, then the result is stored in a string array.
					delimited = line.split("\\|");
//					System.out.println("\t\tPrinting the line read -> "+line);
					
					// This will make sure to take only the correct data in the correct format
					if(delimited.length == 12)
					{
						//	string array is used to initialize a Person object.
						Person p1 = new Person(delimited[0],delimited[1],delimited[2],delimited[3],
							delimited[4],delimited[5],delimited[6],delimited[7],delimited[8],delimited[9],delimited[10],delimited[11]);
						// Each Person object is stored in a List of Person
						listPerson.add(p1);
						System.out.println(p1);
						flg=true;
					}
					
				}
				// Closing the connections
				bufferedReader.close();
				fileReader.close();
//				System.out.println("Read Successfully... And wrote successfully to List... ");
			
		}
		catch(FileNotFoundException fnfe)
		{
			fnfe.printStackTrace();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		if(flg)
		{
			System.out.println("flag "+flg);
		}
		// Return the result
		return listPerson;
	} */
	
	/*
	 * This method is used to read the File contents from the Disk, and store them in a list and then returns the list.
	 * * First it reads from the file line by line, then we use a delimiter to split the line into each constituent property, 
	 * * and then we stores each User Contact as an Object of the Person Class.
	 * * Then stores it in a list of objects, List<Person>, and then finally returns that list.
	 */
	private List<Person> readFileFromDisk()
	{
		boolean flg=false;
		// List will be used to hold the Person objects as they are read from the file
		List <Person> listPerson = new ArrayList<Person>();
		try ( BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName)) )
		{

			//This string will be used to take the value as each line os read from the file
			String line ;
			
			/* While loop used for reading, reads from the file line by line till the end of file is reached.
			 * The split function is used to split each line read into it's constituent properties, then the result is stored in a string array.
			 * this string array is used to initialize a Person object. Each Person object is stored in a List of Person objects then, and the list is returned to the calling func
			 */
			while((line = bufferedReader.readLine())!=null)
			{
				String delimited[] = null;
				// Split each line into it's constituent properties, then the result is stored in a string array.
				delimited = line.split("\\|");
//				System.out.println("\t\tPrinting the line read -> "+line);
				
				// This will make sure to take only the correct data in the correct format
				if(delimited.length == 12)
				{
					//	string array is used to initialize a Person object.
					Person p1 = new Person(delimited[0],delimited[1],delimited[2],delimited[3],
						delimited[4],delimited[5],delimited[6],delimited[7],delimited[8],delimited[9],delimited[10],delimited[11]);
					// Each Person object is stored in a List of Person
					listPerson.add(p1);
					System.out.println(p1);
					flg=true;
				}
			}
//			System.out.println("Read Successfully... And wrote successfully to List... ");
			
		}
		catch(FileNotFoundException fnfe)
		{
			fnfe.printStackTrace();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
		if(flg)
		{
			System.out.println("flag "+flg);
		}
		// Return the result
		return listPerson;
	}
	
	/*
	 * This method is used to write a single record to the File on the Disk, This function is executed after the user clicks save and stores only one record. 
	 * Each record is stored in a file , each property is separated using a delimiter, here we have used a '|' 
	 * returns True when save successful.
	 */
	private boolean saveRecordToDisk(String varFirstName, String varMiddleName,
			String varLastName, String varGender, String varEmail, String varPhoneNo, String varAddress1,
			String varAdress2, String varCity, 
			String varState, String varZipCode, String varCountry)
	{
		FileWriter fileWriter;
		BufferedWriter bufferedWriter;
		try{
//			System.out.println("Trying to save record to disk ...");
			// Assume default encoding
			// true denotes file will not be overwritten each time, but will insert data in same file
			fileWriter = new FileWriter(fileName,true);
			//wrap FileWriter in BufferedWriter
			bufferedWriter= new BufferedWriter(fileWriter);
			
			// Write the data in a single line
			bufferedWriter.write(varFirstName.toUpperCase()+"|"+varMiddleName.toUpperCase()+"|"+varLastName.toUpperCase()+"|"+varGender.toUpperCase()+"|"+varEmail.toUpperCase()+"|"+varPhoneNo.toUpperCase()+"|"+varAddress1.toUpperCase()+"|"+
					varAdress2.toUpperCase()+"|"+varCity.toUpperCase()+"|"+ varState.toUpperCase()+"|"+varZipCode.toUpperCase()+"|"+varCountry.toUpperCase());
			//Goto next line.. so that next time record will be added in the next line
			bufferedWriter.newLine();
	        
	        /*
	         * Will add checksum functionality later and will add back files later
	         */
	        
			// close and flush connections
	        fileWriter.flush();
	        bufferedWriter.flush();
			bufferedWriter.close();
			fileWriter.close();
			
			// If successful return true;
//			System.out.println("Record saved successfully");
			return true;
		}
		catch(Exception exception)
		{
//			System.out.println("Exception during save: ");
			exception.printStackTrace();
		}
		// If failed return false
//		System.out.println("Coudn't Save ...");
		return false;
	}
	
	
	/*
	 * Used for deleting a record the user selects from the GUI...
	 * First it reads the list from the disk. Then it searches for the record from the read List and then deletes the record if found and then stores the new list in the file...
	 */
	public boolean deleteRecord(String varFirstName, String varMiddleName , String varLastName)
	{
		// Boolean to let us know if the record was found and deleted
		boolean wasDeleted = false;
					
		try{
			
//			System.out.println("Have to delete "+varFirstName+" "+varMiddleName+" "+varLastName);
			
			// will be used for storing the list after reading from the disk
			List <Person> listRead = new ArrayList<Person>();
				
			// Read the current data into the list...
			listRead = readFile();
			/*
			 * Iterate over each object and check if the name property of each object is the same as the one we have to delete, if so then, delete the object and then write the list again to file
			 */
			for(Person person : listRead)
			{
				if(person.varFirstName.equalsIgnoreCase(varFirstName) && person.varMiddleName.equalsIgnoreCase(varMiddleName) 
						&& person.varLastName.equalsIgnoreCase(varLastName) )
				{
//					System.out.println("\t Record found.. .deleting...");
					listRead.remove(person);
					wasDeleted = true; // set to true, means record was deleted
//					System.out.println("\t Delete success.. ");
					break; // Break here, as as only one occurrence of a particular combination of First Name + Middle Name + Last Name are allowed
				}
			}
			
			// if record was deleted then write the new list to disk..
			if(wasDeleted)
			{
			    boolean wasWritten = writeListToDisk(listRead); // Boolean to know if the file was successfully written
//			    if(wasWritten)
//			    	System.out.println("\t The write to disk of new list after delete was also a success");
			    wasDeleted=wasWritten;
			}
			return wasDeleted;							
		}
		catch (Exception exception)
		{
//			System.out.println("Exception during deleting : ");
			exception.printStackTrace();
		}
		return wasDeleted;
	}
	
	/*
	 * Used to write the whole list to the Disk.. works similar to saveRecordsToDisk function, only difference is it iterates over each record and saves it
	 */
	public boolean writeListToDisk(List<Person> listToWrite)
	{
		
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null; 
		try{
			// Assume default encoding
			// We usually write as, FileWriter(fileName,true). BUT NO 'true' here denotes file will not be overwritten each time, but will insert data in same file.
			fileWriter = new FileWriter(fileName);
			//wrap FileWriter in BufferedWriter
			bufferedWriter= new BufferedWriter(fileWriter);
			for(Person p : listToWrite)
		    {
			bufferedWriter.write(p.varFirstName+"|"+p.varMiddleName+"|"+p.varLastName+"|"+p.varGender+"|"+p.varEmail+"|"+p.varPhoneNo+"|"
		    +p.varAddress1+"|"+p.varAdress2+"|"+p.varCity+"|"+p.varState+"|"+p.varZipCode+"|"+p.varCountry);
			bufferedWriter.newLine();
			}

			// close and flush connections
	        fileWriter.flush();
	        bufferedWriter.flush();
			bufferedWriter.close();
			fileWriter.close();
			return true;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

}