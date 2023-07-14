package coding.mentor.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coding.mentor.entity.Book;
import coding.mentor.entity.Order;
import coding.mentor.entity.OrderDetails;
import coding.mentor.service.BookService;
import coding.mentor.service.OrderService;
import coding.mentor.service.OrderDetailsService;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {
			String command = request.getParameter("command");
			int bookId = 0;
			if(command != null && command.equals("ADD_TO_CART")) {
				bookId = Integer.parseInt(request.getParameter("bookId"));
				BookService bookService = new BookService();
				Book book = bookService.getBookDetails(bookId);
				HttpSession session = request.getSession();
				Map<Integer, Book> cart = (Map<Integer, Book>) session.getAttribute("cart");
				if(cart == null) {
					cart = new HashMap<Integer, Book>();
				}
				cart.put(book.getId(), book);
				session.setAttribute("cart", cart);
				request.setAttribute("book", book);
				//go back to the home page
				response.sendRedirect("home?command=DETAIL&bookId=" + bookId);
							
			}else if(command != null && command.equals("VIEW_CART")) {
				response.sendRedirect("cart.jsp");
			}
			else if(command !=null && command.equals("REMOVE")) {
				bookId = Integer.parseInt(request.getParameter("bookId"));
				HttpSession session = request.getSession();
				Map<Integer, Book> cart = (Map<Integer, Book>) session.getAttribute("cart");
				cart.remove(bookId);
				response.sendRedirect("cart.jsp");
			}else if(command != null && command.equals("SUBMIT_CART")) {
				HttpSession session = request.getSession();
				Map<Integer, Book> cart = (Map<Integer, Book>) session.getAttribute("cart");
				
				
				//create order - return orderId
				int userId = (int) session.getAttribute("userId");
				Order order = new Order(userId, false);
				OrderService orderService = new OrderService();
				Integer orderID = orderService.addOrder(order);
		
				session.setAttribute("orderId", orderID);
				
				//create orderDetails
				for(int key: cart.keySet()) {
					OrderDetails orderDetails = new OrderDetails(orderID, key);
					OrderDetailsService orderDetailsService = new OrderDetailsService();
					orderDetailsService.addOrderDetails(orderDetails);
					
				}

				session.removeAttribute("cart");
				//response.sendRedirect("cart.jsp");
				System.out.println(orderID);
				if(orderID != null) {
					double totalPrice = 0;
					OrderDetailsService orderDetailsService = new OrderDetailsService();
					List<Book> orderDetails = orderDetailsService.showOrderDetails(orderID);
					System.out.println(orderDetails.size());
					for(Book book: orderDetails) {
						totalPrice = totalPrice + book.getPrice();
					}
					RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
					request.setAttribute("orderDetails", orderDetails);
					request.setAttribute("totalPrice", totalPrice);
					rd.forward(request, response);
				}
			
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
