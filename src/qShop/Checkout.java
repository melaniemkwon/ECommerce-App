package qShop.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public void init( ServletConfig config ) throws ServletException
    {
        super.init( config );

        try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException e )
        {
            throw new ServletException( e );
        }
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<cartInventory> cart = (ArrayList<cartInventory>) request.getSession().getAttribute("cart");
		
		double grandTotal = 0;
		for (cartInventory c : cart) {
			grandTotal += c.getTotal();
		}
		
		request.setAttribute("totalPrice", grandTotal);
		
		System.out.println("totalPrice in cart = " + grandTotal);
		//System.out.println("num items in cart = " + itemCount);
		
//		List<cartInventory> cartInventory = new ArrayList<cartInventory>();
//		Connection c = null;
//        try
//        {
//        	String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu28";
//            String username = "cs3220stu28";
//            String password = "";
//            
//            c = DriverManager.getConnection( url, username, password );
//            Statement stmt = c.createStatement();
//            ResultSet rs = stmt.executeQuery( "select * from cart" );
//            while(rs.next()){
//            	cartInventory items = new cartInventory(rs.getInt("id"), rs.getString("itemName"), rs.getString("itemImage"), rs.getInt("itemQuantity"),rs.getDouble("itemPrice"));
//            	cartInventory.add(items);
//       
//            }
//            Statement stmt1 = c.createStatement();
//            ResultSet rs1 = stmt1.executeQuery( "select sum(itemPrice) from cart" );
//            while(rs1.next()){
//            	String totalPrice = rs1.getString("sum(itemPrice)");
//            	request.setAttribute("totalPrice", totalPrice);
//            }
//
//        }
//        catch( SQLException e )
//        {
//            throw new ServletException( e );
//        }
//        finally
//        {
//            try
//            {
//                if( c != null ) c.close();
//            }
//            catch( SQLException e )
//            {
//                throw new ServletException( e );
//            }
//        }
//        
//        request.setAttribute("cartInventory", cartInventory);
        
        request.getSession().setAttribute("cart", cart);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/store/checkout.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<cartInventory> cart = (ArrayList<cartInventory>) request.getSession().getAttribute("cart");
		
		// FORM CONTROLS
		String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        boolean isError = false;
        if (firstName == null || firstName.trim().length() == 0) {
            request.setAttribute("error_firstName", "Missing first name.");
            isError = true;
        }
        if (lastName == null || lastName.trim().length() == 0) {
            request.setAttribute("error_lastName", "Missing last name.");
            isError = true;
        }
        if (email == null || email.trim().length() == 0) {
            request.setAttribute("error_email", "Missing email.");
            isError = true;
        }
        
        // If there's a user input error, redisplay the form
        if (isError) {
            System.out.println("Error on order form submission.");
            doGet(request, response);
            return;
        }
        // Else successfully submit the order. Execute transaction on database.
        else {
        	Connection c = null;
            try
            {
            	String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu28";
                String username = "cs3220stu28";
                String password = "";
                
                c = DriverManager.getConnection( url, username, password );
                
                //subtract cart item quantities from itemInventory
                for (cartInventory i : cart) {
                	String sql = "UPDATE itemInventory SET quantity = ? WHERE id = ?";
    	            PreparedStatement pstmt = c.prepareStatement( sql );
    	            pstmt.setInt( 1, i.getQuantity() );
    	            pstmt.setInt( 2, i.getId() );
    	            pstmt.executeUpdate();
        		}
                
                //empty the cart
                cart.clear();
                request.getSession().setAttribute("cart", cart);
            }
            catch( SQLException e )
            {
                throw new ServletException( e );
            }
            finally
            {
                try
                {
                    if( c != null ) c.close();
                }
                catch( SQLException e )
                {
                    throw new ServletException( e );
                }
            }
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/store/thankyou.jsp");
    		dispatcher.forward(request, response);
        }// end else
	}
}

