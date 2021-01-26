package org.studyeasy;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

@WebServlet("/Demo")
public class Demo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/project")
	private DataSource dataSource;
	//private MysqlDataSource dataSource;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Step 1: Initialize connection objects
		   PrintWriter out = response.getWriter();
		   
	        String url = "jdbc:mysql://127.0.0.1:3306/studyeasy_db?serverTimezone=CET";
	        String user = "zoli";
	        String password = "Zoli_123!";

           Connection connect = null;
           Statement stmt = null;
           ResultSet rs = null;
           try {
        	   connect = dataSource.getConnection();
        	   //connect = DriverManager.getConnection(url, user, password);
			
			// Step 2: Create a SQL statements string
			String query = "Select * from users";
			stmt = connect.createStatement();

			// Step 3: Execute SQL query
            rs = stmt.executeQuery(query);
            
			// Step 4: Process the result set
			while(rs.next()){
				out.print("<br/>"+rs.getString("email"));
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}








