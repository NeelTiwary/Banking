package com.Account;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/DepositeServlet")
public class DepositeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String driver = "oracle.jdbc.driver.OracleDriver";
		Class.forName(driver);	
		
		
		//connection
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="system";
		String password="admin";
		
		Connection con = DriverManager.getConnection(url,user,password);
		
		
		PrintWriter out = response.getWriter();
		int num= Integer.parseInt(request.getParameter("num"));
		int balance=Integer.parseInt(request.getParameter("balance"));
		
		String query="update account set balance=balance+? where num=?";
		PreparedStatement ps =con.prepareStatement(query);
		ps.setInt(1,balance);
		ps.setInt(2,num);
		ps.executeUpdate();
		out.println("Inserted");
		ps.close();
	}catch(Exception e) {
		System.out.println(e.getMessage());
	}
	}
}
