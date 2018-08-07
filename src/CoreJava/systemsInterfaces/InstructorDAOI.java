package CoreJava.systemsInterfaces;

import java.util.List;

import CoreJava.Models.Instructor;

public interface InstructorDAOI {

	List<Instructor> getAllInstructors();
	
	Instructor getInstructoByGmail(String email);
	
}
