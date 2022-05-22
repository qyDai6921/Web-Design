package logic;

import java.sql.*;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/** 
 * StudentDAO class  that encapsulates code to store and retrieve the Survey data into/from a database. 
 * 
 * It provides two methods: saveData, getData
 */
public class StudentDAO {
	private final static String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final static String DB_URL = "*****";
	private final static String USERNAME = "*****";
	private final static String PASSWORD = "*****";
	
	private static String querySET = "INSERT INTO STUDENTS (sid, sname, address, city, state, zipcode, telephone, email, url, INTERESTEDAREA, highestDiff, dos) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  TO_DATE(?, 'YYYY-MM-DD'))";
	private static String queryGET = "SELECT sid, sname, address, city ,state, zipcode, telephone, email, url, INTERESTEDAREA, highestDiff, dos FROM STUDENTS where sid = ?";

	private static String queryGETALL = "SELECT sid FROM STUDENTS";
	private static PreparedStatement myStmt;
	private static PreparedStatement myStmt2;
	
	private final static Logger logger = Logger.getLogger(StudentDAO.class.getName());

	public static List<String> saveData(List<String> data) {
		
		List<String> parts = new ArrayList<String>();
		
		try {
			Class.forName(DRIVER_NAME); // Test Java Oracle library

			logger.info("Connecting to Oracle...");

			Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

			logger.info("Connected!");
			
			myStmt = conn.prepareStatement(querySET);
			myStmt2 = conn.prepareStatement(queryGETALL);
			
			myStmt.setInt(1, Integer.parseInt(data.get(0)));	//student_id
			myStmt.setString(2, data.get(1));					//name
			myStmt.setString(3, data.get(2));					//address
			myStmt.setString(4, data.get(3));					//city
			myStmt.setString(5, data.get(4));					//state
			myStmt.setString(6, data.get(5));					//zipcode
			myStmt.setString(7, data.get(6));					//telephone_number
			myStmt.setString(8, data.get(7));					//e_mail
			myStmt.setString(9, data.get(8));					//url
			myStmt.setString(10, data.get(9));					//InterestedArea
			myStmt.setString(11, data.get(10));					//highestDiff
			myStmt.setString(12, data.get(11));					//date_of_survey
			
			int res = myStmt.executeUpdate();
			
			logger.info(res + " records inserted");
			
			ResultSet myRes = myStmt2.executeQuery();
			
			
			while(myRes.next())
			{
				parts.add(String.valueOf(myRes.getInt(1)));
			}

			conn.close();

			logger.info("Disconnected from Oracle!");
			
			return parts;

		} catch (Exception e) {
			logger.severe("ORACLE error detected: " + e);
		}

		return parts;
	}

	public static List<List<String>> getData(String student_id) {
		
		List<List<String>> masterList = new ArrayList<List<String>>();
		
		try {
			Class.forName(DRIVER_NAME); // Test Java Oracle library

			logger.info("Connecting to Oracle...");

			Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

			logger.info("Connected!");
			
			myStmt = conn.prepareStatement(queryGET);
			myStmt2 = conn.prepareStatement(queryGETALL);
			
			myStmt.setInt(1, Integer.parseInt(student_id));	//student_id
			
			ResultSet myRes = myStmt.executeQuery();
			ResultSet myRes2 = myStmt2.executeQuery();
			
			List<String> parts = new ArrayList<String>();
			
			while(myRes.next()) {
				for(int i = 1; i <= 12; i++)
				{
					if(i == 1) parts.add(String.valueOf(myRes.getInt(1)));
					else parts.add(myRes.getString(i));
				}
			}
			
			List<String> idparts = new ArrayList<String>();
			
			while(myRes2.next())
			{
				idparts.add(String.valueOf(myRes2.getInt(1)));
			}
			
			masterList.add(parts);			//index 0 = studentsParts
			masterList.add(idparts);		//index 1 = idParts

			conn.close();

			logger.info("Disconnected from Oracle!");
			
			return masterList;
			
		} catch (Exception e) {
			logger.severe("ORACLE error detected: " + e);
		}
		return masterList;
	}
}