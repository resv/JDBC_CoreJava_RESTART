package CoreJava.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CoreJava.Models.Teaching;
import CoreJava.systemsInterfaces.TeachingDAOI;

public class TeachingDAO implements TeachingDAOI{

	/*getIntructorsCourses – This method takes no parameters 
	and queries the database for every instructor assigned 
	to a course.*/
	private static List<Teaching> getInstructorsCourses() throws SQLException {

		List<Teaching> teachingList = new ArrayList<Teaching>();
		Teaching teaching = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
		conn = OracleConnection.getConnection();
		String sql ="SELECT c.COURSE_NAME, c.MINIMUN_GPA, i.FULL_NAME , i.EMAIL\r\n" + 
					"FROM COURSE c\r\n" + 
					"JOIN TEACHING t ON c.COURSE_ID=t.COURSE_ID\r\n" + 
					"JOIN INSTRUCTOR i ON i.INSTRUCTOR_ID=t.INSTRUCTOR_ID";
		ps = conn.prepareStatement(sql);
		result = ps.executeQuery();
		
		while (result.next()) {
			teaching = new Teaching();
			teaching.setCourse_name(result.getString(1));
			teaching.setMinimum_gpa(result.getDouble(2));
			teaching.setFull_name(result.getString(3));
			teaching.setEmail(result.getString(4));
			teachingList.add(teaching);
		}
		}catch (Exception e) {
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
		System.out.println(teachingList);
		return teachingList;
	}
}
