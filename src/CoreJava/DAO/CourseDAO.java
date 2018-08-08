package CoreJava.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CoreJava.Models.Course;

public class CourseDAO {

	private static List<Course> getAllCourses() throws SQLException {
		List<Course> courses = new ArrayList<Course>();
		Course course = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		try {
			conn = OracleConnection.getConnection();
			String sql = "SELECT * FROM COURSE";
			ps = conn.prepareStatement(sql);
			result = ps.executeQuery();

			while (result.next()) {
				course = new Course();
				course.setCourse_id(result.getInt(1));
				course.setCourse_name(result.getString(2));
				course.setMinimum_gpa(result.getDouble(3));
				courses.add(course);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (result != null) {
				result.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return courses;
	}	
	
	
	
}
