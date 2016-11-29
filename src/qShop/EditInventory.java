package qShop.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/EditInventory")
public class EditInventory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Item item = null;
		
		Connection c = null;
		
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu28";
            String username = "cs3220stu28";
            String password = "DUMMY123";

            c = DriverManager.getConnection( url, username, password );
            
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM itemInventory WHERE id = " +  id);

            while( rs.next() ) {
                item = new Item( rs.getInt("id"), rs.getString("itemName"), rs.getString("itemImage"), rs.getString("description"),rs.getInt("quantity"), rs.getDouble("itemPrice") );
            }
		}
		catch( SQLException e ) {
            throw new ServletException( e );
        }
        finally {
            try {
                if( c != null ) c.close();
            }
            catch( SQLException e ) {
                throw new ServletException( e );
            }
        }
		
		getServletContext().setAttribute("item", item);
		request.getRequestDispatcher( "/WEB-INF/store/EditInventory.jsp" ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// If there is an error in user input, redisplay form
		if (request.getParameter("itemName").isEmpty() || request.getParameter("itemImage").isEmpty() || request.getParameter("itemDescription").isEmpty() || !isDouble(request.getParameter("itemPrice")) || !isInteger(request.getParameter("quantity")) ) {
			doGet(request, response);	
		}
		else {
			String id = request.getParameter( "id" );
			String itemName = request.getParameter("itemName");
			String itemImage = request.getParameter("itemImage");
			String itemDescription = request.getParameter("itemDescription");
			String itemQuantity = request.getParameter("quantity");
			String itemPrice = request.getParameter("itemPrice");
			
			Connection c = null;
			
			try {
				String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu28";
	            String username = "cs3220stu28";
	            String password = "DUMMY123";
	            c = DriverManager.getConnection( url, username, password );
	            
	            String sql = "UPDATE itemInventory SET itemName = ?, itemImage = ?, description = ?, quantity = ?, itemPrice = ? WHERE id = ?";
	            PreparedStatement pstmt = c.prepareStatement( sql );
	            
	            pstmt.setString( 1, itemName );
	            pstmt.setString( 2, itemImage );
	            pstmt.setString( 3, itemDescription );
	            pstmt.setString( 4, itemQuantity );
	            pstmt.setString( 5, itemPrice );
	            pstmt.setString( 6, id );
	            pstmt.executeUpdate();
	            
			}
			catch( SQLException e ) {
	            throw new ServletException( e );
	        }
	        finally {
	            try {
	                if( c != null ) c.close();
	            }
	            catch( SQLException e ) {
	                throw new ServletException( e );
	            }
	        }
			
			response.sendRedirect("Inventory");
		}
	}
	public boolean isInteger(String s) {
	      boolean isValidInteger = false;
	      
	      try {
	         Integer.parseInt(s);
	         isValidInteger = true;
	      }
	      catch (NumberFormatException ex) {
	      }
	 
	      return isValidInteger;
	}
	
	public boolean isDouble(String s) {
	      boolean isValidDouble = false;
	      
	      try {
	         Double.parseDouble(s);
	         isValidDouble = true;
	      }
	      catch (NumberFormatException ex) {
	      }
	 
	      return isValidDouble;
	}
}
