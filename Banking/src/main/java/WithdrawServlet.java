

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String query="Select balance from account where num=?";
		String driver = "oracle.jdbc.driver.OracleDriver";
		Class.forName(driver);	
		
		
		//connection
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="system";
		String password="admin";
		
		Connection con = DriverManager.getConnection(url,user,password);
		
		PrintWriter out = response.getWriter();
		int num= Integer.parseInt(request.getParameter("num"));
		int amt=Integer.parseInt(request.getParameter("balance"));
		
		
		PreparedStatement ps =con.prepareStatement(query);
		ps.setInt(1, num);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			if(rs.getInt(1)>=amt){
				String query1="update account set balance = balance-? where num=?";
				PreparedStatement ps1 =con.prepareStatement(query1);
				ps1.setInt(1, amt);
				ps1.setInt(2, num);
				ps1.executeUpdate();
				System.out.println("Withdraw kaar liya apne");
				ps1.close();
			}
			else
			{
				System.out.println("HAHAHA paisa hea nhi hai itna ish account mae ");
			}
			ps.close();
			rs.close();
		}
		else
			System.out.println("galat account number dal raha hai bhai tu");
	}catch(Exception e) {
		System.out.println(e.getMessage());
	}
	
	}

}
