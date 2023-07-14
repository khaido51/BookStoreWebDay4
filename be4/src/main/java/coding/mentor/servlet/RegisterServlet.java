package coding.mentor.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coding.mentor.entity.Account;
import coding.mentor.service.RegisterService;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		// Register account
		// read data from user input
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		Account account = new Account(username, password, email);
		
		// Validate User Input
		List<String> errorMessages = validateData(account);
		if(!errorMessages.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
			request.setAttribute("errorMessages", errorMessages);
			rd.forward(request, response);
			return;
		}
		
		

		// Create a new account
		RegisterService registerService = new RegisterService();
		
		try {
			boolean isInsertSuccess = registerService.registerAccount(account);
			if (isInsertSuccess) {
				response.sendRedirect("success.jsp");
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
				request.setAttribute("errorMessage", "Error, Please re-input your username!");
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
		// rd.forward(request, response);
	}
	
	private List<String> validateData(Account account){
		//Regular Expression
		Pattern p = Pattern.compile("^[a-zA-Z_0-9]*$");
		List<String> errors = new ArrayList<String>();
		
		boolean isUsernameMatched = p.matcher(account.getUsername()).find();
		boolean isPasswordMatched = p.matcher(account.getPassword()).find();


		// if invalid show error message -> register.jsp
		// the message must tell the exact error that occurs
		// user, password, email are valid
		if(!isUsernameMatched) {
			errors.add("Username must contain alphabet and numeric only!!");
			
		}
		
		if(!isPasswordMatched) {
			errors.add("Password must contain alphabet and numeric only!!");
		}
		return errors;
	}

}
