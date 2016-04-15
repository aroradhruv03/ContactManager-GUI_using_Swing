/*
 * Name - Dhruv Arora
 * UTD ID :  - 2021212546
 * Date : 09/14/2014
 * Purpose : Assignment 1 ; Class CS-6301-015  
 */
import java.io.Serializable;

/**
 * The objects of this class will be used for storing a Person Information. The list of these persons together will constitute as the Address List
 * Something like a Java Bean Class. But instead of using getters and setters. I used a constructor here.
 */
public class Person implements Serializable {
	/**
	 * The serialization runtime associates with each serializable class a version number, 
	 * called a serialVersionUID, which is used during deserialization to verify that the 
	 * sender and receiver of a serialized object have loaded classes for that object that 
	 * are compatible with respect to serialization.
	 */
	private static final long serialVersionUID = 1L;

	public String varFirstName;
	public String varMiddleName;
	public String varLastName;
	public String varGender;
	public String varEmail;
	public String varPhoneNo;
	public String varAddress1;
	public String varAdress2;
	public String varCity;
	public String varState;
	public String varZipCode;
	public String varCountry;
	
	//Will be sued for initializing the Object var to default
	public Person()  {
		super();
	}

	// Constructor - 
	public Person(String varFirstName, String varMiddleName,
			String varLastName, String varGender, String varEmail,
			String varPhoneNo, String varAddress1, String varAdress2,
			String varCity, String varState, String varZipCode,
			String varCountry) {
		super();
		this.varFirstName = varFirstName;
		this.varMiddleName = varMiddleName;
		this.varLastName = varLastName;
		this.varGender = varGender;
		this.varEmail = varEmail;
		this.varPhoneNo = varPhoneNo;
		this.varAddress1 = varAddress1;
		this.varAdress2 = varAdress2;
		this.varCity = varCity;
		this.varState = varState;
		this.varZipCode = varZipCode;
		this.varCountry = varCountry;
	}
}