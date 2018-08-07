package CoreJava.systemsInterfaces;

import java.util.List;

import CoreJava.Models.Course;

public interface CourseDAOI {

	List<Course> getAllCourses();
	
	List<Course> getCourseByInstructor(int instructor_id);
	
}
