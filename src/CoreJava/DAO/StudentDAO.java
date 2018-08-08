package CoreJava.DAO;

import CoreJava.Models.Student;

public class StudentDAO implements StudentDAOI{

	Student getStudentByGmail(String email) {
		
		/*This method takes a String as a parameter and 
		queries the database for an Student with such 
		an email and returns a Student Object.*/
		
	}
	
	
	Boolean validateUser(String passToValidate, String comparablePas) {
		
		/*This method takes two parameters: the first one 
		is the password from the database and the second 
		one is the password from the user input. If both 
		passwords are the same return true otherwise return false.*/
	}
	
	
	
}
