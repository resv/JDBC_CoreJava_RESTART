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
		ResultSet results = ps.executeQuery();

		/*
		 * 1 is the first column, change number for next column, this is because we
		 * queried for *.GETTING RESULTS SETS ARE PERTAINING TO VIEWS, if views have
		 * more than one column then changing the 1 to another number will get that
		 * respective column if we change * to a specific column name we will get that
		 * returned, and the value 1 becomes views
		 */
//		ps.setInt(1, id);

		while (results.next()) {
			System.out.println(results.getString(1));
		}

		/*
		 * UNCOMMENT METHODS BELOW TO TEST EACH METHOD, METHOD WILL RETURN MEMORY
		 * ADDRESS, YOU MUST RUN DEBUGGER AND CONFIRM THAT THE VALUES ARE CORRECT IN
		 * EACH OBJECT/LIST BEING RETURNED
		 */

//		vgetStudentByGmail("b@gmail.com"); // tested and works with other student emails
//		vvalidateUser("111", "111"); //false also works by changing the 2nd parameter to not match
//		vgetAllInstructors(); //returns list array with all instructor objects
//		vgetInstructoByGmail("lance@gmail.com"); //returns instructor object based on email in param
//		vgetValidateUser(ins , "555"); NOT SURE HOW TO TEST THIS BECAUSE I DON'T know how to fit instructor object in param
//		vgetAllCourses();
	}

//TESTING QUERY FOR STUDENT BY EMAIL, DEBUGGED AND WORKS!
	public static Student vgetStudentByGmail(String email) throws ClassNotFoundException, IOException, SQLException {

		Student student = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet result = null;

		try {
			/* Connection */ conn = OracleConnection.getConnection();
			student = new Student();
			String sql = "SELECT * FROM STUDENT WHERE email = ?";
			ps = conn.prepareStatement(sql);
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
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		System.out.println(student);
		return student;
	}

//TESTING IF PASSWORD RETURNS BOOL! DEBUGGED AND WORKS!!!!
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
				System.out.println(passToValidate + " matches!!! " + comparablePas);
				return true;
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
		System.out.println("false!!");
		return false;
	}

//TESTING IF GET ALL INSTRUCTORS! DEBUGGED AND WORKS!!!
	private static List<Instructor> vgetAllInstructors() throws SQLException {
		List<Instructor> arr = new ArrayList<>();
		Instructor instructor = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet result = null;

		try {
			conn = OracleConnection.getConnection();
			String sql = "SELECT * FROM INSTRUCTOR";
			ps = conn.prepareStatement(sql);
			result = ps.executeQuery();
			while (result.next()) {
				instructor = new Instructor();
				instructor.setInstructor_id(result.getInt(1));
				instructor.setFull_name(result.getString(2));
				instructor.setEmail(result.getString(3));
				instructor.setSpeciality(result.getString(4));
				instructor.setAdmin_role(result.getInt(5));
				arr.add(instructor);
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
		System.out.println("this is suppose to return all instructors");
		System.out.println(arr);
		return arr;
	}

//TESTING IF EMAIL PARAM RETURNS INSTRUCTOR OBJECT AND WORKS!!!
	private static Instructor vgetInstructoByGmail(String email) throws SQLException {
		Connection conn = null;
		Instructor instructor = null;
		PreparedStatement ps = null;
		ResultSet result = null;

		try {
			conn = OracleConnection.getConnection();
			instructor = new Instructor();
			String sql = "SELECT * FROM INSTRUCTOR WHERE EMAIL=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			result = ps.executeQuery();

			if (result.next()) {
				instructor.setInstructor_id(result.getInt(1));
				instructor.setFull_name(result.getString(2));
				instructor.setEmail(result.getString(3));
				instructor.setSpeciality(result.getString(4));
				instructor.setAdmin_role(result.getInt(5));
				instructor.setPass(result.getString(6));
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
		System.out.println("This is suppose to print out instructor object");
		return instructor;
	}


//TESTING, GRABS OBJECT INSTRUCTOR AND PASS THEN RETURNS admin/instructor/wrong CANNOT TEST ATM	
	String vgetValidateUser(Instructor ins, String comparablePas) throws SQLException {
		Connection conn = null;
		Instructor instructor = null;
		PreparedStatement ps = null;
		ResultSet result = null;

		String answer ="";

		try {
			conn = OracleConnection.getConnection();
			instructor = new Instructor();
			String sql = "SELECT * FROM INSTRUCTOR WHERE PASS=?";
			ps = conn.prepareStatement(sql);
			String insPass = ins.getPass();
			ps.setString(1, insPass);
			result = ps.executeQuery();

			if (insPass.equals(comparablePas) && ins.getAdmin_role() == 1) {
				answer = "Admin";
			} else if (insPass.equals(comparablePas) && ins.getAdmin_role() == 0) {
				answer = "Instructor";
			} else if (!insPass.equals(comparablePas)) {
				answer = "Wrong Credentials";
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
		return answer;
	}


//TESTING QUERY TO GET ALL COURSES! DEBUGGED AND WORKS!!!
	private static List<Course> vgetAllCourses() throws SQLException {
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
		System.out.println(courses);//
		return courses;
	}

}// main end
