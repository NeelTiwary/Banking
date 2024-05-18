package com.account;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num= Integer.parseInt(request.getParameter("num"));
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
			String query="delete from account where num=?";
			PreparedStatement ps= con.prepareStatement(query);
			ps.setInt(1, num);
			int count = ps.executeUpdate();
			if (count>0) {
			out.println("<h2>Deleted</h2>");
			}
			else {
				out.println("Not Deleted");
			}
		
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

