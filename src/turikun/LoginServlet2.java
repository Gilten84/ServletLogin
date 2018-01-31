 package turikun;

import java.io.IOException;
import java.util.Enumeration;
import java.sql.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		response.getWriter().write(createForm(null));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		boolean check = false;
		try{
			//D:\eclipse work space\LoginServletApp2\WebContent\WEB-INF\lib\mysql-connector-java-5.1.45-bin.jar
			Class.forName("org.gjt.mm.mysql.Driver");
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/products?autoReconnect=true&useSSL=false","root","toporpales#?2345");
			Statement myStmt = myConn.createStatement();			
			ResultSet myRs = myStmt.executeQuery("select * from security");
			while (myRs.next())
			{
				if (myRs.getString("user").equals(userName)&&myRs.getString("password").equals(password))
				{
					check=true;
				}
			}
				
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
		StringBuilder responseStr = new StringBuilder();
		response.setContentType("text/html");
		if (check)
		{
			responseStr.append("<h2>Welcome admin!</h2>")
			.append("You are successfully logged in");

		}
		else
		{
			responseStr.append(createForm("Invalid user id or password. Please try again"));	
		}
		
		response.getWriter().write(responseStr.toString());
	}
	
	protected String createForm(String errMsg)
	{
		StringBuilder sb = new StringBuilder("<h2>Login</h2>");
		if (errMsg!=null)
		{
			sb.append("<span style='color: red;'>")
			.append(errMsg)
			.append("</span>");
		}
		sb.append("<form method='post'>\n")
		.append("User Name: <input type='text' name='userName'<br>\n")
		.append("Password: <input type='password' name='password'<br>\n")
		.append("<button type='submit' name='submit'>Submit</button>\n")
		.append("<button type='reset'>Reset</button>\n")
		.append("</form>");
		return sb.toString();
	}

}
