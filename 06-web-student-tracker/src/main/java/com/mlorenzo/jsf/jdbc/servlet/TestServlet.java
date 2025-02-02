package com.mlorenzo.jsf.jdbc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Define datasource/connection pool for Resource Injection.
	// Mediante esta anotación, inyectamos en esta propiedad el DataSource "jdbc/web_student_tracker" definido en el archivo
	// "context.xml" dentro del directorio "META-INF".
	@Resource(name = "jdbc/web_student_tracker")
	private DataSource dataSource;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Step 1: Set up the printwriter and set the content type
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		try {
			// Step 2: Get a connection to the database
			Connection myConn = dataSource.getConnection();
			
			// Step 3: Create a SQL statements
			String sql = "select * from students";
			Statement myStmt = myConn.createStatement();
			
			// Step 4: Execute SQL query
			ResultSet myRs = myStmt.executeQuery(sql);
			
			// Step 5: Process the result set
			while(myRs.next()) {
				String email = myRs.getString("email");
				out.println(email);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
