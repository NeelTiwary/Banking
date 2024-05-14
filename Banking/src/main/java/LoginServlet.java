

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String Username = request.getParameter("uname");
		String password = request.getParameter("pwd");
		String driver="oracle.jdbc.driver.OracleDriver";
		String url ="jdbc:oracle:thin:@localhost:1521:xe";
		
			try {
				Class.forName(driver);
				Connection con = DriverManager.getConnection(url,"system","admin");
				String query="Select * from Register where username=? and pwd=?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1,Username);
				ps.setString(2,password);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					RequestDispatcher rd =request.getRequestDispatcher("Success.html");
					rd.forward(request, response);
				}
				else {
					out.println("<h4 style='color:red'>Registertation Not Done</h4>");
					RequestDispatcher rd= request.getRequestDispatcher("Login.html");
					rd.include(request,response);
				}
			}
			catch(Exception e) {
				out.println("Exception"+e.getMessage());
			}
		}

	}


