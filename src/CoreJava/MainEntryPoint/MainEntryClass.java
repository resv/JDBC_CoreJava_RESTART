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
		
		
		
		
	//TESTING QUERY FROM STUDENTS (ID) WORKS!	
		System.out.println("testing console");
		
			//TESTING CONNECTION
		
		Connection conn = OracleConnection.getConnection();
		
		String sql = "SELECT FULL_NAME FROM STUDENT";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		
//		ps.setInt(1, id);
		
		ResultSet results = ps.executeQuery();
		
		while (results.next()) {
			System.out.println(results.getString(1)); // 1 is the first column, change number for next column
		}
		
		
		
		
		
		
	}

}
