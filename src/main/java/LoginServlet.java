

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		response.setContentType("text/html");
		PrintWriter pwd= response.getWriter();
		String username=request.getParameter("username");

		String password=request.getParameter("password");
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","");

			Statement stmt=con.createStatement();
			String query="select * from login where username='"+username+"' and password='"+password+"'";

			ResultSet rs=stmt.executeQuery(query);

			if(rs.next())
			{
			pwd.print("<h1>"+username+":Welcome to home page </h1> <br>");
			pwd.print("<h1>Login successfully</h1>");

			}
			else{
			pwd.print("<h1>"+username+":please enter valid user name </h1> <br>");
			pwd.print("<h1>Login Fail</h1>");


			}
			rs.close();
			stmt.close();
			con.close();

			}
			catch (ClassNotFoundException e) {
		    e.printStackTrace();
			} 
			catch (SQLException e) {
		    e.printStackTrace();
		}

	}
}

