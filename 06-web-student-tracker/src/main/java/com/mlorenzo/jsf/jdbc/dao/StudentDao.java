package com.mlorenzo.jsf.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mlorenzo.jsf.jdbc.model.Student;

public class StudentDao {
	// Nota: "jdbc/web_student_tracker" es el nombre del recurso que hemos creado en el archivo "context.xml" para el DataSource.
	private final static String JNDI_NAME = "java:comp/env/jdbc/web_student_tracker";
	
	private static StudentDao instance;
	private final DataSource dataSource;
	
	// Como estamos usando el patrón Singleton, hacemos que el constructor de esta clase sea privado para que
	// no sea posible crear instancias de esta clase desde el exterior. La única forma de crear una instancia
	// o de obtenerla es mediante la invocación del método estático de abajo "getInstance".
	private StudentDao() throws NamingException {
		dataSource = getDataSource();
	}
	
	// Usamos el patrón Singleton
	public static StudentDao getInstance() throws NamingException {
		if(instance == null)
			instance = new StudentDao();
		
		return instance;
	}
	
	public List<Student> getStudents() throws SQLException {
		List<Student> students = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from students order by last_name";
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while(myRs.next()) {
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				
				// create new  student object
				Student student = new Student(id, firstName, lastName, email);
				
				// add it to the list of students
				students.add(student);
			}
			
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
		
		return students;
	}
	
	public void addStudent(Student student) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement for insert
			String sql = "insert into students (first_name, last_name, email) values (?, ?, ?)";
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setString(1, student.getFirstName());
			myStmt.setString(2, student.getLastName());
			myStmt.setString(3, student.getEmail());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	
	public Student getStudent(int studentId) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql statement for get selected student
			String sql = "select * from students where id=?";
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values
			myStmt.setInt(1, studentId);
			
			// execute query
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if(myRs.next()) {
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				
				return new Student(id, firstName, lastName, email);
			}
			else
				throw new Exception("Could not find student id " + studentId);
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}
	
	public void updateStudent(Student student) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql update statement
			String sql = "update students set first_name=?, last_name=?, email=? where id=?";
			myStmt = myConn.prepareStatement(sql);
			
			// set param values
			myStmt.setString(1, student.getFirstName());
			myStmt.setString(2, student.getLastName());
			myStmt.setString(3, student.getEmail());
			myStmt.setInt(4, student.getId());
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	
	public void deleteStudent(int studentId) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql delete statement
			String sql = "delete from students where id=?";
			myStmt = myConn.prepareStatement(sql);
			
			// set param values
			myStmt.setInt(1, studentId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	
	private void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if(rs != null)
				rs.close();
			
			if(stmt != null)
				stmt.close();
			
			if(conn != null)
				conn.close(); // doesn't really close it ... just puts back in connection pool
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();
		
		// lookup Connection Pool that was created by Tomcat
		DataSource dataSource = (DataSource) context.lookup(JNDI_NAME);
		
		return dataSource;
	}
}
