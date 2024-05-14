

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String Username = request.getParameter("uname");
		String password = request.getParameter("pwd");
		String Cpassword = request.getParameter("ConfirmPwd");
		String driver="oracle.jdbc.driver.OracleDriver";
		String url ="jdbc:oracle:thin:@localhost:1521:xe";
		
	if (password.equals(Cpassword)) {
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,"system","admin");
			String query="insert into Register values(?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,Username);
			ps.setString(2,password);
			int count = ps.executeUpdate();
			//out.println(count);
			if(count>0) {
				out.println("<h4 style='color:red'>Registertation Done</h4>");
				RequestDispatcher rd= request.getRequestDispatcher("Login.html");
				rd.include(request,response);
			}
			else {
				out.println("<h4 style='color:red'>Registertation Not Done</h4>");
				RequestDispatcher rd= request.getRequestDispatcher("Register.html");
				rd.include(request,response);
			}
		}
		catch(Exception e) {
			out.println("Exception"+e.getMessage());
		}
	}
	else {
		out.println("Confirm password and password mismatched");
		RequestDispatcher rd= request.getRequestDispatcher("Register.html");
		rd.include(request,response);
	}
}
}
