

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


@WebServlet("/DisplayAccount")
public class DisplayAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
int num= Integer.parseInt(request.getParameter("num"));
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
			String query="select * from account where num=?";
			PreparedStatement ps= con.prepareStatement(query);
			ps.setInt(1, num);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
			out.println("Details:"+ rs.getInt(1)+ ","+rs.getString(2)+","+rs.getInt(3));
			}
			else {
				out.println("Not Deleted");
			}
		
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	}



