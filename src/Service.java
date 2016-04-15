/*
 * Name - Dhruv Arora
 * UTD ID :  - 2021212546
 * Date : 09/14/2014
 * Purpose : Assignment 1 ; Class CS-6301-015  
 */
import java.util.List;

public class Service {
	
	
	public boolean saveContents(String varFirstName, String varMiddleName,
			String varLastName, String varGender, String varEmail, String varPhoneNo, String varAddress1,
			String varAdress2, String varCity, 
			String varState, String varZipCode, String varCountry) {
	
		FileIO fileOperationsService = new FileIO();
			boolean operationSuccess = fileOperationsService.saveRecord(varFirstName, varMiddleName, varLastName,
					varGender, varEmail, varPhoneNo, varAddress1, varAdress2, varCity, varState, 
					varZipCode, varCountry);
			fileOperationsService=null;
			return operationSuccess;	
	}
	
	// Service Layer function, calls the read file function for the IO
	public List<Person> getListAddrMain(){
		FileIO fileOperationsService = new FileIO();
		return fileOperationsService.readFile();
	}
	
	public boolean deleteRecord(String varFirstName, String varMiddleName , String varLastName)
	{
		FileIO fileOperationsService = new FileIO();
		return fileOperationsService.deleteRecord(varFirstName,varMiddleName,varLastName);
	}

}
