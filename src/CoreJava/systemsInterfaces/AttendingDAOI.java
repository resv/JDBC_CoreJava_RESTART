package CoreJava.systemsInterfaces;

import java.util.List;

import CoreJava.Models.Attending;

public interface AttendingDAOI {

	int registerStudentToCourse(Student student, Course course);
	
	List<Attending> getStudentCourse(int student_id);
	
}
