package CoreJava.MainEntryPoint;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import CoreJava.CustomExceptions.StudentRegistrationException;
import CoreJava.DAO.AttendingDAO;
import CoreJava.DAO.CourseDAO;
import CoreJava.DAO.InstructorDAO;
import CoreJava.DAO.OracleConnection;
import CoreJava.DAO.StudentDAO;
import CoreJava.DAO.TeachingDAO;
import CoreJava.Models.Attending;
import CoreJava.Models.Course;
import CoreJava.Models.Instructor;
import CoreJava.Models.Student;
import CoreJava.Models.Teaching;

public class MainEntryClass {

	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {

		// TESTING QUERY FROM STUDENTS (ID) WORKS!
		System.out.println("testing console");

		// TESTING CONNECTION

		Connection conn = OracleConnection.getConnection();

		String sql = "SELECT * FROM STUDENT";

		PreparedStatement ps = conn.prepareStatement(sql);

//		ps.setInt(1, id);

		ResultSet results = ps.executeQuery();

		// 1 is the first column, change number for next column, this is because we
		// queried for *
		// GETTING RESULTS SETS ARE PERTAINING TO VIEWS, if views have more than one
		// column then
		// changing the 1 to another number will get that respective column
		// if we change * to a specific column name we will get that returned, and the
		// value 1 becomes
		// views
		while (results.next()) {
			System.out.println(results.getString(1));
		}

//		vgetStudentByGmail("b@gmail.com");
//		vvalidateUser("111", "111");
		vgetAllInstructors();

	}

// TESTING QUERY FOR STUDENT BY EMAIL, DEBUGGED AND WORKS!
	public static Student vgetStudentByGmail(String email) throws ClassNotFoundException, IOException, SQLException {

		Student student = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;

		try {
			/* Connection */ conn = OracleConnection.getConnection();
			student = new Student();
			String sql = "SELECT * FROM STUDENT WHERE email = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			/* ResultSet */ result = ps.executeQuery();

			if (result.next()) {
				student.setStudent_id(result.getInt(1));
				student.setFull_name(result.getString(2));
				student.setEmail(result.getString(3));
				student.setGpa(result.getInt(4));
				student.setPass(result.getString(5));
				student.setStudent_role(result.getInt(6));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		System.out.println(student);
		return student;
	}
	
	
//TESTING IF PASSWORD RETURNS BOOL! WORKS!!!!
	public static Boolean vvalidateUser(String passToValidate, String comparablePas) throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet result = null;

		try {
			conn = OracleConnection.getConnection();
			String sql = "SELECT * FROM STUDENT WHERE PASS=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, passToValidate);
			result = ps.executeQuery();
			
			if (passToValidate.equals(comparablePas)) {
				System.out.println( passToValidate + " matches!!! "+ comparablePas);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		System.out.println("false!!");
		return false;
	}

	//TESTING IF GET ALL INSTRUCTORS WORK
	private static void vgetAllInstructors() {
		List<Instructor> arr = new ArrayList<>();
		Instructor instructor  = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			conn = OracleConnection.getConnection();
			String sql = "SELECT * FROM INSTRUCTOR";
			ps = conn.prepareStatement(sql);
			while (result.next()) {
				
			}
		}
		
		
	}

	
}
